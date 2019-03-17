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

import de.acosix.alfresco.rest.client.jackson.Wrapped;
import de.acosix.alfresco.rest.client.jackson.Wrapped.WrapType;
import de.acosix.alfresco.rest.client.model.common.UserInfo;

/**
 * @author Axel Faust
 */
@Wrapped(WrapType.ENTRY)
public class NodeResponseEntity extends CommonNodeEntity<ResponsePermissionsInfo>
{

    private String id;

    private Boolean isFolder;

    private Boolean isFile;

    private Boolean isLocked;

    private Boolean isLink;

    private Boolean isFavorite;

    private String createdAt;

    private UserInfo createdByUser;

    private String modifiedAt;

    private UserInfo modifiedByUser;

    private String parentId;

    private ContentInfo content;

    private List<String> allowableOperations;

    private PathInfo path;

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
     * @return the isFolder
     */
    public Boolean getIsFolder()
    {
        return this.isFolder;
    }

    /**
     * @param isFolder
     *            the isFolder to set
     */
    public void setIsFolder(final Boolean isFolder)
    {
        this.isFolder = isFolder;
    }

    /**
     * @return the isFile
     */
    public Boolean getIsFile()
    {
        return this.isFile;
    }

    /**
     * @param isFile
     *            the isFile to set
     */
    public void setIsFile(final Boolean isFile)
    {
        this.isFile = isFile;
    }

    /**
     * @return the isLocked
     */
    public Boolean getIsLocked()
    {
        return this.isLocked;
    }

    /**
     * @param isLocked
     *            the isLocked to set
     */
    public void setIsLocked(final Boolean isLocked)
    {
        this.isLocked = isLocked;
    }

    /**
     * @return the isLink
     */
    public Boolean getIsLink()
    {
        return this.isLink;
    }

    /**
     * @param isLink
     *            the isLink to set
     */
    public void setIsLink(final Boolean isLink)
    {
        this.isLink = isLink;
    }

    /**
     * @return the isFavorite
     */
    public Boolean getIsFavorite()
    {
        return this.isFavorite;
    }

    /**
     * @param isFavorite
     *            the isFavorite to set
     */
    public void setIsFavorite(final Boolean isFavorite)
    {
        this.isFavorite = isFavorite;
    }

    /**
     * @return the createdAt
     */
    public String getCreatedAt()
    {
        return this.createdAt;
    }

    /**
     * @param createdAt
     *            the createdAt to set
     */
    public void setCreatedAt(final String createdAt)
    {
        this.createdAt = createdAt;
    }

    /**
     * @return the createdByUser
     */
    public UserInfo getCreatedByUser()
    {
        return this.createdByUser;
    }

    /**
     * @param createdByUser
     *            the createdByUser to set
     */
    public void setCreatedByUser(final UserInfo createdByUser)
    {
        this.createdByUser = createdByUser;
    }

    /**
     * @return the modifiedAt
     */
    public String getModifiedAt()
    {
        return this.modifiedAt;
    }

    /**
     * @param modifiedAt
     *            the modifiedAt to set
     */
    public void setModifiedAt(final String modifiedAt)
    {
        this.modifiedAt = modifiedAt;
    }

    /**
     * @return the modifiedByUser
     */
    public UserInfo getModifiedByUser()
    {
        return this.modifiedByUser;
    }

    /**
     * @param modifiedByUser
     *            the modifiedByUser to set
     */
    public void setModifiedByUser(final UserInfo modifiedByUser)
    {
        this.modifiedByUser = modifiedByUser;
    }

    /**
     * @return the parentId
     */
    public String getParentId()
    {
        return this.parentId;
    }

    /**
     * @param parentId
     *            the parentId to set
     */
    public void setParentId(final String parentId)
    {
        this.parentId = parentId;
    }

    /**
     * @return the content
     */
    public ContentInfo getContent()
    {
        return this.content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(final ContentInfo content)
    {
        this.content = content;
    }

    /**
     * @return the allowableOperations
     */
    public List<String> getAllowableOperations()
    {
        return this.allowableOperations != null ? new ArrayList<>(this.allowableOperations) : null;
    }

    /**
     * @param allowableOperations
     *            the allowableOperations to set
     */
    public void setAllowableOperations(final List<String> allowableOperations)
    {
        this.allowableOperations = allowableOperations != null ? new ArrayList<>(allowableOperations) : null;
    }

    /**
     * @return the path
     */
    public PathInfo getPath()
    {
        return this.path;
    }

    /**
     * @param path
     *            the path to set
     */
    public void setPath(final PathInfo path)
    {
        this.path = path;
    }

}
