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

/**
 * @author Walid Maadi
 *
 * @since 24/08/2022 - 10:27
 */
public class PagingEntity
{

    private int maxItems;

    private int skipCount;

    /**
     * @return the maxItems
     */
    public int getMaxItems()
    {
        return maxItems;
    }

    /**
     * @param maxItems
     *     the maxItems to set
     */
    public void setMaxItems(int maxItems)
    {
        this.maxItems = maxItems;
    }

    /**
     * @return the skipCount
     */
    public int getSkipCount()
    {
        return skipCount;
    }

    /**
     * @param skipCount
     *     the skipCount to set
     */
    public void setSkipCount(int skipCount)
    {
        this.skipCount = skipCount;
    }
}
