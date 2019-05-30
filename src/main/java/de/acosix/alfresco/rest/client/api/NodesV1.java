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
package de.acosix.alfresco.rest.client.api;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import de.acosix.alfresco.rest.client.model.common.MultiValuedParam;
import de.acosix.alfresco.rest.client.model.common.PaginatedList;
import de.acosix.alfresco.rest.client.model.common.Sort;
import de.acosix.alfresco.rest.client.model.nodes.ChildNodeResponseEntity;
import de.acosix.alfresco.rest.client.model.nodes.CommonNodeEntity;
import de.acosix.alfresco.rest.client.model.nodes.NodeCreationRequestEntity;
import de.acosix.alfresco.rest.client.model.nodes.NodeLockRequestEntity;
import de.acosix.alfresco.rest.client.model.nodes.NodeResponseEntity;
import de.acosix.alfresco.rest.client.model.nodes.NodeSortField;
import de.acosix.alfresco.rest.client.model.nodes.PaginatedNodeChildrenList;
import de.acosix.alfresco.rest.client.model.nodes.PermissionsInfo;

/**
 * Instances of this API provide operations to work with nodes.
 *
 * @author Axel Faust
 */
@Path("/api/-default-/public/alfresco/versions/1/nodes")
public interface NodesV1
{

    /**
     * This enumeration specifies the optional fields to include in a tailored response.
     *
     * @author Axel Faust
     */
    public static enum IncludeOption
    {
        /**
         * Include {@link NodeResponseEntity#getAllowableOperations() allowable operations}
         */
        ALLOWABLE_OPERATIONS("allowableOperations"),
        /**
         * Include {@link ChildNodeResponseEntity#getAssociation() association}
         */
        ASSOCIATION("association"),
        /**
         * Include {@link NodeResponseEntity#getIsLink() isLink}
         */
        IS_LINK("isLink"),
        /**
         * Include {@link NodeResponseEntity#getIsFavorite() isFavorite}
         */
        IS_FAVORITE("isFavorite"),
        /**
         * Include {@link NodeResponseEntity#getIsLocked() isLocked}
         */
        IS_LOCKED("isLocked"),
        /**
         * Include {@link NodeResponseEntity#getPath() path information}
         */
        PATH("path"),
        /**
         * Include {@link NodeResponseEntity#getPermissions() permissions}
         */
        PERMISSIONS("permissions");

        private final String simpleName;

        private IncludeOption(final String simpleName)
        {
            this.simpleName = simpleName;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public String toString()
        {
            return simpleName;
        }
    }

    /**
     * Retrieves data about a particular node.
     *
     * @param nodeId
     *            the ID of the node to retrieve - supports the pseudo IDs {@code -root-}, {@code -shared-} and {@code -my-}
     *
     * @return the retrieved node in the detail requested
     */
    @GET
    @Path("/{nodeId}")
    @Produces("application/json")
    NodeResponseEntity getNode(@PathParam("nodeId") String nodeId);

