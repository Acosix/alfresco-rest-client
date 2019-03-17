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
package de.acosix.alfresco.rest.client.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import de.acosix.alfresco.rest.client.jackson.Wrapped.WrapType;

/**
 * @author Axel Faust
 */
public class WrappedFacadeDeserializer<T> extends StdDeserializer<T> implements ResolvableDeserializer
{

    private static final long serialVersionUID = 010L;

    private final Class<T> cls;

    private final WrapType wrapType;

    private final JsonDeserializer<?> defaultDeserializer;

    public WrappedFacadeDeserializer(final Class<T> cls, final WrapType wrapType, final JsonDeserializer<?> defaultDeserializer)
    {
        super(cls);
        this.cls = cls;
        this.wrapType = wrapType;
        this.defaultDeserializer = defaultDeserializer;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public T deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JsonProcessingException
    {
        T result;
        String nextFieldName;
        if (p.isExpectedStartObjectToken())
        {
            nextFieldName = p.nextFieldName();
        }
        else if (p.getCurrentToken() == JsonToken.FIELD_NAME)
        {
            nextFieldName = p.getCurrentName();
        }
        else
        {
            nextFieldName = null;
        }

        String wrapFieldName;
        switch (this.wrapType)
        {
            case ENTRY:
                wrapFieldName = "entry";
                break;
            case LIST:
                wrapFieldName = "list";
                break;
            default:
                throw new IllegalStateException("Unsupported wrap type " + this.wrapType);
        }

        if (wrapFieldName.equals(nextFieldName))
        {
            // now points to START_OBJECT token (should)
            p.nextToken();
            final Object deserialized = this.defaultDeserializer.deserialize(p, ctxt);
            result = this.cls.cast(deserialized);
            // now points to END_OBJECT token of nested obj
            p.nextToken();

            if (p.currentToken() == JsonToken.FIELD_NAME)
            {
                throw new JsonMappingException(p, "JSON for " + this.cls + " contains unsupported extra field " + p.currentName()
                        + " besides '" + wrapFieldName + "' wrapper property");
            }
        }
        else
        {
            throw new JsonMappingException(p,
                    "JSON for " + this.cls + " is not wrapped inside an object with '" + wrapFieldName + "' property");
        }
        return result;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void resolve(final DeserializationContext ctxt) throws JsonMappingException
    {
        if (this.defaultDeserializer instanceof ResolvableDeserializer)
        {
            ((ResolvableDeserializer) this.defaultDeserializer).resolve(ctxt);
        }
    }

}
