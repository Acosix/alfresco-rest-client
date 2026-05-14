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
package de.acosix.alfresco.rest.client.model.search;

import de.acosix.alfresco.rest.client.jackson.Wrapped;
import de.acosix.alfresco.rest.client.jackson.Wrapped.WrapType;
import de.acosix.alfresco.rest.client.model.nodes.NodeResponseEntity;

/**
 * @author Walid Maadi
 * @since 24/08/2022 - 10:24
 */
@Wrapped(WrapType.ENTRY)
public class SearchResponseEntity extends NodeResponseEntity
{

    private Object search;

    private String location;

    /**
     * @return the search
     */
    public Object getSearch()
    {
        return search;
    }

    /**
     * @param search
     *     the search to set
     */
    public void setSearch(Object search)
    {
        this.search = search;
    }

    /**
     * @return the location
     */
    public String getLocation()
    {
        return location;
    }

    /**
     * @param location
     *     the location to set
     */
    public void setLocation(String location)
    {
        this.location = location;
    }

}
