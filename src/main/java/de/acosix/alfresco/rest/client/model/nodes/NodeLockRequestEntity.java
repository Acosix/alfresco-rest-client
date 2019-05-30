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
public class NodeLockRequestEntity
{

    /**
     * Instances of this enumeration define the allowed types of locks that can be set via the ReST API.
     *
     * @author Axel Faust
     */
    public static enum LockType
    {
        /**
         * Allows (default) changes to the node to be made only by the lock owner.
         */
        ALLOW_OWNER_CHANGES,
        /**
         * Allows no changes by any user to be made.
         */
        FULL;
    }

    /**
     * Instances of this enumeration define the allowed lifetime modes to which a lock can set via the ReST API.
     *
     * @author Axel Faust
     */
    public static enum LockLifetime
    {
        /**
         * Locks with this lifetime mode are stored in the database and survive system restarts.
         */
        PERSISTENT,
        /**
         * Locks with this lifetime mode are only stored in-memory and will be lost upon system restart and/or cluster splits.
         */
        EPHEMERAL;
    }

    private Integer timeToExpire;

    private LockType type;

    private LockLifetime lifetime;

    /**
     * Creates a new instance of this value class.
     */
    public NodeLockRequestEntity()
    {
        // NO-OP
    }

    /**
     * Creates a new instance of this value class as a full (recursive) copy of the provided reference / template.
     *
     * @param reference
     *            the reference / template for the new instance
     */
    public NodeLockRequestEntity(final NodeLockRequestEntity reference)
    {
        this.timeToExpire = reference.getTimeToExpire();
        this.type = reference.getType();
        this.lifetime = reference.getLifetime();
    }

    /**
     * @return the timeToExpire
     */
    public Integer getTimeToExpire()
    {
        return this.timeToExpire;
    }

    /**
     * @param timeToExpire
     *            the timeToExpire to set
     */
    public void setTimeToExpire(final Integer timeToExpire)
    {
        this.timeToExpire = timeToExpire;
    }

    /**
     * @return the type
     */
    public LockType getType()
    {
        return this.type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(final LockType type)
    {
        this.type = type;
    }

    /**
     * @return the lifetime
     */
    public LockLifetime getLifetime()
    {
        return this.lifetime;
    }

    /**
     * @param lifetime
     *            the lifetime to set
     */
    public void setLifetime(final LockLifetime lifetime)
    {
        this.lifetime = lifetime;
    }

}
