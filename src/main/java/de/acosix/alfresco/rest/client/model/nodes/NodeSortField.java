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

import de.acosix.alfresco.rest.client.model.common.SortField;

/**
 * @author Axel Faust
 */
public enum NodeSortField implements SortField
{
    IS_FOLDER("isFolder"),
    NAME("name"),
    MIMETYPE("mimeType"),
    NODE_TYPE("nodeType"),
    SIZE("sizeInBytes"),
    MODIFIED_AT("modifiedAt"),
    CREATED_AT("createdAt"),
    MODIFIED_BY("modifiedByUser"),
    CREATED_BY("createdByUser");

    private final String fieldName;

    private NodeSortField(final String fieldName)
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
