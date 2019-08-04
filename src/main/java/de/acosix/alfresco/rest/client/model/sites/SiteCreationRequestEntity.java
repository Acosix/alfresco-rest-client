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
public class SiteCreationRequestEntity extends CoreSiteDetails
{

    private String id;

    /**
     * Creates a new instance of this value class.
     */
    public SiteCreationRequestEntity()
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
    public SiteCreationRequestEntity(final SiteCreationRequestEntity reference)
    {
        super(reference);
        this.id = reference.getId();
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
}
