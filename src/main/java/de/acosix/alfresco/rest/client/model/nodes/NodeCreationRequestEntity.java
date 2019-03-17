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
public class NodeCreationRequestEntity extends CommonNodeEntity<PermissionsInfo>
{
    // TODO copy constructors

    private String relativePath;

    private AssociationTypeEntity association;

    private List<ChildAssociationRequestEntity> secondaryChildren;

    private List<TargetAssociationEntity> targets;

    /**
     * @return the relativePath
     */
    public String getRelativePath()
    {
        return this.relativePath;
    }

    /**
     * @param relativePath
     *            the relativePath to set
     */
    public void setRelativePath(final String relativePath)
    {
        this.relativePath = relativePath;
    }

    /**
     * @return the association
     */
    public AssociationTypeEntity getAssociation()
    {
        return this.association;
    }

    /**
     * @param association
     *            the association to set
     */
    public void setAssociation(final AssociationTypeEntity association)
    {
        this.association = association;
    }

    /**
     * @return the secondaryChildren
     */
    public List<ChildAssociationRequestEntity> getSecondaryChildren()
    {
        return this.secondaryChildren != null ? new ArrayList<>(this.secondaryChildren) : null;
    }

    /**
     * @param secondaryChildren
     *            the secondaryChildren to set
     */
    public void setSecondaryChildren(final List<ChildAssociationRequestEntity> secondaryChildren)
    {
        this.secondaryChildren = secondaryChildren != null ? new ArrayList<>(secondaryChildren) : null;
    }

    /**
     * @return the targets
     */
    public List<TargetAssociationEntity> getTargets()
    {
        return this.targets != null ? new ArrayList<>(this.targets) : null;
    }

    /**
     * @param targets
     *            the targets to set
     */
    public void setTargets(final List<TargetAssociationEntity> targets)
    {
        this.targets = targets != null ? new ArrayList<>(targets) : null;
    }

}
