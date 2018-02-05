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
import org.eurekaclinical.scribeupext.provider.GitHubProvider;

import org.eurekaclinical.user.service.config.UserServiceProperties;

/**
 * A provider for injecting a scribe-up GitHub OAuth provider.
 * 
 * @author miaoai, Andrew Post
 */
public class ScribeExtGitHubProvider extends AbstractOAuthProvider<GitHubProvider> {
    
    /**
     * Creates the injection provider for the GitHub OAuth provider.
     * 
     * @param inProperties the user service properties.
     */
    @Inject
    public ScribeExtGitHubProvider(UserServiceProperties inProperties) {
        super(inProperties, GitHubProvider.class, inProperties.getGitHubCallbackUrl());
    }

    /**
     * Gets the GitHub OAuth key.
     * 
     * @return the key string.
     */
    @Override
    String getKey() {
        return getProperties().getGitHubOAuthKey();
    }

    /**
     * Gets the GitHub OAuth secret.
     * 
     * @return the secret string.
     */
    @Override
    String getSecret() {
        return getProperties().getGitHubOAuthSecret();
    }

}
