/*
 * #%L
 * Eureka! Clinical User Webapp
 * %%
 * Copyright (C) 2016 Emory University
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.eurekaclinical.user.service.provider;

import javax.inject.Inject;
import org.eurekaclinical.scribeupext.provider.GlobusProvider;

import org.eurekaclinical.user.service.config.UserServiceProperties;

/**
 * A provider for injecting a scribe-up Globus OAuth provider. It is annotated
 * as a singleton for now because the Eureka! Clinical property mechanism is
 * read-only, but that may change in the future.
 * 
 * @author miaoai, Andrew Post
 */
public class ScribeExtGlobusProvider extends AbstractOAuthProvider<GlobusProvider> {

    /**
     * Creates the injection provider for the Globus OAuth provider.
     * 
     * @param inProperties the user webapp properties.
     */
    @Inject
    public ScribeExtGlobusProvider(UserServiceProperties inProperties) {
        super(inProperties, GlobusProvider.class, inProperties.getGlobusCallbackUrl());
    }

    /**
     * Gets the Globus OAuth key.
     * 
     * @return the key string.
     */
    @Override
    String getKey() {
        return getProperties().getGlobusOAuthKey();
    }

    /**
     * Gets the Globus OAuth secret.
     * 
     * @return the secret string.
     */
    @Override
    String getSecret() {
        return getProperties().getGlobusOAuthSecret();
    }
    
}
