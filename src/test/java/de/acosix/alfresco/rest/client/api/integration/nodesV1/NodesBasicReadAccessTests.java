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
import java.util.Arrays;
import java.util.List;

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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
import de.acosix.alfresco.rest.client.model.nodes.ChildNodeResponseEntity;
import de.acosix.alfresco.rest.client.model.nodes.NodeResponseEntity;
import de.acosix.alfresco.rest.client.model.nodes.PaginatedNodeChildrenList;
import de.acosix.alfresco.rest.client.resteasy.MultiValuedParamConverterProvider;

/**
 * @author Axel Faust
 */
public class NodesBasicReadAccessTests
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
    public void absoluteNodeLookupDefaultScope()
    {
        final NodeResponseEntity rootNode = this.nodesAPI.getNode("-root-");
        this.validateRootNode(rootNode);

        final NodeResponseEntity rootNodeByExplicitLookup = this.nodesAPI.getNode(rootNode.getId());
        this.validateRootNode(rootNodeByExplicitLookup);
    }

    @Test
    public void relativeNodeLookupDefaultScope()
    {
        final NodeResponseEntity modelsFolder = this.nodesAPI.getNode("-root-", "Data Dictionary/Models");
        this.validateCoreNodeData(modelsFolder, "Models", "cm:folder");

        Assert.assertEquals("admin", modelsFolder.getCreatedByUser().getId());
        Assert.assertEquals("Administrator", modelsFolder.getCreatedByUser().getDisplayName());

        Assert.assertEquals("admin", modelsFolder.getModifiedByUser().getId());
        Assert.assertEquals("Administrator", modelsFolder.getModifiedByUser().getDisplayName());

        final NodeResponseEntity sharedFolder = this.nodesAPI.getNode("-root-", "Shared");
        this.validateCoreNodeData(sharedFolder, "Shared", "cm:folder");

        Assert.assertEquals("admin", sharedFolder.getCreatedByUser().getId());
        Assert.assertEquals("Administrator", sharedFolder.getCreatedByUser().getDisplayName());

        Assert.assertEquals("admin", sharedFolder.getModifiedByUser().getId());
        Assert.assertEquals("Administrator", sharedFolder.getModifiedByUser().getDisplayName());
    }

    @Test
    public void nodeLookupWithPermissions()
    {
        final NodeResponseEntity rootNode = this.nodesAPI.getNode("-root-",
                new MultiValuedParam<>(Arrays.asList(IncludeOption.ALLOWABLE_OPERATIONS, IncludeOption.PERMISSIONS)), null);
        this.validateRootNode(rootNode);

        Assert.assertNotNull("permissions should be loaded when requested", rootNode.getPermissions());
        Assert.assertNotNull("Root node permissions should have locally set permissions", rootNode.getPermissions().getLocallySet());
        Assert.assertFalse("Root node permissions should have locally set permissions",
                rootNode.getPermissions().getLocallySet().isEmpty());
        Assert.assertEquals("Root node permissions should not have inherited permissions", Boolean.FALSE,
                rootNode.getPermissions().getIsInheritanceEnabled());
        Assert.assertNull("Root node permissions should not have inherited permissions", rootNode.getPermissions().getInherited());

        Assert.assertNotNull("Root node permissions should have settable permissions", rootNode.getPermissions().getSettable());
        Assert.assertFalse("Root node permissions should have settable permissions", rootNode.getPermissions().getSettable().isEmpty());

        Assert.assertNotNull("allowableOperations should be loaded when requested", rootNode.getAllowableOperations());
        Assert.assertFalse("allowableOperations should not be empty on the root node", rootNode.getAllowableOperations().isEmpty());
        Assert.assertTrue("admin should have create operation allowed on root node", rootNode.getAllowableOperations().contains("create"));
        Assert.assertTrue("admin should have update operation allowed on root node", rootNode.getAllowableOperations().contains("update"));
        Assert.assertTrue("admin should have updatePermissions operation allowed on root node",
                rootNode.getAllowableOperations().contains("updatePermissions"));
    }

    @Test
    public void rootChildren()
    {
        final PaginatedNodeChildrenList rootChildren = this.nodesAPI.getChildren("-root-");
        final List<ChildNodeResponseEntity> children = rootChildren.getEntries();
        Assert.assertNotNull("Pagination info should always be provided", rootChildren.getPagination());
        Assert.assertEquals(rootChildren.getPagination().getCount(), children.size());

        Assert.assertFalse("Children of -root- should not be empty", children.isEmpty());
        Assert.assertTrue("Children of -root- should contain Data Dictionary",
                children.stream().anyMatch(node -> "Data Dictionary".equals(node.getName())));
        Assert.assertTrue("Children of -root- should contain Shared", children.stream().anyMatch(node -> "Shared".equals(node.getName())));

        Assert.assertNull("Source should not be included unless requested", rootChildren.getSource());
    }

    @Test
    public void DataDictionaryChildrenWithSource()
    {
        final PaginatedNodeChildrenList rootChildren = this.nodesAPI.getChildren("-root-", "Data Dictionary", true);
        final List<ChildNodeResponseEntity> children = rootChildren.getEntries();
        Assert.assertNotNull("Pagination info should always be provided", rootChildren.getPagination());
        Assert.assertEquals(rootChildren.getPagination().getCount(), children.size());

        Assert.assertFalse("Children of Data Dictionary should not be empty", children.isEmpty());
        Assert.assertTrue("Children of Data Dictionary should contain Messages",
                children.stream().anyMatch(node -> "Messages".equals(node.getName())));
        Assert.assertTrue("Children of Data Dictionary should contain Models",
                children.stream().anyMatch(node -> "Models".equals(node.getName())));

        Assert.assertNotNull("Source should be included since it was requested", rootChildren.getSource());
        Assert.assertEquals("Data Dictionary", rootChildren.getSource().getName());
        Assert.assertEquals("cm:folder", rootChildren.getSource().getNodeType());
    }

    private void validateRootNode(final NodeResponseEntity rootNode)
    {
        this.validateCoreNodeData(rootNode, "Company Home", "cm:folder");

        Assert.assertEquals("admin", rootNode.getCreatedByUser().getId());
        Assert.assertEquals("Administrator", rootNode.getCreatedByUser().getDisplayName());

        Assert.assertEquals("admin", rootNode.getModifiedByUser().getId());
        Assert.assertEquals("Administrator", rootNode.getModifiedByUser().getDisplayName());
    }

    private void validateCoreNodeData(final NodeResponseEntity node, final String expectedNodeName, final String expectedNodeType,
            final String... expectedAspectNames)
    {
        Assert.assertNotNull("ID must be loaded", node.getId());
        Assert.assertEquals(expectedNodeName, node.getName());
        Assert.assertEquals(expectedNodeType, node.getNodeType());
        Assert.assertNotNull("Aspect names must be loaded", node.getAspectNames());
        Assert.assertFalse("Aspect names must not be empty (technical aspects always included)", node.getAspectNames().isEmpty());

        for (final String expectedAspectName : expectedAspectNames)
        {
            Assert.assertTrue("Expected aspect " + expectedAspectName + " must be set", node.getAspectNames().contains(expectedAspectName));
        }

        Assert.assertNotNull("Properties must be loaded", node.getProperties());
        Assert.assertFalse("Properties must not be empty (technical properties always included)", node.getProperties().isEmpty());

        Assert.assertNotNull("createdAt must be loaded", node.getCreatedAt());
        Assert.assertNotNull("createdByUser must be loaded", node.getCreatedByUser());

        Assert.assertNotNull("modifiedAt must be loaded", node.getModifiedAt());
        Assert.assertNotNull("modifiedByUser must be loaded", node.getModifiedByUser());
    }
}
