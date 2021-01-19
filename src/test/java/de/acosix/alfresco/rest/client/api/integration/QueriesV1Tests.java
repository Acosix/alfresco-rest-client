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

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.acosix.alfresco.rest.client.api.AuthenticationV1;
import de.acosix.alfresco.rest.client.api.NodesV1;
import de.acosix.alfresco.rest.client.api.QueriesV1;
import de.acosix.alfresco.rest.client.jackson.RestAPIBeanDeserializerModifier;
import de.acosix.alfresco.rest.client.model.authentication.TicketEntity;
import de.acosix.alfresco.rest.client.model.authentication.TicketRequest;
import de.acosix.alfresco.rest.client.model.common.MultiValuedParam;
import de.acosix.alfresco.rest.client.model.nodes.NodeCreationRequestEntity;
import de.acosix.alfresco.rest.client.model.nodes.NodeResponseEntity;
import de.acosix.alfresco.rest.client.model.nodes.PaginatedNodeChildrenList;
import de.acosix.alfresco.rest.client.resteasy.MultiValuedParamConverterProvider;
import org.apache.commons.codec.binary.Base64;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.internal.LocalResteasyProviderFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.UriBuilder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nugusbayev Kanagat
 */
public class QueriesV1Tests {

    private ResteasyWebTarget target;

    private QueriesV1 queriesAPI;

    private NodesV1 nodesAPI;

    @Before
    public void setup() {
        final SimpleModule module = new SimpleModule();
        module.setDeserializerModifier(new RestAPIBeanDeserializerModifier());

        final ResteasyJackson2Provider resteasyJacksonProvider = new ResteasyJackson2Provider();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_EMPTY);
        mapper.registerModule(module);
        resteasyJacksonProvider.setMapper(mapper);

        final LocalResteasyProviderFactory resteasyProviderFactory = new LocalResteasyProviderFactory(new ResteasyProviderFactory());
        resteasyProviderFactory.register(resteasyJacksonProvider);
        resteasyProviderFactory.register(new MultiValuedParamConverterProvider());
        // will cause a warning regarding Jackson provider which is already registered
        RegisterBuiltin.register(resteasyProviderFactory);

        final ResteasyClient client = new ResteasyClientBuilder().providerFactory(resteasyProviderFactory).build();
        this.target = client.target(UriBuilder.fromPath("http://localhost:8082/alfresco"));

        final TicketRequest rq = new TicketRequest();
        rq.setUserId("admin");
        rq.setPassword("admin");

        final AuthenticationV1 authenticationAPI = this.target.proxy(AuthenticationV1.class);
        final TicketEntity ticket = authenticationAPI.createTicket(rq);

        final ClientRequestFilter rqAuthFilter = (requestContext) -> {
            final String base64Token = Base64.encodeBase64String(ticket.getId().getBytes(StandardCharsets.UTF_8));
            requestContext.getHeaders().add("Authorization", "Basic " + base64Token);
        };
        this.target.register(rqAuthFilter);

        this.queriesAPI = this.target.proxy(QueriesV1.class);
        this.nodesAPI = this.target.proxy(NodesV1.class);
    }

    @Test
    public void createNewNodeAndFindItViaQueries() throws InterruptedException {
        final NodeCreationRequestEntity nodeToCreate = new NodeCreationRequestEntity();
        nodeToCreate.setNodeType("cm:folder");
        final String name = "foobar";
        nodeToCreate.setName(name);
        final Map<String, Object> properties = new HashMap<>();
        properties.put("cm:title", "foo bar");
        nodeToCreate.setProperties(properties);

        final NodeResponseEntity createdNode = this.nodesAPI.createNode("-root-", nodeToCreate);

        PaginatedNodeChildrenList paginatedNodeChildrenList = this.queriesAPI.queryNodes("foobar", "-root-", 0, 10, "cm:folder", new MultiValuedParam<String>("properties"), null, new MultiValuedParam<String>("id", "name", "parentId"));

        Assert.assertNotNull(paginatedNodeChildrenList);

        System.out.println(paginatedNodeChildrenList.getEntries());
        System.out.println(paginatedNodeChildrenList.getPagination());
        System.out.println(paginatedNodeChildrenList.getSource());
    }

}
