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

    private String mimetype;

    private String mimetypeName;

    private Long sizeInBytes;

    private String encoding;

    /**
     * @return the mimetype
     */
    public String getMimetype()
    {
        return this.mimetype;
    }

    /**
     * @param mimetype
     *            the mimetype to set
     */
    public void setMimetype(final String mimetype)
    {
        this.mimetype = mimetype;
    }

    /**
     * @return the mimetypeName
     */
    public String getMimetypeName()
    {
        return this.mimetypeName;
    }

    /**
     * @param mimetypeName
     *            the mimetypeName to set
     */
    public void setMimetypeName(final String mimetypeName)
    {
        this.mimetypeName = mimetypeName;
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
