/*
 * Copyright 2019 Acosix GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.acosix.alfresco.rest.client.api.integration;

import java.util.UUID;

import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.internal.LocalResteasyProviderFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import de.acosix.alfresco.rest.client.api.AuthenticationV1;
import de.acosix.alfresco.rest.client.api.PeopleV1;
import de.acosix.alfresco.rest.client.jackson.RestAPIBeanDeserializerModifier;
import de.acosix.alfresco.rest.client.jaxrs.BasicAuthenticationClientRequestFilter;
import de.acosix.alfresco.rest.client.model.authentication.TicketRequest;
import de.acosix.alfresco.rest.client.model.people.PersonRequestEntity;
import de.acosix.alfresco.rest.client.model.people.PersonResponseEntity;

/**
 * @author Axel Faust
 */
public class PeopleV1Tests
{

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private BasicAuthenticationClientRequestFilter rqAuthFilter;

    private ResteasyWebTarget target;

    private AuthenticationV1 authenticationAPI;

    private PeopleV1 peopleAPI;

    @Before
    public void setup()
    {
        final SimpleModule module = new SimpleModule();
        module.setDeserializerModifier(new RestAPIBeanDeserializerModifier());

        final ResteasyJackson2Provider resteasyJacksonProvider = new ResteasyJackson2Provider();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_EMPTY);
        mapper.registerModule(module);
        resteasyJacksonProvider.setMapper(mapper);

        final LocalResteasyProviderFactory resteasyProviderFactory = new LocalResteasyProviderFactory(new ResteasyProviderFactory());
        resteasyProviderFactory.register(resteasyJacksonProvider);
        // will cause a warning regarding Jackson provider which is already registered
        RegisterBuiltin.register(resteasyProviderFactory);

        this.rqAuthFilter = new BasicAuthenticationClientRequestFilter();

        final ResteasyClient client = new ResteasyClientBuilder().providerFactory(resteasyProviderFactory).build();
        this.target = client.target(UriBuilder.fromPath("http://localhost:8082/alfresco"));
        this.target.register(this.rqAuthFilter);

        this.authenticationAPI = this.target.proxy(AuthenticationV1.class);
        this.peopleAPI = this.target.proxy(PeopleV1.class);
    }

    @Test
    public void createNewPersonAndSelfChangePasswordWithReLogin()
    {
        this.rqAuthFilter.setUserName("admin");
        this.rqAuthFilter.setAuthentication("admin");

        final PersonRequestEntity newPersonRq = new PersonRequestEntity();
        newPersonRq.setId(UUID.randomUUID().toString());
        newPersonRq.setFirstName("Max");
        newPersonRq.setLastName("Mustermann");
        newPersonRq.setEmail("max.mustermann@beispiel.de");
        newPersonRq.setPassword(UUID.randomUUID().toString());

        final PersonResponseEntity createdPerson = this.peopleAPI.createPerson(newPersonRq);

        this.rqAuthFilter.setUserName(createdPerson.getId());
        this.rqAuthFilter.setAuthentication(newPersonRq.getPassword());

        final PersonRequestEntity pwChangeRq = new PersonRequestEntity();
        pwChangeRq.setOldPassword(newPersonRq.getPassword());
        pwChangeRq.setPassword(UUID.randomUUID().toString());

        this.peopleAPI.updatePerson("-me-", pwChangeRq);

        this.rqAuthFilter.setUserName(null);
        this.rqAuthFilter.setAuthentication(null);

        final TicketRequest loginRq = new TicketRequest();
        loginRq.setUserId(createdPerson.getId());
        loginRq.setPassword(pwChangeRq.getPassword());

        this.authenticationAPI.createTicket(loginRq);
    }

    // TODO Additional tests
}
