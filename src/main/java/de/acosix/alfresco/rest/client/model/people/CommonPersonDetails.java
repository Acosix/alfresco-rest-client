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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Axel Faust
 */
public class CommonPersonDetails
{

    private String id;

    private String firstName;

    private String lastName;

    private String description;

    private String avatarId;

    private String email;

    private String skypeId;

    private String googleId;

    private String instantMessageId;

    private String jobTitle;

    private String location;

    private CompanyDetails company;

    private String mobile;

    private String telephone;

    private String statusUpdatedAt;

    private String userStatus;

    private Boolean enabled;

    private Boolean emailNotificationsEnabled;

    private List<String> aspectNames;

    private Map<String, Object> properties;

    // TODO What kind of values can capabilities contain? Documentation provides no information
    private Map<String, Object> capabilities;

    /**
     * Creates a new instance of this value class.
     */
    public CommonPersonDetails()
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
    public CommonPersonDetails(final CommonPersonDetails reference)
    {
        this.id = reference.getId();
        this.firstName = reference.getFirstName();
        this.lastName = reference.getLastName();
        this.description = reference.getDescription();
        this.avatarId = reference.getAvatarId();
        this.email = reference.getEmail();
        this.skypeId = reference.getSkypeId();
        this.googleId = reference.getGoogleId();
        this.instantMessageId = reference.getInstantMessageId();
        this.jobTitle = reference.getJobTitle();
        this.location = reference.getLocation();

        final CompanyDetails company = reference.getCompany();
        if (company != null)
        {
            this.company = new CompanyDetails(company);
        }

        this.mobile = reference.getMobile();
        this.telephone = reference.getTelephone();
        this.statusUpdatedAt = reference.getStatusUpdatedAt();
        this.userStatus = reference.getUserStatus();
        this.enabled = reference.getEnabled();
        this.emailNotificationsEnabled = reference.getEmailNotificationsEnabled();

        final List<String> aspectNames = reference.getAspectNames();
        if (aspectNames != null)
        {
            this.aspectNames = new ArrayList<>(aspectNames);
        }
        final Map<String, Object> properties = reference.getProperties();
        if (properties != null)
        {
            this.properties = new HashMap<>(properties);
        }
        final Map<String, Object> capabilities = reference.getCapabilities();
        if (capabilities != null)
        {
            this.capabilities = new HashMap<>(capabilities);
        }
    }

    /**
     * @return the id
     */
    public String getId()
    {
        return this.id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final String id)
    {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName()
    {
        return this.firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(final String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName()
    {
        return this.lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(final String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(final String description)
    {
        this.description = description;
    }

    /**
     * @return the avatarId
     */
    public String getAvatarId()
    {
        return this.avatarId;
    }

    /**
     * @param avatarId
     *            the avatarId to set
     */
    public void setAvatarId(final String avatarId)
    {
        this.avatarId = avatarId;
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

    /**
     * @return the skypeId
     */
    public String getSkypeId()
    {
        return this.skypeId;
    }

    /**
     * @param skypeId
     *            the skypeId to set
     */
    public void setSkypeId(final String skypeId)
    {
        this.skypeId = skypeId;
    }

    /**
     * @return the googleId
     */
    public String getGoogleId()
    {
        return this.googleId;
    }

    /**
     * @param googleId
     *            the googleId to set
     */
    public void setGoogleId(final String googleId)
    {
        this.googleId = googleId;
    }

    /**
     * @return the instantMessageId
     */
    public String getInstantMessageId()
    {
        return this.instantMessageId;
    }

    /**
     * @param instantMessageId
     *            the instantMessageId to set
     */
    public void setInstantMessageId(final String instantMessageId)
    {
        this.instantMessageId = instantMessageId;
    }

    /**
     * @return the jobTitle
     */
    public String getJobTitle()
    {
        return this.jobTitle;
    }

    /**
     * @param jobTitle
     *            the jobTitle to set
     */
    public void setJobTitle(final String jobTitle)
    {
        this.jobTitle = jobTitle;
    }

    /**
     * @return the location
     */
    public String getLocation()
    {
        return this.location;
    }

    /**
     * @param location
     *            the location to set
     */
    public void setLocation(final String location)
    {
        this.location = location;
    }

    /**
     * @return the company
     */
    public CompanyDetails getCompany()
    {
        return this.company;
    }

    /**
     * @param company
     *            the company to set
     */
    public void setCompany(final CompanyDetails company)
    {
        this.company = company;
    }

    /**
     * @return the mobile
     */
    public String getMobile()
    {
        return this.mobile;
    }

    /**
     * @param mobile
     *            the mobile to set
     */
    public void setMobile(final String mobile)
    {
        this.mobile = mobile;
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
     * @return the statusUpdatedAt
     */
    public String getStatusUpdatedAt()
    {
        return this.statusUpdatedAt;
    }

    /**
     * @param statusUpdatedAt
     *            the statusUpdatedAt to set
     */
    public void setStatusUpdatedAt(final String statusUpdatedAt)
    {
        this.statusUpdatedAt = statusUpdatedAt;
    }

    /**
     * @return the userStatus
     */
    public String getUserStatus()
    {
        return this.userStatus;
    }

    /**
     * @param userStatus
     *            the userStatus to set
     */
    public void setUserStatus(final String userStatus)
    {
        this.userStatus = userStatus;
    }

    /**
     * @return the enabled
     */
    public Boolean getEnabled()
    {
        return this.enabled;
    }

    /**
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(final Boolean enabled)
    {
        this.enabled = enabled;
    }

    /**
     * @return the emailNotificationsEnabled
     */
    public Boolean getEmailNotificationsEnabled()
    {
        return this.emailNotificationsEnabled;
    }

    /**
     * @param emailNotificationsEnabled
     *            the emailNotificationsEnabled to set
     */
    public void setEmailNotificationsEnabled(final Boolean emailNotificationsEnabled)
    {
        this.emailNotificationsEnabled = emailNotificationsEnabled;
    }

    /**
     * @return the aspectNames
     */
    public List<String> getAspectNames()
    {
        return this.aspectNames;
    }

    /**
     * @param aspectNames
     *            the aspectNames to set
     */
    public void setAspectNames(final List<String> aspectNames)
    {
        this.aspectNames = aspectNames;
    }

    /**
     * @return the properties
     */
    public Map<String, Object> getProperties()
    {
        return this.properties;
    }

    /**
     * @param properties
     *            the properties to set
     */
    public void setProperties(final Map<String, Object> properties)
    {
        this.properties = properties;
    }

    /**
     * @return the capabilities
     */
    public Map<String, Object> getCapabilities()
    {
        return this.capabilities;
    }

    /**
     * @param capabilities
     *            the capabilities to set
     */
    public void setCapabilities(final Map<String, Object> capabilities)
    {
        this.capabilities = capabilities;
    }

}
