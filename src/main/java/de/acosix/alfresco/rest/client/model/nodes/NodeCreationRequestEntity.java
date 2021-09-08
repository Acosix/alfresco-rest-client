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

    private String relativePath;

    private AssociationTypeEntity association;

    private List<ChildAssociationEntity> secondaryChildren;

    private List<TargetAssociationEntity> targets;

    /**
     * Creates a new instance of this value class.
     */
    public NodeCreationRequestEntity()
    {
        // NO-OP
    }

    /**
     * Creates a new instance of this value class as a full (recursive) copy of the provided reference / template.
     *
     * @param reference
     *            the reference / template for the new instance
     */
    public NodeCreationRequestEntity(final NodeCreationRequestEntity reference)
    {
        super(reference);

        this.relativePath = reference.getRelativePath();

        final AssociationTypeEntity association = reference.getAssociation();
        if (association != null)
        {
            this.association = new AssociationTypeEntity(association);
        }

        final List<ChildAssociationEntity> secondaryChildren = reference.getSecondaryChildren();
        if (secondaryChildren != null)
        {
            this.secondaryChildren = new ArrayList<>(secondaryChildren.size());
            secondaryChildren.stream().map(ChildAssociationEntity::new).forEach(this.secondaryChildren::add);
        }

        final List<TargetAssociationEntity> targets = reference.getTargets();
        if (targets != null)
        {
            this.targets = new ArrayList<>(targets.size());
            targets.stream().map(TargetAssociationEntity::new).forEach(this.targets::add);
        }
    }

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
    public List<ChildAssociationEntity> getSecondaryChildren()
    {
        return this.secondaryChildren != null ? new ArrayList<>(this.secondaryChildren) : null;
    }

    /**
     * @param secondaryChildren
     *            the secondaryChildren to set
     */
    public void setSecondaryChildren(final List<ChildAssociationEntity> secondaryChildren)
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
