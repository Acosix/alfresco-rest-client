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
import javax.ws.rs.DELETE;
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
import de.acosix.alfresco.rest.client.model.sites.SiteContainerResponseEntity;
import de.acosix.alfresco.rest.client.model.sites.SiteRelation;
import de.acosix.alfresco.rest.client.model.sites.SiteRequestEntity;
import de.acosix.alfresco.rest.client.model.sites.SiteResponseEntity;
import de.acosix.alfresco.rest.client.model.sites.SiteSortField;

/**
 * Instances of this API provide operations to work with sites and associated structures.
 *
 * @author Axel Faust
 */
// cannot use actual base URL //api/-default-/public/alfresco/versions/1/sites as getSites/ createSite maps on this without additional
// path elements
@Path("/api/-default-/public/alfresco/versions/1")
public interface SitesV1
{

    /**
     * Retrieves the list of accessible sites from the Alfresco instance, using server-side defaults for pagination and sort order.
     *
     * @return the list of sites
     */
    @GET
    @Produces("application/json")
    @Path("/sites")
    PaginatedList<SiteResponseEntity> getSites();

    /**
     * Retrieves the list of accessible sites from the Alfresco instance, using server-side defaults for pagination and sort order.
     *
     * @param where
     *            a conditional filter to be applied to restrict the list of sites
     * @return the list of sites
     */
    @GET
    @Produces("application/json")
    @Path("/sites")
    PaginatedList<SiteResponseEntity> getSites(@QueryParam("where") String where);

