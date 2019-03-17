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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to mark a specific value type as a type which the Alfresco v1 ReST API always wraps inside an extra layer of a JSON
 * object with a very specific property. Based on the presence of this annotation, the default deserialization by Jackson will be facaded to
 * ensure objects are properly read from JSON returned by the Alfresco Repository.
 *
 * @author Axel Faust
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Wrapped
{

    /**
     *
     * @author Axel Faust
     */
    public static enum WrapType
    {
        ENTRY,
        LIST;
    }

    /**
     * Returns the type of wrapping used in Alfresco v1 ReST API for this value type.
     *
     * @return the type of wrapping for this value type
     */
    WrapType value();
}
