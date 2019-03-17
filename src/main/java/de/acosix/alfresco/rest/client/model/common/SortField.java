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
 * This interface acts primarily as a common type marker for various sort field enumerations for specific Alfresco v1 ReST API operations.
 * It is generally used as an upper type bound in generic type definitions of other model types.
 *
 * @author Axel Faust
 */
public interface SortField
{

    /**
     * Retrieves the name of the sort field represented by this instance, as should be used in a call to the Alfresco v1 ReST API.
     *
     * @return the name of the field by which to sort
     */
    String getFieldName();
}
