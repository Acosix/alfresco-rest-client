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

import de.acosix.alfresco.rest.client.model.common.SortField;

/**
 * This enumeration specifies the supported fields by which sites may be sorted.
 *
 * @author Axel Faust
 */
public enum SiteSortField implements SortField
{
    ID("id"),
    TITLE("title"),
    DESCRIPTION("description");

    private final String fieldName;

    private SiteSortField(final String fieldName)
    {
        this.fieldName = fieldName;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getFieldName()
    {
        return this.fieldName;
    }

}
