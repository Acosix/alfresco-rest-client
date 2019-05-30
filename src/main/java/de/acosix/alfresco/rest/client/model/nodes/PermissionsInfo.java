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
public class PermissionsInfo
{

    private Boolean isInheritanceEnabled;

    private List<PermissionElement> locallySet;

    /**
     * Creates a new instance of this value class.
     */
    public PermissionsInfo()
    {
        // NO-OP
    }

    /**
     * Creates a new instance of this value class as a full (recursive) copy of the provided reference / template.
     *
     * @param reference
     *            the reference / template for the new instance
     */
    public PermissionsInfo(final PermissionsInfo reference)
    {
        this.isInheritanceEnabled = reference.getIsInheritanceEnabled();
        final List<PermissionElement> locallySet = reference.getLocallySet();
        if (locallySet != null)
        {
            this.locallySet = new ArrayList<>(locallySet.size());
            locallySet.stream().map(PermissionElement::new).forEach(this.locallySet::add);
        }
    }

    /**
     * @return the isInheritanceEnabled
     */
    public Boolean getIsInheritanceEnabled()
    {
        return this.isInheritanceEnabled;
    }

    /**
     * @param isInheritanceEnabled
     *            the isInheritanceEnabled to set
     */
    public void setIsInheritanceEnabled(final Boolean isInheritanceEnabled)
    {
        this.isInheritanceEnabled = isInheritanceEnabled;
    }

    /**
     * @return the locallySet
     */
    public List<PermissionElement> getLocallySet()
    {
        return this.locallySet != null ? new ArrayList<>(this.locallySet) : null;
    }

    /**
     * @param locallySet
     *            the locallySet to set
     */
    public void setLocallySet(final List<PermissionElement> locallySet)
    {
        this.locallySet = locallySet != null ? new ArrayList<>(locallySet) : null;
    }
}
