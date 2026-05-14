/*
 * Copyright 2019 - 2026 Acosix GmbH
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

import de.acosix.alfresco.rest.client.model.search.PaginatedSearchList;
import de.acosix.alfresco.rest.client.model.search.SearchRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

/**
 * Instances of this API provide operations to execute search queries.
 *
 * @author Walid Maadi
 *
 * @since 24/08/2022 - 10:17
 */
@Path("/api/-default-/public/search/versions/1")
public interface SearchV1
{

    /**
     * Execute a search query using alfresco V1 rest api 'search'
     *
     * @param queryRequest
     *     the query body containing query parameters
     * @return the list of result entries
     */
    @Path("/search")
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    PaginatedSearchList search(SearchRequest queryRequest);
}
