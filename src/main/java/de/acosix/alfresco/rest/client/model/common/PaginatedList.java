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

import java.util.ArrayList;
import java.util.List;

import de.acosix.alfresco.rest.client.jackson.Wrapped;
import de.acosix.alfresco.rest.client.jackson.Wrapped.WrapType;

/**
 * @author Axel Faust
 */
@Wrapped(WrapType.LIST)
public class PaginatedList<T>
{

    private PaginationInfo pagination;

    private List<T> entries;

    /**
     * @return the pagination
     */
    public PaginationInfo getPagination()
    {
        return this.pagination;
    }

    /**
     * @param pagination
     *            the pagination to set
     */
    public void setPagination(final PaginationInfo pagination)
    {
        this.pagination = pagination;
    }

    /**
     * @return the entries
     */
    public List<T> getEntries()
    {
        return this.entries != null ? new ArrayList<>(this.entries) : null;
    }

    /**
     * @param entries
     *            the entries to set
     */
    public void setEntries(final List<T> entries)
    {
        this.entries = entries != null ? new ArrayList<>(entries) : null;
    }

}
