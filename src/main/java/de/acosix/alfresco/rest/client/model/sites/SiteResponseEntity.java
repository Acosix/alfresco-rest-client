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
package de.acosix.alfresco.rest.client.model.sites;

import de.acosix.alfresco.rest.client.jackson.Wrapped;
import de.acosix.alfresco.rest.client.jackson.Wrapped.WrapType;

/**
 * @author Axel Faust
 */
@Wrapped(WrapType.ENTRY)
public class SiteResponseEntity extends CoreSiteDetails
{

    private String guid;

    private String id;

    private String preset;

    private String role;

    /**
     * Creates a new instance of this value class.
     */
    public SiteResponseEntity()
    {
        // NO-OP
    }

    /**
     * Creates a new instance of this value class as a full (recursive) copy of the provided reference / template. All state will be copied
     * to create a best possible detached copy.
     *
     * @param reference
     *            the reference / template for the new instance
     */
    public SiteResponseEntity(final SiteResponseEntity reference)
    {
        super(reference);
        this.guid = reference.getGuid();
        this.id = reference.getId();
        this.preset = reference.getPreset();
        this.role = reference.getRole();
    }

    /**
     * @return the guid
     */
    public String getGuid()
    {
        return this.guid;
    }

    /**
     * @param guid
     *            the guid to set
     */
    public void setGuid(final String guid)
    {
        this.guid = guid;
    }

    /**
     * @return the id
     */
    public String getId()
    {
        return this.id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final String id)
    {
        this.id = id;
    }

    /**
     * @return the preset
     */
    public String getPreset()
    {
        return this.preset;
    }

    /**
     * @param preset
     *            the preset to set
     */
    public void setPreset(final String preset)
    {
        this.preset = preset;
    }

    /**
     * @return the role
     */
    public String getRole()
    {
        return this.role;
    }

    /**
     * @param role
     *            the role to set
     */
    public void setRole(final String role)
    {
        this.role = role;
    }
}
