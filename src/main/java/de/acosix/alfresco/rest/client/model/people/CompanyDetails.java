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
public class CompanyDetails
{

    private String organization;

    private String address1;

    private String address2;

    private String address3;

    private String postcode;

    private String telephone;

    private String fax;

    private String email;

    /**
     * Creates a new instance of this value class.
     */
    public CompanyDetails()
    {
        // NO-OP
    }

    /**
     * Creates a new instance of this value class as a full (recursive) copy of the provided reference / template.
     *
     * @param reference
     *            the reference / template for the new instance
     */
    public CompanyDetails(final CompanyDetails reference)
    {
        this.organization = reference.getOrganization();
        this.address1 = reference.getAddress1();
        this.address2 = reference.getAddress2();
        this.address3 = reference.getAddress3();
        this.postcode = reference.getPostcode();
        this.telephone = reference.getTelephone();
        this.fax = reference.getFax();
        this.email = reference.getEmail();
    }

    /**
     * @return the organization
     */
    public String getOrganization()
    {
        return this.organization;
    }

    /**
     * @param organization
     *            the organization to set
     */
    public void setOrganization(final String organization)
    {
        this.organization = organization;
    }

    /**
     * @return the address1
     */
    public String getAddress1()
    {
        return this.address1;
    }

    /**
     * @param address1
     *            the address1 to set
     */
    public void setAddress1(final String address1)
    {
        this.address1 = address1;
    }

    /**
     * @return the address2
     */
    public String getAddress2()
    {
        return this.address2;
    }

    /**
     * @param address2
     *            the address2 to set
     */
    public void setAddress2(final String address2)
    {
        this.address2 = address2;
    }

    /**
     * @return the address3
     */
    public String getAddress3()
    {
        return this.address3;
    }

    /**
     * @param address3
     *            the address3 to set
     */
    public void setAddress3(final String address3)
    {
        this.address3 = address3;
    }

    /**
     * @return the postcode
     */
    public String getPostcode()
    {
        return this.postcode;
    }

    /**
     * @param postcode
     *            the postcode to set
     */
    public void setPostcode(final String postcode)
    {
        this.postcode = postcode;
    }

    /**
     * @return the telephone
     */
    public String getTelephone()
    {
        return this.telephone;
    }

    /**
     * @param telephone
     *            the telephone to set
     */
    public void setTelephone(final String telephone)
    {
        this.telephone = telephone;
    }

    /**
     * @return the fax
     */
    public String getFax()
    {
        return this.fax;
    }

    /**
     * @param fax
     *            the fax to set
     */
    public void setFax(final String fax)
    {
        this.fax = fax;
    }

    /**
     * @return the email
     */
    public String getEmail()
    {
        return this.email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(final String email)
    {
        this.email = email;
    }

}
