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
package org.eurekaclinical.user.service.resource;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.eurekaclinical.scribeupext.profile.GitHubProfile;
import org.eurekaclinical.scribeupext.provider.GitHubProvider;
import org.eurekaclinical.user.service.util.AbstractOAuthRegistrationUserResource;
import org.eurekaclinical.user.service.util.RegistrationOAuthUserProfile;


/**
 *
 * @author miaoai
 */
@Path("/oauthuser/github")

public class GitHubRegistrationOAuthUserResource extends AbstractOAuthRegistrationUserResource<GitHubProfile> {

    @Context
    HttpServletRequest request;
    
    @Inject
    public GitHubRegistrationOAuthUserResource(GitHubProvider inProvider) {
        super(inProvider);
    }
    
  
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RegistrationOAuthUserProfile getProfile(){
        
        return super.getRegistrationOAuthProfile(this.request);
        
        
    }

}
