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

import de.acosix.alfresco.rest.client.model.common.MultiValuedParam;
import de.acosix.alfresco.rest.client.model.nodes.ChildNodeResponseEntity;
import de.acosix.alfresco.rest.client.model.nodes.NodeResponseEntity;
import de.acosix.alfresco.rest.client.model.nodes.PaginatedNodeChildrenList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Instances of this API provide operations to work with queries: nodes.
 *
 * @author Kanagat Nugusbayev
 */
@Path("/api/-default-/public/alfresco/versions/1/queries")
public interface QueriesV1 {

    /**
     * Note: this endpoint is available in Alfresco 5.2 and newer versions.
     * <p>
     * Gets a list of nodes that match the given search criteria.
     * <p>
     * The search term is used to look for nodes that match against name, title, description, full text content or tags.
     * <p>
     * The search term:
     * <p>
     * - must contain a minimum of 3 alphanumeric characters
     * - allows "quoted term"
     * - can optionally use '*' for wildcard matching
     * By default, file and folder types will be searched unless a specific type is provided as a query parameter.
     * <p>
     * By default, the search will be across the repository unless a specific root node id is provided to start the search from.
     * <p>
     * You can sort the result list using the orderBy parameter. You can specify one or more of the following fields in the orderBy parameter:
     * <p>
     * - name
     * - modifiedAt
     * - createdAt
     *
     * @param term       - The term to search for.
     * @param rootNodeId - The id of the node to start the search from. Supports the aliases -my-, -root- and -shared-.
     * @param skipCount  - The number of entities that exist in the collection before those included in this list. If not supplied then the default value is 0.
     * @param maxItems   - The maximum number of items to return in the list. If not supplied then the default value is 100.
     * @param nodeType   - Restrict the returned results to only those of the given node type and its sub-types
     * @param include    - Returns additional information about the node. The following optional fields can be requested:
     *                   allowableOperations
     *                   aspectNames
     *                   isLink
     *                   isFavorite
     *                   isLocked
     *                   path
     *                   properties
     * @param orderBy    - A string to control the order of the entities returned in a list. To sort the entities in a specific order,
     *                   you can use the ASC and DESC keywords for any field.
     * @param fields     - A list of field names.
     * @return the retrieved node in the detail requested
     */
    @GET
    @Path("/nodes")
    @Produces("application/json")
    PaginatedNodeChildrenList queryNodes(@QueryParam("term") String term,
                                      @QueryParam("rootNodeId") String rootNodeId,
                                      @QueryParam("skipCount") Integer skipCount,
                                      @QueryParam("maxItems") Integer maxItems,
                                      @QueryParam("nodeType") String nodeType,
                                      @QueryParam("include") MultiValuedParam<String> include,
                                      @QueryParam("orderBy") MultiValuedParam<String> orderBy,
                                      @QueryParam("fields") MultiValuedParam<String> fields);

}
