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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Axel Faust
 */
public class ResponsePermissionsInfo extends PermissionsInfo
{

    private List<PermissionElement> inherited;

    private List<String> settable;

    /**
     * Creates a new instance of this value class.
     */
    public ResponsePermissionsInfo()
    {
        super();
    }

    /**
     * Creates a new instance of this value class as a full (recursive) copy of the provided reference / template.
     *
     * @param reference
     *            the reference / template for the new instance
     */
    public ResponsePermissionsInfo(final ResponsePermissionsInfo reference)
    {
        super(reference);

        final List<PermissionElement> inherited = reference.getInherited();
        if (inherited != null)
        {
            this.inherited = new ArrayList<>(inherited.size());
            inherited.stream().map(PermissionElement::new).forEach(this.inherited::add);
        }

        final List<String> settable = reference.getSettable();
        if (settable != null)
        {
            this.settable = new ArrayList<>(settable);
        }
    }

    /**
     * @return the inherited
     */
    public List<PermissionElement> getInherited()
    {
        return this.inherited != null ? new ArrayList<>(this.inherited) : null;
    }

    /**
     * @param inherited
     *            the inherited to set
     */
    public void setInherited(final List<PermissionElement> inherited)
    {
        this.inherited = inherited != null ? new ArrayList<>(inherited) : null;
    }

    /**
     * @return the settable
     */
    public List<String> getSettable()
    {
        return this.settable != null ? new ArrayList<>(this.settable) : null;
    }

    /**
     * @param settable
     *            the settable to set
     */
    public void setSettable(final List<String> settable)
    {
        this.settable = settable != null ? new ArrayList<>(settable) : null;
    }

}
