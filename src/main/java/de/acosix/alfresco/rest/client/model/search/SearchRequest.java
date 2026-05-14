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
 * @since 24/08/2022 - 10:23
 */
public class SearchRequest
{

    private QueryEntity query;

    private PagingEntity paging;

    private String[] include;

    private String[] fields;

    public SearchRequest(final QueryEntity query)
    {
        this.query = query;
    }

    public SearchRequest(final QueryEntity query, final PagingEntity paging)
    {
        this.query = query;
        this.paging = paging;
    }

    public SearchRequest(final QueryEntity query, final PagingEntity paging, String[] include, String[] fields)
    {
        this.query = query;
        this.paging = paging;
        this.include = include;
        this.fields = fields;
    }

    /**
     * @return the query
     */
    public QueryEntity getQuery()
    {
        return query;
    }

    /**
     * @param query
     *     the query to set
     */
    public void setQuery(QueryEntity query)
    {
        this.query = query;
    }

    /**
     * @return the paging
     */
    public PagingEntity getPaging()
    {
        return paging;
    }

    /**
     * @param paging
     *     the paging to set
     */
    public void setPaging(PagingEntity paging)
    {
        this.paging = paging;
    }

    /**
     * @return the include
     */
    public String[] getInclude()
    {
        return include;
    }

    /**
     * @param include
     *     the include to set
     */
    public void setInclude(String[] include)
    {
        this.include = include;
    }

    /**
     * @return the fields
     */
    public String[] getFields()
    {
        return fields;
    }

    /**
     * @param fields
     *     the fields to set
     */
    public void setFields(String[] fields)
    {
        this.fields = fields;
    }

}
