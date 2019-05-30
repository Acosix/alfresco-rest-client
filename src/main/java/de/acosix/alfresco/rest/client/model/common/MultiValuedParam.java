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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.ext.ParamConverter;

/**
 * Instances of this class encapsulate multiple values for a particular Alfresco v1 ReST API operation.
 *
 * This class becomes necessary since JAX-RS handling, e.g. as implemented in RESTEasy, will take a regular {@link Collection
 * collection}-based query parameter and transform it into multiple occurences of the query parameter, while Alfresco mostly only supports
 * comma-separated values in a single parameter. In an operation defined as
 *
 * <pre>
 *
 * NodeResponseEntity getNode(@PathParam("nodeId") String nodeId, @QueryParam("include") List&lt;IncludeOption&gt; include,
 *         &#64;QueryParam("fields") List&lt;String&gt; fields);
 * </pre>
 *
 * the parameters for {@code include} and {@code fields} would be processed into
 *
 * <pre>
 * ...?include=includeElem1&amp;include=includeElem2&amp;include=includeElemX&amp;fields=fieldsElem1&amp;fields=fieldsElem2&amp;fields=fieldsElemX
 * </pre>
 *
 * Even though there is support for custom parameter conversion via {@link ParamConverter parameter converters}, any converter is only
 * invoked when the collection / array has already been "exploded" into individual elements, so cannot affect how multi-valued parameters
 * are treated with regards to URL query parameters.
 *
 * @author Axel Faust
 */
public class MultiValuedParam<T>
{

    private final List<T> values;

    /**
     * Creates a new instance from the provided list of values.
     *
     * @param values
     *            the list of values to wrap
     */
    public MultiValuedParam(final List<T> values)
    {
        if (values == null)
        {
            throw new IllegalArgumentException("'values' must not be null");
        }
        this.values = new ArrayList<>(values);
    }

    /**
     * Creates a new instance from the provided list of values.
     *
     * @param values
     *            the list of values to wrap
     */
    @SafeVarargs
    public MultiValuedParam(final T... values)
    {
        if (values == null)
        {
            throw new IllegalArgumentException("'values' must not be null");
        }
        this.values = new ArrayList<>(Arrays.asList(values));
    }

    /**
     * Retrieves the values encapsulated by this instance.
     *
     * @return the encapsulated values
     */
    public List<T> getValues()
    {
        return new ArrayList<>(this.values);
    }

}