    /**
     * Retrieves data about a particular node.
     *
     * @param nodeId
     *            the ID of the node to retrieve - supports the pseudo IDs {@code -root-}, {@code -shared-} and {@code -my-}
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the retrieved node in the detail requested
     */
    @GET
    @Path("/{nodeId}")
    @Produces("application/json")
    NodeResponseEntity getNode(@PathParam("nodeId") String nodeId, @QueryParam("include") MultiValuedParam<IncludeOption> include,
            @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Retrieves data about a particular node using a reference node and relative lookup path.
     *
     * @param nodeId
     *            the ID of the node from which to retrieve a node by relative path - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param relativePath
     *            the relative path from the specified node with which to lookup the node to retrieve
     *
     * @return the retrieved node in the detail requested
     */
    @GET
    @Path("/{nodeId}")
    @Produces("application/json")
    NodeResponseEntity getNode(@PathParam("nodeId") String nodeId, @QueryParam("relativePath") String relativePath);

    /**
     * Retrieves data about a particular node using a reference node and relative lookup path.
     *
     * @param nodeId
     *            the ID of the node from which to retrieve a node by relative path - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param relativePath
     *            the relative path from the specified node with which to lookup the node to retrieve
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the retrieved node in the detail requested
     */
    @GET
    @Path("/{nodeId}")
    @Produces("application/json")
    NodeResponseEntity getNode(@PathParam("nodeId") String nodeId, @QueryParam("relativePath") String relativePath,
            @QueryParam("include") MultiValuedParam<IncludeOption> include, @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Deletes a particular node (and its primary children/descendants) and moves it into the archive / trash can.
     *
     * @param nodeId
     *            the ID of the node to delete
     *
     */
    @DELETE
    @Path("/{nodeId}")
    void deleteNode(@PathParam("nodeId") String nodeId);

    /**
     * Deletes a particular node (and its primary children/descendants).
     *
     * @param nodeId
     *            the ID of the node to delete
     * @param permanent
     *            {@code true} if the node should be deleted permanently (not moved into the archive / trash can), {@code false} otherwise
     *
     */
    @DELETE
    @Path("/{nodeId}")
    void deleteNode(@PathParam("nodeId") String nodeId, @QueryParam("permanent") boolean permanent);

    /**
     * Updates a particular node.
     *
     * @param nodeId
     *            the ID of the node to update - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param updates
     *            the updates to apply to the node; any members / properties of the provided bean that is not null will be processed,
     *            typically fully overriding the current values (exception: {@code properties}), so the caller is required to first retrieve
     *            and merge existing values (e.g. aspectNames) which should not be overwritten entirely
     *
     * @return the updated node in the detail requested
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{nodeId}")
    NodeResponseEntity updateNode(@PathParam("nodeId") String nodeId, CommonNodeEntity<PermissionsInfo> updates);

    /**
     * Updates a particular node.
     *
     * @param nodeId
     *            the ID of the node to update - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param updates
     *            the updates to apply to the node; any members / properties of the provided bean that is not null will be processed,
     *            typically fully overriding the current values (exception: {@code properties}), so the caller is required to first retrieve
     *            and merge existing values (e.g. aspectNames) which should not be overwritten entirely
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     *
     * @return the updated node in the detail requested
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{nodeId}")
    NodeResponseEntity updateNode(@PathParam("nodeId") String nodeId, CommonNodeEntity<PermissionsInfo> updates,
            @QueryParam("include") MultiValuedParam<IncludeOption> include, @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Retrieves a list of children of a specified node.
     *
     * @param nodeId
     *            the ID of the node from which to retrieve the children - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @return the list of children of the specified node
     */
    @GET
    @Produces("application/json")
    @Path("/{nodeId}/children")
    PaginatedNodeChildrenList getChildren(@PathParam("nodeId") String nodeId);

    /**
     * Retrieves a list of children of a specified node.
     *
     * @param nodeId
     *            the ID of the node from which to retrieve the children - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the list of children of the specified node
     */
    @GET
    @Produces("application/json")
    @Path("/{nodeId}/children")
    PaginatedNodeChildrenList getChildren(@PathParam("nodeId") String nodeId,
            @QueryParam("include") MultiValuedParam<IncludeOption> include, @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Retrieves a list of children of a resolved node.
     *
     * @param nodeId
     *            the ID of the node from which to resolve the source node by relative path - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param relativePath
     *            the relative path from the specified node with which to lookup the source node for which to retrieve the children
     * @param includeSource
     *            {@code true} if the resolved source node should be included in the response, {@code false} otherwise
     * @return the list of children of the resolved node
     */
    @GET
    @Produces("application/json")
    @Path("/{nodeId}/children")
    PaginatedNodeChildrenList getChildren(@PathParam("nodeId") String nodeId, @QueryParam("relativePath") String relativePath,
            @QueryParam("includeSource") boolean includeSource);

    /**
     * Retrieves a list of children of a resolved node.
     *
     * @param nodeId
     *            the ID of the node from which to resolve the source node by relative path - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param relativePath
     *            the relative path from the specified node with which to lookup the source node for which to retrieve the children
     * @param includeSource
     *            {@code true} if the resolved source node should be included in the response, {@code false} otherwise
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the list of children of the resolved node
     */
    @GET
    @Produces("application/json")
    @Path("/{nodeId}/children")
    PaginatedNodeChildrenList getChildren(@PathParam("nodeId") String nodeId, @QueryParam("relativePath") String relativePath,
            @QueryParam("includeSource") boolean includeSource, @QueryParam("include") MultiValuedParam<IncludeOption> include,
            @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Retrieves a list of children of a specified node.
     *
     * @param nodeId
     *            the ID of the node from which to retrieve the children - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param where
     *            a conditional filter to be applied to the children of the node
     * @return the list of children of the specified node
     */
    @GET
    @Produces("application/json")
    @Path("/{nodeId}/children")
    PaginatedNodeChildrenList getChildren(@PathParam("nodeId") String nodeId, @QueryParam("where") String where);

    /**
     * Retrieves a list of children of a specified node.
     *
     * @param nodeId
     *            the ID of the node from which to retrieve the children - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param where
     *            a conditional filter to be applied to the children of the node
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the list of children of the specified node
     */
    @GET
    @Produces("application/json")
    @Path("/{nodeId}/children")
    PaginatedNodeChildrenList getChildren(@PathParam("nodeId") String nodeId, @QueryParam("where") String where,
            @QueryParam("include") MultiValuedParam<IncludeOption> include, @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Retrieves a list of children of a resolved node.
     *
     * @param nodeId
     *            the ID of the node from which to resolve the source node by relative path - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param relativePath
     *            the relative path from the specified node with which to lookup the source node for which to retrieve the children
     * @param includeSource
     *            {@code true} if the resolved source node should be included in the response, {@code false} otherwise
     * @param where
     *            a conditional filter to be applied to the children of the node
     * @return the list of children of the resolved node
     */
    @GET
    @Produces("application/json")
    @Path("/{nodeId}/children")
    PaginatedNodeChildrenList getChildren(@PathParam("nodeId") String nodeId, @QueryParam("relativePath") String relativePath,
            @QueryParam("includeSource") boolean includeSource, @QueryParam("where") String where);

    /**
     * Retrieves a list of children of a resolved node.
     *
     * @param nodeId
     *            the ID of the node from which to resolve the source node by relative path - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param relativePath
     *            the relative path from the specified node with which to lookup the source node for which to retrieve the children
     * @param includeSource
     *            {@code true} if the resolved source node should be included in the response, {@code false} otherwise
     * @param where
     *            a conditional filter to be applied to the children of the node
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the list of children of the resolved node
     */
    @GET
    @Produces("application/json")
    @Path("/{nodeId}/children")
    PaginatedNodeChildrenList getChildren(@PathParam("nodeId") String nodeId, @QueryParam("relativePath") String relativePath,
            @QueryParam("includeSource") boolean includeSource, @QueryParam("where") String where,
            @QueryParam("include") MultiValuedParam<IncludeOption> include, @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Retrieves a list of children of a specified node.
     *
     * @param nodeId
     *            the ID of the node from which to retrieve the children - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param skipCount
     *            the number of children matching the retrieval condition(s) to skip
     * @param maxItems
     *            the maximum number of children to retrieve
     * @param orderBy
     *            the sort order for children to use
     * @return the list of children of the specified node
     */
    @GET
    @Produces("application/json")
    @Path("/{nodeId}/children")
    PaginatedNodeChildrenList getChildren(@PathParam("nodeId") String nodeId, @QueryParam("skipCount") int skipCount,
            @QueryParam("maxItems") int maxItems, @QueryParam("orderBy") MultiValuedParam<Sort<NodeSortField>> orderBy);

    /**
     * Retrieves a list of children of a resolved node.
     *
     * @param nodeId
     *            the ID of the node from which to resolve the source node by relative path - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param relativePath
     *            the relative path from the specified node with which to lookup the source node for which to retrieve the children
     * @param includeSource
     *            {@code true} if the resolved source node should be included in the response, {@code false} otherwise
     * @param skipCount
     *            the number of children matching the retrieval condition(s) to skip
     * @param maxItems
     *            the maximum number of children to retrieve
     * @param orderBy
     *            the sort order for children to use
     * @return the list of children of the resolved node
     */
    @GET
    @Produces("application/json")
    @Path("/{nodeId}/children")
    PaginatedNodeChildrenList getChildren(@PathParam("nodeId") String nodeId, @QueryParam("relativePath") String relativePath,
            @QueryParam("includeSource") boolean includeSource, @QueryParam("skipCount") int skipCount,
            @QueryParam("maxItems") int maxItems, @QueryParam("orderBy") MultiValuedParam<Sort<NodeSortField>> orderBy);

    /**
     * Retrieves a list of children of a resolved node.
     *
     * @param nodeId
     *            the ID of the node from which to resolve the source node by relative path - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param relativePath
     *            the relative path from the specified node with which to lookup the source node for which to retrieve the children
     * @param includeSource
     *            {@code true} if the resolved source node should be included in the response, {@code false} otherwise
     * @param skipCount
     *            the number of children matching the retrieval condition(s) to skip
     * @param maxItems
     *            the maximum number of children to retrieve
     * @param orderBy
     *            the sort order for children to use
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the list of children of the resolved node
     */
    @GET
    @Produces("application/json")
    @Path("/{nodeId}/children")
    PaginatedNodeChildrenList getChildren(@PathParam("nodeId") String nodeId, @QueryParam("relativePath") String relativePath,
            @QueryParam("includeSource") boolean includeSource, @QueryParam("skipCount") int skipCount,
            @QueryParam("maxItems") int maxItems, @QueryParam("orderBy") MultiValuedParam<Sort<NodeSortField>> orderBy,
            @QueryParam("include") MultiValuedParam<IncludeOption> include, @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Retrieves a list of children of a specified node.
     *
     * @param nodeId
     *            the ID of the node from which to retrieve the children - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param where
     *            a conditional filter to be applied to the children of the node
     * @param skipCount
     *            the number of children matching the retrieval condition(s) to skip
     * @param maxItems
     *            the maximum number of children to retrieve
     * @param orderBy
     *            the sort order for children to use
     * @return the list of children of the specified node
     */
    @GET
    @Produces("application/json")
    @Path("/{nodeId}/children")
    PaginatedNodeChildrenList getChildren(@PathParam("nodeId") String nodeId, @QueryParam("where") String where,
            @QueryParam("skipCount") int skipCount, @QueryParam("maxItems") int maxItems,
            @QueryParam("orderBy") MultiValuedParam<Sort<NodeSortField>> orderBy);

    /**
     * Retrieves a list of children of a specified node.
     *
     * @param nodeId
     *            the ID of the node from which to retrieve the children - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param where
     *            a conditional filter to be applied to the children of the node
     * @param skipCount
     *            the number of children matching the retrieval condition(s) to skip
     * @param maxItems
     *            the maximum number of children to retrieve
     * @param orderBy
     *            the sort order for children to use
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the list of children of the specified node
     */
    @GET
    @Produces("application/json")
    @Path("/{nodeId}/children")
    PaginatedNodeChildrenList getChildren(@PathParam("nodeId") String nodeId, @QueryParam("where") String where,
            @QueryParam("skipCount") int skipCount, @QueryParam("maxItems") int maxItems,
            @QueryParam("orderBy") MultiValuedParam<Sort<NodeSortField>> orderBy,
            @QueryParam("include") MultiValuedParam<IncludeOption> include, @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Retrieves a list of children of a resolved node.
     *
     * @param nodeId
     *            the ID of the node from which to resolve the source node by relative path - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param relativePath
     *            the relative path from the specified node with which to lookup the source node for which to retrieve the children
     * @param includeSource
     *            {@code true} if the resolved source node should be included in the response, {@code false} otherwise
     * @param where
     *            a conditional filter to be applied to the children of the node
     * @param skipCount
     *            the number of children matching the retrieval condition(s) to skip
     * @param maxItems
     *            the maximum number of children to retrieve
     * @param orderBy
     *            the sort order for children to use
     * @return the list of children of the resolved node
     */
    @GET
    @Produces("application/json")
    @Path("/{nodeId}/children")
    PaginatedNodeChildrenList getChildren(@PathParam("nodeId") String nodeId, @QueryParam("relativePath") String relativePath,
            @QueryParam("includeSource") boolean includeSource, @QueryParam("where") String where, @QueryParam("skipCount") int skipCount,
            @QueryParam("maxItems") int maxItems, @QueryParam("orderBy") MultiValuedParam<Sort<NodeSortField>> orderBy);

    /**
     * Retrieves a list of children of a resolved node.
     *
     * @param nodeId
     *            the ID of the node from which to resolve the source node by relative path - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param relativePath
     *            the relative path from the specified node with which to lookup the source node for which to retrieve the children
     * @param includeSource
     *            {@code true} if the resolved source node should be included in the response, {@code false} otherwise
     * @param where
     *            a conditional filter to be applied to the children of the node
     * @param skipCount
     *            the number of children matching the retrieval condition(s) to skip
     * @param maxItems
     *            the maximum number of children to retrieve
     * @param orderBy
     *            the sort order for children to use
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the list of children of the resolved node
     */
    @GET
    @Produces("application/json")
    @Path("/{nodeId}/children")
    PaginatedNodeChildrenList getChildren(@PathParam("nodeId") String nodeId, @QueryParam("relativePath") String relativePath,
            @QueryParam("includeSource") boolean includeSource, @QueryParam("where") String where, @QueryParam("skipCount") int skipCount,
            @QueryParam("maxItems") int maxItems, @QueryParam("orderBy") MultiValuedParam<Sort<NodeSortField>> orderBy,
            @QueryParam("include") MultiValuedParam<IncludeOption> include, @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Creates a content-less node.
     *
     * @param nodeId
     *            the ID of the node below which to create the new node - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param nodeToCreate
     *            the requested structure of the node to create
     * @return the details of the created node
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{nodeId}/children")
    NodeResponseEntity createNode(@PathParam("nodeId") String nodeId, NodeCreationRequestEntity nodeToCreate);

    /**
     * Creates a content-less node.
     *
     * @param nodeId
     *            the ID of the node below which to create the new node - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param nodeToCreate
     *            the requested structure of the node to create
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the details of the created node
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{nodeId}/children")
    NodeResponseEntity createNode(@PathParam("nodeId") String nodeId, NodeCreationRequestEntity nodeToCreate,
            @QueryParam("include") MultiValuedParam<IncludeOption> include, @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Creates a content-less node.
     *
     * @param nodeId
     *            the ID of the node below which to create the new node - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param nodeToCreate
     *            the requested structure of the node to create
     * @param autoRename
     *            {@code true} if the node should be auto-renamed (from the requested name) in order to prevent name-clashes, {@code false}
     *            otherwise
     * @return the details of the created node
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{nodeId}/children")
    NodeResponseEntity createNode(@PathParam("nodeId") String nodeId, NodeCreationRequestEntity nodeToCreate,
            @QueryParam("autoRename") boolean autoRename);

    /**
     * Creates a content-less node.
     *
     * @param nodeId
     *            the ID of the node below which to create the new node - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param nodeToCreate
     *            the requested structure of the node to create
     * @param autoRename
     *            {@code true} if the node should be auto-renamed (from the requested name) in order to prevent name-clashes, {@code false}
     *            otherwise
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the details of the created node
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{nodeId}/children")
    NodeResponseEntity createNode(@PathParam("nodeId") String nodeId, NodeCreationRequestEntity nodeToCreate,
            @QueryParam("autoRename") boolean autoRename, @QueryParam("include") MultiValuedParam<IncludeOption> include,
            @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Creates multiple content-less nodes.
     *
     * @param nodeId
     *            the ID of the node below which to create the new nodes - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param nodesToCreate
     *            the requested structure of the nodes to create
     * @return the details of the created nodes
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{nodeId}/children")
    PaginatedList<NodeResponseEntity> createNodes(@PathParam("nodeId") String nodeId, List<NodeCreationRequestEntity> nodesToCreate);

    /**
     * Creates multiple content-less nodes.
     *
     * @param nodeId
     *            the ID of the node below which to create the new nodes - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param nodesToCreate
     *            the requested structure of the nodes to create
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the details of the created nodes
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{nodeId}/children")
    PaginatedList<NodeResponseEntity> createNodes(@PathParam("nodeId") String nodeId, List<NodeCreationRequestEntity> nodesToCreate,
            @QueryParam("include") MultiValuedParam<IncludeOption> include, @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Creates multiple content-less nodes.
     *
     * @param nodeId
     *            the ID of the node below which to create the new nodes - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param nodesToCreate
     *            the requested structure of the nodes to create
     * @param autoRename
     *            {@code true} if the node should be auto-renamed (from the requested name) in order to prevent name-clashes, {@code false}
     *            otherwise
     * @return the details of the created nodes
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{nodeId}/children")
    PaginatedList<NodeResponseEntity> createNodes(@PathParam("nodeId") String nodeId, List<NodeCreationRequestEntity> nodesToCreate,
            @QueryParam("autoRename") boolean autoRename);

    /**
     * Creates multiple content-less nodes.
     *
     * @param nodeId
     *            the ID of the node below which to create the new nodes - supports the pseudo IDs {@code -root-},
     *            {@code -shared-} and {@code -my-}
     * @param nodesToCreate
     *            the requested structure of the nodes to create
     * @param autoRename
     *            {@code true} if the node should be auto-renamed (from the requested name) in order to prevent name-clashes, {@code false}
     *            otherwise
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the details of the created nodes
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{nodeId}/children")
    PaginatedList<NodeResponseEntity> createNodes(@PathParam("nodeId") String nodeId, List<NodeCreationRequestEntity> nodesToCreate,
            @QueryParam("autoRename") boolean autoRename, @QueryParam("include") MultiValuedParam<IncludeOption> include,
            @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Retrieves the content (restricted to cm:content property) of the specified node.
     *
     * @param nodeId
     *            the node from which to retrieve the content
     * @return the response object allowing access to the raw content
     */
    @GET
    @Path("/{nodeId}/content")
    Response getContent(@PathParam("nodeId") String nodeId);

    /**
     * Retrieves the content (restricted to cm:content property) of the specified node.
     *
     * @param nodeId
     *            the node from which to retrieve the content
     * @param ifModifiedSince
     *            a textual representation of a timestamp to limit this operation to only retrieve content if the node was modified since
     *            the specified time - this parameter must comply to the value syntax of the HTTP header "If-Modified-Since"
     * @param range
     *            the textual representation of one or many byte ranges to retrieve one or many partial chunks of the content - this
     *            parameter must comply to the value syntax of the HTTP header "Range"
     * @return the response object allowing access to the raw content
     */
    @GET
    @Path("/{nodeId}/content")
    Response getContent(@PathParam("nodeId") String nodeId, @HeaderParam("If-Modified-Since") String ifModifiedSince,
            @HeaderParam("Range") String range);

    /**
     * Sets the content (restricted to cm:content property) of the specified node. This operation never explicitly creates a new version,
     * though a version may be created by server-side automation / behaviours.
     *
     * @param nodeId
     *            the node for which to set the content
     * @param content
     *            the raw stream to provide the content
     * @param contentType
     *            the mimetype of the content - this parameter must comply with the value syntax of the HTTP header "Content-Type"
     * @return the details of the updated node
     */
    @PUT
    @Consumes("application/octet-stream")
    @Produces("application/json")
    @Path("/{nodeId}/content")
    NodeResponseEntity setContent(@PathParam("nodeId") String nodeId, InputStream content, @HeaderParam("Content-Type") String contentType);

    /**
     * Sets the content (restricted to cm:content property) of the specified node. This operation never explicitly creates a new version,
     * though a version may be created by server-side automation / behaviours.
     *
     * @param nodeId
     *            the node for which to set the content
     * @param content
     *            the raw stream to provide the content
     * @param contentType
     *            the mimetype of the content - this parameter must comply with the value syntax of the HTTP header "Content-Type"
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the details of the updated node
     */
    @PUT
    @Consumes("application/octet-stream")
    @Produces("application/json")
    @Path("/{nodeId}/content")
    NodeResponseEntity setContent(@PathParam("nodeId") String nodeId, InputStream content, @HeaderParam("Content-Type") String contentType,
            @QueryParam("include") MultiValuedParam<IncludeOption> include, @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Sets the content (restricted to cm:content property) of the specified node. This operation never explicitly creates a new version,
     * though a version may be created by server-side automation / behaviours.
     *
     * @param nodeId
     *            the node for which to set the content
     * @param content
     *            the raw stream to provide the content
     * @param contentType
     *            the mimetype of the content - this parameter must comply with the value syntax of the HTTP header "Content-Type"
     * @param name
     *            the new name of the node to combine the content update operation with a rename operation
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the details of the updated node
     */
    @PUT
    @Consumes("application/octet-stream")
    @Produces("application/json")
    @Path("/{nodeId}/content")
    NodeResponseEntity setContent(@PathParam("nodeId") String nodeId, InputStream content, @HeaderParam("Content-Type") String contentType,
            @QueryParam("name") String name, @QueryParam("include") MultiValuedParam<IncludeOption> include,
            @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Sets the content (restricted to cm:content property) of the specified node. This operation always creates a new version, the type of
     * which can be determined via the {@code majorVersion} parameter.
     *
     * @param nodeId
     *            the node for which to set the content
     * @param content
     *            the raw stream to provide the content
     * @param contentType
     *            the mimetype of the content - this parameter must comply with the value syntax of the HTTP header "Content-Type"
     * @param name
     *            the new name of the node to combine the content update operation with a rename operation
     * @param majorVersion
     *            {@code true} if this operation should result in the creation of a major version, {@code false} if it should create a minor
     *            version
     * @param comment
     *            the comment to set in the new version
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the details of the updated node
     */
    @PUT
    @Consumes("application/octet-stream")
    @Produces("application/json")
    @Path("/{nodeId}/content")
    NodeResponseEntity setContent(@PathParam("nodeId") String nodeId, InputStream content, @HeaderParam("Content-Type") String contentType,
            @QueryParam("name") String name, @QueryParam("majorVersion") boolean majorVersion, @QueryParam("comment") String comment,
            @QueryParam("include") MultiValuedParam<IncludeOption> include, @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Locks a (content) node.
     *
     * @param nodeId
     *            the ID of the node to lock
     * @param nodeLock
     *            the details of the lock to apply
     * @return the details of the locked node
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{nodeId}/lock")
    NodeResponseEntity lockNode(@PathParam("nodeId") String nodeId, NodeLockRequestEntity nodeLock);

    /**
     * Locks a (content) node.
     *
     * @param nodeId
     *            the ID of the node to lock
     * @param nodeLock
     *            the details of the lock to apply
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the details of the locked node
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{nodeId}/lock")
    NodeResponseEntity lockNode(@PathParam("nodeId") String nodeId, NodeLockRequestEntity nodeLock,
            @QueryParam("include") MultiValuedParam<IncludeOption> include, @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Unlocks a (content) node.
     *
     * @param nodeId
     *            the ID of the node to lock
     * @return the details of the node
     */
    @POST
    @Produces("application/json")
    @Path("/{nodeId}/unlock")
    NodeResponseEntity unlockNode(@PathParam("nodeId") String nodeId);

    /**
     * Unlocks a (content) node.
     *
     * @param nodeId
     *            the ID of the node to lock
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the details of the node
     */
    @POST
    @Produces("application/json")
    @Path("/{nodeId}/unlock")
    NodeResponseEntity unlockNode(@PathParam("nodeId") String nodeId, @QueryParam("include") MultiValuedParam<IncludeOption> include,
            @QueryParam("fields") MultiValuedParam<String> fields);
}
