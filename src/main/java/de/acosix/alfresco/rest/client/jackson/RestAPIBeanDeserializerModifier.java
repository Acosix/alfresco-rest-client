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

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;

/**
 * @author Axel Faust
 */
public class RestAPIBeanDeserializerModifier extends BeanDeserializerModifier
{

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonDeserializer<?> modifyDeserializer(final DeserializationConfig config, final BeanDescription beanDesc,
            final JsonDeserializer<?> deserializer)
    {
        final Class<?> beanClass = beanDesc.getBeanClass();
        JsonDeserializer<?> resultDeserializer = deserializer;
        final Wrapped wrapped = beanClass.getAnnotation(Wrapped.class);
        if (wrapped != null)
        {
            resultDeserializer = new WrappedFacadeDeserializer<>(beanClass, wrapped.value(), deserializer);
        }
        return resultDeserializer;
    }

}
