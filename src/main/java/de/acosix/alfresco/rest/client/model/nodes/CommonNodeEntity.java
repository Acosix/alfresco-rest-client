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
package de.acosix.alfresco.rest.client.model.nodes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Axel Faust
 */
public class CommonNodeEntity<PI extends PermissionsInfo> extends NodeCoreIdentity
{

    private Map<String, Object> properties;

    private PI permissions;

    /**
     * Creates a new instance of this value class.
     */
    public CommonNodeEntity()
    {
        // NO-OP
    }

    /**
     * Creates a new instance of this value class as a full copy of the provided reference / template. All state - except for the values of
     * properties - will be recursively copied to create a best possible detached copy.
     *
     * @param reference
     *            the reference / template for the new instance
     */
    public CommonNodeEntity(final CommonNodeEntity<PI> reference)
    {
        super(reference);

        final Map<String, Object> properties = reference.getProperties();
        if (properties != null)
        {
            this.properties = new HashMap<>(properties);
        }

        // due to generics we cannot handle copy of permissions other than via reflection
        final PI permissions = reference.getPermissions();
        if (permissions != null)
        {
            @SuppressWarnings("unchecked")
            final Class<PI> permClass = (Class<PI>) permissions.getClass();
            try
            {
                final Constructor<PI> copyConstructor = permClass.getConstructor(permClass);
                this.permissions = copyConstructor.newInstance(permissions);
            }
            catch (final NoSuchMethodException nsme)
            {
                throw new UnsupportedOperationException(
                        "Cannot create copy as permissions info class " + permClass + " does not define a copy-constructor");
            }
            catch (final IllegalAccessException | InstantiationException | InvocationTargetException ite)
            {
                throw new RuntimeException("Failed to copy permissions info", ite);
            }
        }
    }

    /**
     * @return the properties
     */
    public Map<String, Object> getProperties()
    {
        return this.properties != null ? new HashMap<>(this.properties) : null;
    }

    /**
     * @param properties
     *            the properties to set
     */
    public void setProperties(final Map<String, Object> properties)
    {
        this.properties = properties != null ? new HashMap<>(properties) : null;
    }

    /**
     * Sets the value of an individual property.
     *
     * @param propertyName
     *            the name of the property for which to set the value
     * @param value
     *            the value to set
     */
    public void setProperty(final String propertyName, final Object value)
    {
        if (this.properties == null)
        {
            this.properties = new HashMap<>();
        }
        this.properties.put(propertyName, value);
    }

    /**
     * Retrieves the value of an individual property.
     *
     * @param propertyName
     *            the name of the property for which to retrieve the value
     * @return the current value of the property, or {@code null} if the property was not set
     */
    public Object getProperty(final String propertyName)
    {
        Object currentValue = null;
        if (this.properties != null)
        {
            currentValue = this.properties.get(propertyName);
        }
        return currentValue;
    }

    /**
     * Removes the value of an individual property.
     *
     * @param propertyName
     *            the name of the property for which to remove the value
     * @return the previous value of the property, or {@code null} if the property was not set
     */
    public Object removeProperty(final String propertyName)
    {
        Object removedValue = null;
        if (this.properties != null)
        {
            removedValue = this.properties.remove(propertyName);
        }
        return removedValue;
    }

    /**
     * @return the permissions
     */
    public PI getPermissions()
    {
        return this.permissions;
    }

    /**
     * @param permissions
     *            the permissions to set
     */
    public void setPermissions(final PI permissions)
    {
        this.permissions = permissions;
    }
}
