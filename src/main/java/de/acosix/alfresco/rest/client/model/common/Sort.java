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
package de.acosix.alfresco.rest.client.model.common;

/**
 * Instances of this class define a specific sorting specification to be passed to an operation of the Alfresco v1 ReST API.
 *
 * @author Axel Faust
 */
public class Sort<SF extends SortField>
{

    private final SF sortField;

    private final boolean ascending;

    /**
     * Creates a new sorting specification based on the specified field in ascending order.
     *
     * @param sortField
     *            the field by which to sort in ascending order
     */
    public Sort(final SF sortField)
    {
        this(sortField, true);
    }

    /**
     * Creates a new sorting specification based on the specified field and order.
     *
     * @param sortField
     *            the field by which to sort
     * @param ascending
     *            {@code true} if to sort in ascending order, {@code false} otherwise
     */
    public Sort(final SF sortField, final boolean ascending)
    {
        this.sortField = sortField;
        this.ascending = ascending;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        // for simpler conversion into @QueryParam, especially since Sort will typically be used in MultiValuedParam, which does not yet
        // support transitive use of custom ParamConverter instances
        final String str = this.sortField.getFieldName() + " " + (this.ascending ? "ASC" : "DESC");
        return str;
    }
}
