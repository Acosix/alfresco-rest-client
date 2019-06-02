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
package de.acosix.alfresco.rest.client.model.people;

/**
 * @author Axel Faust
 */
public class PersonRequestEntity extends CommonPersonDetails
{

    private String password;

    private String oldPassword;

    /**
     * Creates a new instance of this value class.
     */
    public PersonRequestEntity()
    {
        // NO-OP
    }

    /**
     * Creates a new instance of this value class as a full (recursive) copy of the provided reference / template. All state - except for
     * the values of properties / capabilities - will be recursively copied to create a best possible detached copy.
     *
     * @param reference
     *            the reference / template for the new instance
     */
    public PersonRequestEntity(final PersonRequestEntity reference)
    {
        super(reference);
        this.password = reference.getPassword();
        this.oldPassword = reference.getOldPassword();
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return this.password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(final String password)
    {
        this.password = password;
    }

    /**
     * @return the oldPassword
     */
    public String getOldPassword()
    {
        return this.oldPassword;
    }

    /**
     * @param oldPassword
     *            the oldPassword to set
     */
    public void setOldPassword(final String oldPassword)
    {
        this.oldPassword = oldPassword;
    }

}
