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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.UriBuilder;

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

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import de.acosix.alfresco.rest.client.api.NodesV1;
import de.acosix.alfresco.rest.client.api.NodesV1.IncludeOption;
import de.acosix.alfresco.rest.client.api.PeopleV1;
import de.acosix.alfresco.rest.client.jackson.RestAPIBeanDeserializerModifier;
import de.acosix.alfresco.rest.client.jaxrs.BasicAuthenticationClientRequestFilter;
import de.acosix.alfresco.rest.client.model.common.MultiValuedParam;
import de.acosix.alfresco.rest.client.model.nodes.CommonNodeEntity;
import de.acosix.alfresco.rest.client.model.nodes.NodeCreationRequestEntity;
import de.acosix.alfresco.rest.client.model.nodes.NodeLockRequestEntity;
import de.acosix.alfresco.rest.client.model.nodes.NodeLockRequestEntity.LockType;
import de.acosix.alfresco.rest.client.model.nodes.NodeResponseEntity;
import de.acosix.alfresco.rest.client.model.nodes.PermissionElement;
import de.acosix.alfresco.rest.client.model.nodes.PermissionElement.AccessStatus;
import de.acosix.alfresco.rest.client.model.nodes.PermissionsInfo;
import de.acosix.alfresco.rest.client.model.people.PersonRequestEntity;
import de.acosix.alfresco.rest.client.resteasy.MultiValuedParamConverterProvider;

/**
 * @author Axel Faust
 */
public class NodesLockTests
{

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private BasicAuthenticationClientRequestFilter rqAuthFilter;

    private ResteasyWebTarget target;

    private NodesV1 nodesAPI;

    private String user1Id;

    private String user2Id;

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

        this.rqAuthFilter = new BasicAuthenticationClientRequestFilter();
        this.rqAuthFilter.setUserName("admin");
        this.rqAuthFilter.setAuthentication("admin");

        final ResteasyClient client = new ResteasyClientBuilder().providerFactory(resteasyProviderFactory).build();
        this.target = client.target(UriBuilder.fromPath("http://localhost:8082/alfresco"));
        this.target.register(this.rqAuthFilter);

        this.nodesAPI = this.target.proxy(NodesV1.class);
    }

    @Test
    @Ignore // currently fails at non-owner update - check if Alfresco has a bug here
    public void defaultLock()
    {
        final PeopleV1 peopleAPI = this.target.proxy(PeopleV1.class);
        this.user1Id = UUID.randomUUID().toString();
        this.user2Id = UUID.randomUUID().toString();

        Arrays.asList(this.user1Id, this.user2Id).forEach(userId -> {
            final PersonRequestEntity newPersonRq = new PersonRequestEntity();
            newPersonRq.setId(userId);
            newPersonRq.setFirstName("Max");
            newPersonRq.setLastName("Mustermann");
            newPersonRq.setEmail("max.mustermann@beispiel.de");
            newPersonRq.setPassword(userId);
            peopleAPI.createPerson(newPersonRq);
        });

        this.rqAuthFilter.setUserName(this.user1Id);
        this.rqAuthFilter.setAuthentication(this.user1Id);

        final NodeCreationRequestEntity nodeToCreate = new NodeCreationRequestEntity();
        nodeToCreate.setNodeType("cm:content");
        final String name = "lockNode-" + UUID.randomUUID().toString();
        nodeToCreate.setName(name);

        final NodeResponseEntity createdNode = this.nodesAPI.createNode("-shared-", nodeToCreate);

        // set permissions so other user has update privileges (unless locked)
        final CommonNodeEntity<PermissionsInfo> permSetRq = new CommonNodeEntity<>();
        final PermissionsInfo perms = new PermissionsInfo();
        final PermissionElement perm = new PermissionElement();
        perm.setAccessStatus(AccessStatus.ALLOWED);
        perm.setAuthorityId(this.user2Id);
        perm.setName("Editor");
        perms.setLocallySet(Arrays.asList(perm));
        permSetRq.setPermissions(perms);
        this.nodesAPI.updateNode(createdNode.getId(), permSetRq);

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

        // other user is not allowed to update the node
        this.rqAuthFilter.setUserName(this.user2Id);
        this.rqAuthFilter.setAuthentication(this.user2Id);

        try
        {
            final CommonNodeEntity<PermissionsInfo> updateRq2 = new CommonNodeEntity<>();
            final List<String> aspectNames2 = new ArrayList<>();
            final List<String> existingAspectNames2 = lockNodeRes.getAspectNames();
            if (existingAspectNames2 != null)
            {
                aspectNames2.addAll(existingAspectNames2);
            }
            aspectNames2.remove("cm:author");
            this.nodesAPI.updateNode(createdNode.getId(), updateRq2);

            Assert.fail("Only the lock owner should have been allowed to update the node");
        }
        catch (final ForbiddenException ignore)
        {
            // ignore / expected
        }

        // unlock again
        this.rqAuthFilter.setUserName(this.user1Id);
        this.rqAuthFilter.setAuthentication(this.user1Id);

        final NodeResponseEntity unlockNodeRes = this.nodesAPI.unlockNode(createdNode.getId(),
                new MultiValuedParam<>(IncludeOption.IS_LOCKED), new MultiValuedParam<>());
        Assert.assertEquals(Boolean.FALSE, unlockNodeRes.getIsLocked());
    }

    @Test
    @Ignore // currently fails at owner update - check if Alfresco has a bug here
    public void fullLock()
    {
        final PeopleV1 peopleAPI = this.target.proxy(PeopleV1.class);
        this.user1Id = UUID.randomUUID().toString();

        Arrays.asList(this.user1Id).forEach(userId -> {
            final PersonRequestEntity newPersonRq = new PersonRequestEntity();
            newPersonRq.setId(userId);
            newPersonRq.setFirstName("Max");
            newPersonRq.setLastName("Mustermann");
            newPersonRq.setEmail("max.mustermann@beispiel.de");
            newPersonRq.setPassword(userId);
            peopleAPI.createPerson(newPersonRq);
        });

        this.rqAuthFilter.setUserName(this.user1Id);
        this.rqAuthFilter.setAuthentication(this.user1Id);

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

        this.expectedException.expect(ForbiddenException.class);

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
