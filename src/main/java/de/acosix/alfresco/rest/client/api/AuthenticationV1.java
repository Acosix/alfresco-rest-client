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
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import de.acosix.alfresco.rest.client.model.authentication.TicketEntity;
import de.acosix.alfresco.rest.client.model.authentication.TicketRequest;

/**
 * Instances of this API provide operations to authenticate against the Alfresco v1 ReST API, as well as validate and terminate existing
 * authentications.
 *
 * @author Axel Faust
 */
@Path("/api/-default-/public/authentication/versions/1")
public interface AuthenticationV1
{

    /**
     * Logs in and returns the new authentication ticket.
     *
     * @param ticketRequest
     *            the user credentials
     * @return the authentication ticket
     */
    @POST
    @Path("/tickets")
    @Produces("application/json")
    @Consumes("application/json")
    TicketEntity createTicket(TicketRequest ticketRequest);

    /**
     * Performs a logout invalidating the current ticket (must be provided via Authentication header of the backing client).
     */
    @DELETE
    @Path("/tickets/-me-")
    void deleteTicket();

    /**
     * Validates the current ticket (must be provided via Authentication header of the backing client).
     *
     * @return the ticket if validation was successful
     */
    @GET
    @Path("/tickets/-me-")
    TicketEntity validateTicket();
}
