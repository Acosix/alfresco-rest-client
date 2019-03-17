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
public class NodeCoreIdentity
{
    // TODO copy constructors

    private String name;

    private String nodeType;

    private List<String> aspectNames;

    /**
     * @return the name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * @return the nodeType
     */
    public String getNodeType()
    {
        return this.nodeType;
    }

    /**
     * @param nodeType
     *            the nodeType to set
     */
    public void setNodeType(final String nodeType)
    {
        this.nodeType = nodeType;
    }

    /**
     * @return the aspectNames
     */
    public List<String> getAspectNames()
    {
        return this.aspectNames != null ? new ArrayList<>(this.aspectNames) : null;
    }

    /**
     * @param aspectNames
     *            the aspectNames to set
     */
    public void setAspectNames(final List<String> aspectNames)
    {
        this.aspectNames = aspectNames != null ? new ArrayList<>(aspectNames) : null;
    }

}
