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

/**
 * @author Axel Faust
 */
public class CoreSiteDetails
{

    private String id;

    private String title;

    private String description;

    private SiteVisibility visibility;

    private String preset;

    /**
     * Creates a new instance of this value class.
     */
    public CoreSiteDetails()
    {
        // NO-OP
    }

    /**
     * Creates a new instance of this value class as a full (recursive) copy of the provided reference / template. All state - except for
     * the values of properties / capabilities - will be recursively copied to create a best possible detached copy.
     *
     * @param reference
     *            the reference / template for the new instance
     */
    public CoreSiteDetails(final CoreSiteDetails reference)
    {
        this.id = reference.getId();
        this.title = reference.getTitle();
        this.description = reference.getDescription();
        this.visibility = reference.getVisibility();
        this.preset = reference.getPreset();
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
     * @return the title
     */
    public String getTitle()
    {
        return this.title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(final String title)
    {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(final String description)
    {
        this.description = description;
    }

    /**
     * @return the visibility
     */
    public SiteVisibility getVisibility()
    {
        return this.visibility;
    }

    /**
     * @param visibility
     *            the visibility to set
     */
    public void setVisibility(final SiteVisibility visibility)
    {
        this.visibility = visibility;
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

}
