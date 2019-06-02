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
package de.acosix.alfresco.rest.client.jaxrs;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Axel Faust
 */
public class BasicAuthenticationClientRequestFilter implements ClientRequestFilter
{

    private String userName;

    private String authentication;

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(final String userName)
    {
        this.userName = userName;
    }

    /**
     * @param authentication
     *            the authentication to set
     */
    public void setAuthentication(final String authentication)
    {
        this.authentication = authentication;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void filter(final ClientRequestContext requestContext) throws IOException
    {
        if (this.authentication != null)
        {
            final String effectiveAuthentication;
            if (this.userName != null)
            {
                effectiveAuthentication = this.userName + ":" + this.authentication;
            }
            else
            {
                effectiveAuthentication = this.authentication;
            }
            final String base64Token = Base64.encodeBase64String(effectiveAuthentication.getBytes(StandardCharsets.UTF_8));
            requestContext.getHeaders().add("Authorization", "Basic " + base64Token);
        }
    }

}
