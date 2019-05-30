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
package de.acosix.alfresco.rest.client.api.integration.nodesV1;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.UriBuilder;

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
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import de.acosix.alfresco.rest.client.api.AuthenticationV1;
import de.acosix.alfresco.rest.client.api.NodesV1;
import de.acosix.alfresco.rest.client.api.NodesV1.IncludeOption;
import de.acosix.alfresco.rest.client.jackson.RestAPIBeanDeserializerModifier;
import de.acosix.alfresco.rest.client.model.authentication.TicketEntity;
import de.acosix.alfresco.rest.client.model.authentication.TicketRequest;
import de.acosix.alfresco.rest.client.model.common.MultiValuedParam;
import de.acosix.alfresco.rest.client.model.nodes.CommonNodeEntity;
import de.acosix.alfresco.rest.client.model.nodes.NodeCreationRequestEntity;
import de.acosix.alfresco.rest.client.model.nodes.NodeLockRequestEntity;
import de.acosix.alfresco.rest.client.model.nodes.NodeLockRequestEntity.LockType;
import de.acosix.alfresco.rest.client.model.nodes.NodeResponseEntity;
import de.acosix.alfresco.rest.client.model.nodes.PermissionsInfo;
import de.acosix.alfresco.rest.client.resteasy.MultiValuedParamConverterProvider;

/**
 * @author Axel Faust
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class NodesLockTests
{

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private ResteasyWebTarget target;

    private NodesV1 nodesAPI;

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
        resteasyProviderFactory.register(new MultiValuedParamConverterProvider());

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

        this.nodesAPI = this.target.proxy(NodesV1.class);
    }

    @Test
    public void defaultLock()
    {
        final NodeCreationRequestEntity nodeToCreate = new NodeCreationRequestEntity();
        nodeToCreate.setNodeType("cm:content");
        final String name = "lockNode-" + UUID.randomUUID().toString();
        nodeToCreate.setName(name);

        final NodeResponseEntity createdNode = this.nodesAPI.createNode("-shared-", nodeToCreate);

        final NodeLockRequestEntity lockRq = new NodeLockRequestEntity();

        final NodeResponseEntity lockNodeRes = this.nodesAPI.lockNode(createdNode.getId(), lockRq,
                new MultiValuedParam<>(IncludeOption.IS_LOCKED), new MultiValuedParam<>());

        Assert.assertEquals(Boolean.TRUE, lockNodeRes.getIsLocked());

        // no way to assert specific lock lifetime / timeToExpire have been set as expected

        // lock owner is allowed to update the node
        final CommonNodeEntity<PermissionsInfo> updateRq = new CommonNodeEntity<>();
        final List<String> aspectNames = new ArrayList<>();
        final List<String> existingAspectNames = lockNodeRes.getAspectNames();
        if (existingAspectNames != null)
        {
            aspectNames.addAll(existingAspectNames);
        }
        aspectNames.add("cm:author");
        this.nodesAPI.updateNode(createdNode.getId(), updateRq);

        // TODO Test with different user once API to create users is covered

        final NodeResponseEntity unlockNodeRes = this.nodesAPI.unlockNode(createdNode.getId(),
                new MultiValuedParam<>(IncludeOption.IS_LOCKED), new MultiValuedParam<>());
        Assert.assertEquals(Boolean.FALSE, unlockNodeRes.getIsLocked());
    }

    @Test
    @Ignore // re-enable when non-admin user can be created for test
    public void fullLock()
    {
        // TODO Test with different user (non-admin) once API to create users is covered

        final NodeCreationRequestEntity nodeToCreate = new NodeCreationRequestEntity();
        nodeToCreate.setNodeType("cm:content");
        final String name = "lockNode-" + UUID.randomUUID().toString();
        nodeToCreate.setName(name);

        final NodeResponseEntity createdNode = this.nodesAPI.createNode("-shared-", nodeToCreate);

        final NodeLockRequestEntity lockRq = new NodeLockRequestEntity();
        lockRq.setType(LockType.FULL);

        final NodeResponseEntity lockNodeRes = this.nodesAPI.lockNode(createdNode.getId(), lockRq,
                new MultiValuedParam<>(IncludeOption.IS_LOCKED), new MultiValuedParam<>());

        Assert.assertEquals(Boolean.TRUE, lockNodeRes.getIsLocked());

        // no way to assert specific lock lifetime / timeToExpire have been set as expected

        // lock owner is not allowed to update the node
        final CommonNodeEntity<PermissionsInfo> updateRq = new CommonNodeEntity<>();
        final List<String> aspectNames = new ArrayList<>();
        final List<String> existingAspectNames = lockNodeRes.getAspectNames();
        if (existingAspectNames != null)
        {
            aspectNames.addAll(existingAspectNames);
        }
        aspectNames.add("cm:author");
        this.nodesAPI.updateNode(createdNode.getId(), updateRq);

        Assert.fail("Update should not be allowed with full lock");
    }

    @Test
    public void lockExpiry() throws Exception
    {
        final NodeCreationRequestEntity nodeToCreate = new NodeCreationRequestEntity();
        nodeToCreate.setNodeType("cm:content");
        final String name = "lockNode-" + UUID.randomUUID().toString();
        nodeToCreate.setName(name);

        final NodeResponseEntity createdNode = this.nodesAPI.createNode("-shared-", nodeToCreate);

        final NodeLockRequestEntity lockRq = new NodeLockRequestEntity();
        lockRq.setTimeToExpire(5);

        final NodeResponseEntity lockNodeRes = this.nodesAPI.lockNode(createdNode.getId(), lockRq,
                new MultiValuedParam<>(IncludeOption.IS_LOCKED), new MultiValuedParam<>());

        Assert.assertEquals(Boolean.TRUE, lockNodeRes.getIsLocked());

        Thread.sleep(5500);

        final NodeResponseEntity nodeDetailsRes = this.nodesAPI.getNode(createdNode.getId(),
                new MultiValuedParam<>(IncludeOption.IS_LOCKED), new MultiValuedParam<>());
        Assert.assertEquals(Boolean.FALSE, nodeDetailsRes.getIsLocked());
    }
}
