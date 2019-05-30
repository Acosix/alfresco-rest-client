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
package de.acosix.alfresco.rest.client.model.nodes;

/**
 * @author Axel Faust
 */
public class PermissionElement
{

    /**
     *
     * @author Axel Faust
     */
    public static enum AccessStatus
    {
        /**
         * The permission has been granted to the authority in question.
         */
        ALLOWED,
        /**
         * The permission has been denied to the authority in question.
         */
        DENIED;
    }

    private String authorityId;

    private String name;

    private AccessStatus accessStatus;

    /**
     * Creates a new instance of this value class.
     */
    public PermissionElement()
    {
        // NO-OP
    }

    /**
     * Creates a new instance of this value class as a full (recursive) copy of the provided reference / template.
     *
     * @param reference
     *            the reference / template for the new instance
     */
    public PermissionElement(final PermissionElement reference)
    {
        this.authorityId = reference.getAuthorityId();
        this.name = reference.getName();
        this.accessStatus = reference.getAccessStatus();
    }

    /**
     * @return the authorityId
     */
    public String getAuthorityId()
    {
        return this.authorityId;
    }

    /**
     * @param authorityId
     *            the authorityId to set
     */
    public void setAuthorityId(final String authorityId)
    {
        this.authorityId = authorityId;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * @return the accessStatus
     */
    public AccessStatus getAccessStatus()
    {
        return this.accessStatus;
    }

    /**
     * @param accessStatus
     *            the accessStatus to set
     */
    public void setAccessStatus(final AccessStatus accessStatus)
    {
        this.accessStatus = accessStatus;
    }

}
