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
 * @since 24/08/2022 - 10:20
 */
public class QueryEntity
{

    private String query;

    private String userQuery;

    private String language;

    /**
     * @return the query
     */
    public String getQuery()
    {
        return query;
    }

    /**
     * @param query
     *     the query to set
     */
    public void setQuery(String query)
    {
        this.query = query;
    }

    /**
     * @return the userQuery
     */
    public String getUserQuery()
    {
        return userQuery;
    }

    /**
     * @param userQuery
     *     the userQuery to set
     */
    public void setUserQuery(String userQuery)
    {
        this.userQuery = userQuery;
    }

    /**
     * @return the language
     */
    public String getLanguage()
    {
        return language;
    }

    /**
     * @param language
     *     the language to set
     */
    public void setLanguage(String language)
    {
        this.language = language;
    }

}
