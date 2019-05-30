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
public class ContentInfo
{

    private String mimeType;

    private String mimeTypeName;

    private Long sizeInBytes;

    private String encoding;

    /**
     * Creates a new instance of this value class.
     */
    public ContentInfo()
    {
        // NO-OP
    }

    /**
     * Creates a new instance of this value class as a full (recursive) copy of the provided reference / template.
     *
     * @param reference
     *            the reference / template for the new instance
     */
    public ContentInfo(final ContentInfo reference)
    {
        this.mimeType = reference.getMimeType();
        this.mimeTypeName = reference.getMimeTypeName();
        this.sizeInBytes = reference.getSizeInBytes();
        this.encoding = reference.getEncoding();
    }

    /**
     * @return the mimeType
     */
    public String getMimeType()
    {
        return this.mimeType;
    }

    /**
     * @param mimeType
     *            the mimeType to set
     */
    public void setMimeType(final String mimeType)
    {
        this.mimeType = mimeType;
    }

    /**
     * @return the mimeTypeName
     */
    public String getMimeTypeName()
    {
        return this.mimeTypeName;
    }

    /**
     * @param mimeTypeName
     *            the mimeTypeName to set
     */
    public void setMimeTypeName(final String mimeTypeName)
    {
        this.mimeTypeName = mimeTypeName;
    }

    /**
     * @return the sizeInBytes
     */
    public Long getSizeInBytes()
    {
        return this.sizeInBytes;
    }

    /**
     * @param sizeInBytes
     *            the sizeInBytes to set
     */
    public void setSizeInBytes(final Long sizeInBytes)
    {
        this.sizeInBytes = sizeInBytes;
    }

    /**
     * @return the encoding
     */
    public String getEncoding()
    {
        return this.encoding;
    }

    /**
     * @param encoding
     *            the encoding to set
     */
    public void setEncoding(final String encoding)
    {
        this.encoding = encoding;
    }

}