    /**
     * Retrieves the list of accessible sites from the Alfresco instance, using server-side defaults for pagination and sort order.
     *
     * @param relations
     *            the list of relations to secondary structures to load with the result
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the list of sites
     */
    @GET
    @Produces("application/json")
    @Path("/sites")
    PaginatedList<SiteResponseEntity> getSites(@QueryParam("relations") MultiValuedParam<SiteRelation> relations,
            @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Retrieves the list of accessible sites from the Alfresco instance, using server-side defaults for pagination and sort order.
     *
     * @param relations
     *            the list of relations to secondary structures to load with the result
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @param where
     *            a conditional filter to be applied to restrict the list of sites
     * @return the list of sites
     */
    @GET
    @Produces("application/json")
    @Path("/sites")
    PaginatedList<SiteResponseEntity> getSites(@QueryParam("relations") MultiValuedParam<SiteRelation> relations,
            @QueryParam("fields") MultiValuedParam<String> fields, @QueryParam("where") String where);

    /**
     * Retrieves the list of accessible sites from the Alfresco instance using the provided pagination and sort order.
     *
     * @param skipCount
     *            the number of sites matching the retrieval condition(s) to skip
     * @param maxItems
     *            the maximum number of sites to retrieve
     * @param orderBy
     *            the sort order for sites to use
     * @return the list of sites
     */
    @GET
    @Produces("application/json")
    @Path("/sites")
    PaginatedList<SiteResponseEntity> getSites(@QueryParam("skipCount") int skipCount, @QueryParam("maxItems") int maxItems,
            @QueryParam("orderBy") MultiValuedParam<Sort<SiteSortField>> orderBy);

    /**
     * Retrieves the list of accessible sites from the Alfresco instance using the provided pagination and sort order.
     *
     * @param skipCount
     *            the number of sites matching the retrieval condition(s) to skip
     * @param maxItems
     *            the maximum number of sites to retrieve
     * @param orderBy
     *            the sort order for sites to use
     * @param where
     *            a conditional filter to be applied to restrict the list of sites
     * @return the list of sites
     */
    @GET
    @Produces("application/json")
    @Path("/sites")
    PaginatedList<SiteResponseEntity> getSites(@QueryParam("skipCount") int skipCount, @QueryParam("maxItems") int maxItems,
            @QueryParam("orderBy") MultiValuedParam<Sort<SiteSortField>> orderBy, @QueryParam("where") String where);

    /**
     * Retrieves the list of accessible sites from the Alfresco instance using the provided pagination and sort order.
     *
     * @param skipCount
     *            the number of sites matching the retrieval condition(s) to skip
     * @param maxItems
     *            the maximum number of sites to retrieve
     * @param orderBy
     *            the sort order for sites to use
     * @param relations
     *            the list of relations to secondary structures to load with the result
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the list of sites
     */
    @GET
    @Produces("application/json")
    @Path("/sites")
    PaginatedList<SiteResponseEntity> getSites(@QueryParam("skipCount") int skipCount, @QueryParam("maxItems") int maxItems,
            @QueryParam("orderBy") MultiValuedParam<Sort<SiteSortField>> orderBy,
            @QueryParam("relations") MultiValuedParam<SiteRelation> relations, @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Retrieves the list of accessible sites from the Alfresco instance using the provided pagination and sort order.
     *
     * @param skipCount
     *            the number of sites matching the retrieval condition(s) to skip
     * @param maxItems
     *            the maximum number of sites to retrieve
     * @param orderBy
     *            the sort order for sites to use
     * @param relations
     *            the list of relations to secondary structures to load with the result
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @param where
     *            a conditional filter to be applied to restrict the list of sites
     * @return the list of sites
     */
    @GET
    @Produces("application/json")
    @Path("/sites")
    PaginatedList<SiteResponseEntity> getSites(@QueryParam("skipCount") int skipCount, @QueryParam("maxItems") int maxItems,
            @QueryParam("orderBy") MultiValuedParam<Sort<SiteSortField>> orderBy,
            @QueryParam("relations") MultiValuedParam<SiteRelation> relations, @QueryParam("fields") MultiValuedParam<String> fields,
            @QueryParam("where") String where);

    /**
     * Creates a new site.
     *
     * @param site
     *            the site to create
     *
     * @return the created site
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/sites")
    SiteResponseEntity createSite(SiteRequestEntity site);

    /**
     * Creates a new site.
     *
     * @param site
     *            the site to create
     * @param skipConfiguration
     *            {@code true} if the Share-specific configuration for the site should not be initialised, {@code false} otherwise
     * @param skipAddToFavorites
     *            {@code true} if the site should not be added to the user's list of favorite sites, {@code false} otherwise
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the created site
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/sites")
    SiteResponseEntity createSite(SiteRequestEntity site, @QueryParam("skipConfiguration") boolean skipConfiguration,
            @QueryParam("skipAddToFavorites") boolean skipAddToFavorites, @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Retrieves a site.
     *
     * @param siteId
     *            the ID of the site to retrieve
     *
     * @return the site
     */
    @GET
    @Produces("application/json")
    @Path("/sites/{siteId}")
    SiteResponseEntity getSite(@PathParam("siteId") String siteId);

    /**
     * Retrieves a site.
     *
     * @param siteId
     *            the ID of the site to retrieve
     * @param relations
     *            the list of relations to secondary structures to load with the result
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     *
     * @return the site
     */
    @GET
    @Produces("application/json")
    @Path("/sites/{siteId}")
    SiteResponseEntity getSite(@PathParam("siteId") String siteId, @QueryParam("relations") MultiValuedParam<SiteRelation> relations,
            @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Deletes a particular site (and its primary children/descendants) and moves it into the archive / trash can..
     *
     * @param siteId
     *            the ID of the site to delete
     */
    @DELETE
    @Path("/sites/{siteId}")
    void deleteSite(@PathParam("siteId") String siteId);

    /**
     * Deletes a particular site (and its primary children/descendants).
     *
     * @param siteId
     *            the ID of the site to delete
     * @param permanent
     *            {@code true} if the site should be deleted permanently (not moved into the archive / trash can), {@code false} otherwise
     */
    @DELETE
    @Path("/sites/{siteId}")
    void deleteNode(@PathParam("siteId") String siteId, @QueryParam("permanent") boolean permanent);

    /**
     * Updates a site.
     *
     * @param siteId
     *            the ID of the site to update
     * @param site
     *            the details of the site to update (the ID of the site cannot be modified with this operation)
     *
     * @return the site
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/sites/{siteId}")
    SiteResponseEntity updateSite(@PathParam("siteId") String siteId, SiteRequestEntity site);

    /**
     * Updates a site.
     *
     * @param siteId
     *            the ID of the site to update
     * @param site
     *            the details of the site to update (the ID of the site cannot be modified with this operation)
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     *
     * @return the site
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/site/{siteId}")
    SiteResponseEntity updateSite(@PathParam("siteId") String siteId, SiteRequestEntity site,
            @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Retrieves the list of accessible containers in a particular site.
     *
     * @param siteId
     *            the ID of the site from which to retrieve the containers
     * @return the list of containers in the site
     */
    @GET
    @Produces("application/json")
    @Path("/sites/{siteId}/containers")
    PaginatedList<SiteContainerResponseEntity> getSiteContainers(@PathParam("siteId") String siteId);

    /**
     * Retrieves the list of accessible containers in a particular site.
     *
     * @param siteId
     *            the ID of the site from which to retrieve the containers
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the list of containers in the site
     */
    @GET
    @Produces("application/json")
    @Path("/sites/{siteId}/containers")
    PaginatedList<SiteContainerResponseEntity> getSiteContainers(@PathParam("siteId") String siteId,
            @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Retrieves the list of accessible containers in a particular site.
     *
     * @param siteId
     *            the ID of the site from which to retrieve the containers
     * @param skipCount
     *            the number of site containers matching the retrieval condition(s) to skip
     * @param maxItems
     *            the maximum number of site containers to retrieve
     * @return the list of containers in the site
     */
    @GET
    @Produces("application/json")
    @Path("/sites/{siteId}/containers")
    PaginatedList<SiteContainerResponseEntity> getSiteContainers(@PathParam("siteId") String siteId, @QueryParam("skipCount") int skipCount,
            @QueryParam("maxItems") int maxItems);

    /**
     * Retrieves the list of accessible containers in a particular site.
     *
     * @param siteId
     *            the ID of the site from which to retrieve the containers
     * @param skipCount
     *            the number of site containers matching the retrieval condition(s) to skip
     * @param maxItems
     *            the maximum number of site containers to retrieve
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the list of containers in the site
     */
    @GET
    @Produces("application/json")
    @Path("/sites/{siteId}/containers")
    PaginatedList<SiteContainerResponseEntity> getSiteContainers(@PathParam("siteId") String siteId, @QueryParam("skipCount") int skipCount,
            @QueryParam("maxItems") int maxItems, @QueryParam("fields") MultiValuedParam<String> fields);

    /**
     * Retrieves a particular container in a particular site.
     *
     * @param siteId
     *            the ID of the site from which to retrieve the container
     * @param containerId
     *            the ID of the container to retrieve
     * @return the container of the site
     */
    @GET
    @Produces("application/json")
    @Path("/sites/{siteId}/containers/{containerId}")
    SiteContainerResponseEntity getSiteContainer(@PathParam("siteId") String siteId, @PathParam("containerId") String containerId);

    /**
     * Retrieves a particular container in a particular site.
     *
     * @param siteId
     *            the ID of the site from which to retrieve the container
     * @param containerId
     *            the ID of the container to retrieve
     * @param fields
     *            the list of fields to which to restrict the response in order to save bandwidth ({@code include} adds to this list if
     *            provided) - should be {@code null} if no restrictions should be applied as an empty list / multi-valued param is treated
     *            as "include no fields at all"
     * @return the container of the site
     */
    @GET
    @Produces("application/json")
    @Path("/sites/{siteId}/containers/{containerId}")
    SiteContainerResponseEntity getSiteContainer(@PathParam("siteId") String siteId, @PathParam("containerId") String containerId,
            @QueryParam("fields") MultiValuedParam<String> fields);
}
