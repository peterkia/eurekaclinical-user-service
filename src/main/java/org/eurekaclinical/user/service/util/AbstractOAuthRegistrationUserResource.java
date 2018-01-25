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
package org.eurekaclinical.user.service.util;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.scribe.up.provider.OAuthProvider;

import org.eurekaclinical.scribeupext.profile.EurekaProfile;
import org.eurekaclinical.standardapis.exception.HttpStatusException;
import org.scribe.up.credential.OAuthCredential;
import org.scribe.up.session.HttpUserSession;

/**
 *
 * @author miaoai
 */
public class AbstractOAuthRegistrationUserResource<E extends EurekaProfile> {

    private final OAuthProvider provider;
    
    

    public AbstractOAuthRegistrationUserResource(OAuthProvider oauthProvider) {
        assert oauthProvider != null : "provider cannot be null";
        this.provider = oauthProvider;
    }
    
    
    
    public E getEurekaProfile(HttpServletRequest req) throws HttpStatusException {
        assert req != null : "request cannot be null";
        OAuthCredential credential
                = this.provider.getCredential(
                        new HttpUserSession(req.getSession()),
                        req.getParameterMap());
        if(credential ==null){
            throw new HttpStatusException(Response.Status.BAD_REQUEST,
					"No valid oauth credential found");
        }
        
        E profile = (E) this.provider.getUserProfile(credential); 
        if(profile ==null){
            throw new HttpStatusException(Response.Status.NOT_FOUND,
                                    "Couldn't retrieve user profile");
        }
        
        
        return profile;

    }
    
    public RegistrationOAuthUserProfile getRegistrationOAuthProfile(HttpServletRequest req) throws HttpStatusException {
        RegistrationOAuthUserProfile oauthProfile = new RegistrationOAuthUserProfile();
        E profile = this.getEurekaProfile(req);
        
        String fullName = profile.getDisplayName();
        
        String firstName = profile.getFirstName();
        String lastName = profile.getFamilyName();

        if ((firstName == null || lastName == null) && fullName != null) {
            PersonNameSplitter splitter
                    = new PersonNameSplitter(fullName);
            if (firstName == null) {
                firstName = splitter.getFirstName();
            }
            if (lastName == null) {
                lastName = splitter.getLastName();
            }
        }

        
        oauthProfile.setFirstName(firstName);
        oauthProfile.setLastName(lastName);
        oauthProfile.setUserName(profile.getTypedId());
        oauthProfile.setEmail(profile.getEmail());
        oauthProfile.setProviderUsername(profile.getUsername());        
        oauthProfile.setProvider(this.provider.getType());
        
        return oauthProfile;
        
    }
    

}
