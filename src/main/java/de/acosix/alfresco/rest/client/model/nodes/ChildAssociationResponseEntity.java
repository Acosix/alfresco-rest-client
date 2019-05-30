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

/**
 * @author Axel Faust
 */
public class ChildAssociationResponseEntity extends AssociationTypeEntity
{

    /**
     * Creates a new instance of this value class.
     */
    public ChildAssociationResponseEntity()
    {
        super();
    }

    /**
     * Creates a new instance of this value class as a full (recursive) copy of the provided reference / template.
     *
     * @param reference
     *            the reference / template for the new instance
     */
    public ChildAssociationResponseEntity(final ChildAssociationResponseEntity reference)
    {
        super(reference);
        this.isPrimary = reference.isPrimary;
    }

    private boolean isPrimary;

    /**
     * @return the isPrimary
     */
    public boolean isPrimary()
    {
        return this.isPrimary;
    }

    /**
     * @param isPrimary
     *            the isPrimary to set
     */
    public void setPrimary(final boolean isPrimary)
    {
        this.isPrimary = isPrimary;
    }

}
