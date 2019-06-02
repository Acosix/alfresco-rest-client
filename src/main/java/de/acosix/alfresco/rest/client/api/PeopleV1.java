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

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import de.acosix.alfresco.rest.client.model.common.MultiValuedParam;
import de.acosix.alfresco.rest.client.model.common.PaginatedList;
import de.acosix.alfresco.rest.client.model.common.Sort;
import de.acosix.alfresco.rest.client.model.nodes.NodeSortField;
import de.acosix.alfresco.rest.client.model.people.CommonPersonDetails;
import de.acosix.alfresco.rest.client.model.people.PersonRequestEntity;
import de.acosix.alfresco.rest.client.model.people.PersonResponseEntity;

/**
 * Instances of this API provide operations to work with people / user accounts.
 *
 * @author Axel Faust
 */
// cannot use actual base URL //api/-default-/public/alfresco/versions/1/people as getPeople / createPerson maps on this without additional
// path elements
@Path("/api/-default-/public/alfresco/versions/1")
public interface PeopleV1
{

    /**
     * This enumeration specifies the optional fields to include in a tailored response.
     *
     * @author Axel Faust
     */
    public static enum IncludeOption
    {
        /**
         * Include {@link CommonPersonDetails#getProperties() properties}
         */
        PROPERTIES("properties"),
        /**
         * Include {@link CommonPersonDetails#getAspectNames() aspect names}
         */
        ASPECT_NAMES("aspectNames"),
        /**
         * Include {@link CommonPersonDetails#getCapabilities() capabilities}
         */
        CAPABILITIES("capabilities");

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
     * Retrieves the list of people / user accounts from the Alfresco instance, using server-side defaults for pagination and sort order.
     *
     * @return the list of people / user accounts
     */
    @GET
    @Produces("application/json")
    @Path("/people")
    PaginatedList<PersonResponseEntity> getPeople();

    /**
     * Retrieves the list of people / user accounts from the Alfresco instance, using server-side defaults for pagination and sort order.
     *
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the list of people / user accounts
     */
    @GET
    @Produces("application/json")
    @Path("/people")
    PaginatedList<PersonResponseEntity> getPeople(@QueryParam("include") MultiValuedParam<IncludeOption> include,
            @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Retrieves the list of people / user accounts from the Alfresco instance using the provided pagination and sort order.
     *
     * @param skipCount
     *            the number of people matching the retrieval condition(s) to skip
     * @param maxItems
     *            the maximum number of people to retrieve
     * @param orderBy
     *            the sort order for people to use
     * @return the list of people / user accounts
     */
    @GET
    @Produces("application/json")
    @Path("/people")
    PaginatedList<PersonResponseEntity> getPeople(@QueryParam("skipCount") int skipCount, @QueryParam("maxItems") int maxItems,
            @QueryParam("orderBy") MultiValuedParam<Sort<NodeSortField>> orderBy);

    /**
     * Retrieves the list of people / user accounts from the Alfresco instance using the provided pagination and sort order.
     *
     * @param skipCount
     *            the number of people matching the retrieval condition(s) to skip
     * @param maxItems
     *            the maximum number of people to retrieve
     * @param orderBy
     *            the sort order for people to use
     * @param include
     *            the list of optional fields / information to include in the response
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the list of people / user accounts
     */
    @GET
    @Produces("application/json")
    @Path("/people")
    PaginatedList<PersonResponseEntity> getPeople(@QueryParam("skipCount") int skipCount, @QueryParam("maxItems") int maxItems,
            @QueryParam("orderBy") MultiValuedParam<Sort<NodeSortField>> orderBy,
            @QueryParam("include") MultiValuedParam<IncludeOption> include, @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Creates a new person / user account.
     *
     * @return the created person / user account
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/people")
    PersonResponseEntity createPerson(PersonRequestEntity person);

    /**
     * Creates a new person / user account.
     *
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the created person / user account
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/people")
    PersonResponseEntity createPerson(PersonRequestEntity person, @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Retrieves a person / user account.
     *
     * @param personId
     *            the ID of the person to retrieve - supports the pseudo ID {@code -me-}
     *
     * @return the person / user account
     */
    @GET
    @Produces("application/json")
    @Path("/people/{personId}")
    PersonResponseEntity getPerson(@PathParam("personId") String personId);

    /**
     * Retrieves a person / user account.
     *
     * @param personId
     *            the ID of the person to retrieve - supports the pseudo ID {@code -me-}
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     *
     * @return the person / user account
     */
    @GET
    @Produces("application/json")
    @Path("/people/{personId}")
    PersonResponseEntity getPerson(@PathParam("personId") String personId, @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Updates a person / user account.
     *
     * @param personId
     *            the ID of the person to update - supports the pseudo ID {@code -me-}
     * @param person
     *            the details of the person to update
     *
     * @return the person / user account
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/people/{personId}")
    PersonResponseEntity updatePerson(@PathParam("personId") String personId, PersonRequestEntity person);

    /**
     * Updates a person / user account.
     *
     * @param personId
     *            the ID of the person to update - supports the pseudo ID {@code -me-}
     * @param person
     *            the details of the person to update
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     *
     * @return the person / user account
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/people/{personId}")
    PersonResponseEntity updatePerson(@PathParam("personId") String personId, PersonRequestEntity person,
            @QueryParam("fields") MultiValuedParam<String> fields);
}
