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
public class PathInfo
{

    // TODO copy constructors

    private String name;

    private Boolean isComplete;

    private List<PathNodeEntity> elements;

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
     * @return the isComplete
     */
    public Boolean getIsComplete()
    {
        return this.isComplete;
    }

    /**
     * @param isComplete
     *            the isComplete to set
     */
    public void setIsComplete(final Boolean isComplete)
    {
        this.isComplete = isComplete;
    }

    /**
     * @return the elements
     */
    public List<PathNodeEntity> getElements()
    {
        return this.elements != null ? new ArrayList<>(this.elements) : null;
    }

    /**
     * @param elements
     *            the elements to set
     */
    public void setElements(final List<PathNodeEntity> elements)
    {
        this.elements = elements != null ? new ArrayList<>(elements) : null;
    }

}
