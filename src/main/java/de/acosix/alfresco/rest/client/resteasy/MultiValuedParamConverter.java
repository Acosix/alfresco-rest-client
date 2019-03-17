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
package de.acosix.alfresco.rest.client.resteasy;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.ext.ParamConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.acosix.alfresco.rest.client.model.common.MultiValuedParam;

/**
 * @author Axel Faust
 */
public class MultiValuedParamConverter implements ParamConverter<MultiValuedParam<?>>
{

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiValuedParamConverter.class);

    private final Type genericType;

    public MultiValuedParamConverter(final Type genericType)
    {
        this.genericType = genericType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MultiValuedParam<?> fromString(final String value)
    {
        MultiValuedParam<?> result = null;
        if (value != null && !value.trim().isEmpty())
        {
            LOGGER.debug("Converting {} to MultiValuedParam with generic type {}", value, this.genericType);
            final String[] fragments = value.split(",");
            final List<String> listOfRawValues = Arrays.asList(fragments);
            // TODO Use genericType to try and convert rawValues
            // luckily fromString is not relevant for the client use-case just now
            result = new MultiValuedParam<>(listOfRawValues);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString(final MultiValuedParam<?> value)
    {
        String result = null;

        if (value != null)
        {
            final List<?> values = value.getValues();
            LOGGER.debug("Converting MultiValuedParam with generic type {} and values {} to string", value, this.genericType, values);
            final StringBuilder sb = new StringBuilder();
            value.getValues().forEach(e -> {
                if (e != null)
                {
                    if (sb.length() != 0)
                    {
                        sb.append(',');
                    }
                    // TODO Use any ParamConverter registered for the element / generic type
                    // (or rely on proper toString() like we do now)
                    sb.append(e);
                }
            });
            result = sb.length() > 0 ? sb.toString() : "";
        }

        return result;
    }

}
