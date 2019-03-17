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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Axel Faust
 */
public class CommonNodeEntity<PI extends PermissionsInfo> extends NodeCoreIdentity
{

    // TODO copy constructors

    private Map<String, Object> properties;

    private PI permissions;

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
