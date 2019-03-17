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
 * @author Axel Faust
 */
public class PaginationInfo
{

    private int count;

    private int skipCount;

    private int maxItems;

    private Integer totalItems;

    private boolean hasMoreItems;

    /**
     * @return the count
     */
    public int getCount()
    {
        return this.count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(final int count)
    {
        this.count = count;
    }

    /**
     * @return the skipCount
     */
    public int getSkipCount()
    {
        return this.skipCount;
    }

    /**
     * @param skipCount
     *            the skipCount to set
     */
    public void setSkipCount(final int skipCount)
    {
        this.skipCount = skipCount;
    }

    /**
     * @return the maxItems
     */
    public int getMaxItems()
    {
        return this.maxItems;
    }

    /**
     * @param maxItems
     *            the maxItems to set
     */
    public void setMaxItems(final int maxItems)
    {
        this.maxItems = maxItems;
    }

    /**
     * @return the totalItems
     */
    public Integer getTotalItems()
    {
        return this.totalItems;
    }

    /**
     * @param totalItems
     *            the totalItems to set
     */
    public void setTotalItems(final Integer totalItems)
    {
        this.totalItems = totalItems;
    }

    /**
     * @return the hasMoreItems
     */
    public boolean getHasMoreItems()
    {
        return this.hasMoreItems;
    }

    /**
     * @param hasMoreItems
     *            the hasMoreItems to set
     */
    public void setHasMoreItems(final boolean hasMoreItems)
    {
        this.hasMoreItems = hasMoreItems;
    }

}
