/*-
 * #%L
 * Eureka! Clinical User Services
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
package org.eurekaclinical.user.service.config;

import com.google.inject.Singleton;
import org.eurekaclinical.standardapis.props.CasJerseyEurekaClinicalProperties;

/**
 *
 * @author miaoai
 */
@Singleton
public class UserServiceProperties extends CasJerseyEurekaClinicalProperties {

    public UserServiceProperties() {
        super("/etc/ec-user");
    }

    @Override
    public String getProxyCallbackServer() {
        return getValue("eurekaclinical.userservice.callbackserver");
    }

    /**
     * Get the verification base URL, to be used in sending a verification email
     * to the user.
     *
     * @return The verification base URL, as found in the application
     * configuration file.
     */
    public String getVerificationUrl() {
        String verUrl = getUserWebappUrl();
        return verUrl + (verUrl.endsWith("/") ? "" : "/") + "verify?code=";
    }

    /**
     * Get the verification email subject line.
     *
     * @return The subject for the verification email.
     */
    public String getVerificationEmailSubject() {
        return getValue(
                "eurekaclinical.userservice.email.verify.subject");
    }

    /**
     * Get the activation email subject line.
     *
     * @return The subject for the activation email.
     */
    public String getActivationEmailSubject() {
        return getValue(
                "eurekaclinical.userservice.email.activation.subject");
    }

    /**
     * Get the password change email subject line.
     *
     * @return The email subject line.
     */
    public String getPasswordChangeEmailSubject() {
        return getValue(
                "eurekaclinical.userservice.email.password.subject");
    }

    /**
     * Gets the subject line for a password reset email.
     *
     * @return The subject line.
     */
    public String getPasswordResetEmailSubject() {
        return getValue("eurekaclinical.userservice.email.reset.subject");
    }

    /**
     * Get email address in the From header.
     *
     * @return an email address.
     */
    public String getFromEmailAddress() {
        return getValue("eurekaclinical.userservice.email.from");
    }

    @Override
    public String getUrl() {
        return getValue("eurekaclinical.userservice.url");
    }

    public String getUserWebappUrl() {
        return getValue("eurekaclinical.userwebapp.url");
    }

    /**
     * Get the number of hours to keep a user registration without verification,
     * before deleting it from the database.
     *
     * @return The number of hours listed in the configuration, and 72 if the
     * configuration is not found.
     */
    public int getRegistrationTimeout() {
        return getIntValue("eurekaclinical.userservice.registration.timeout", 72);
    }
    
    
    public boolean isOAuthRegistrationEnabled() {
        return isGoogleOAuthRegistrationEnabled() || isGitHubOAuthRegistrationEnabled() || isGlobusOAuthRegistrationEnabled();
    }

    public boolean isGoogleOAuthRegistrationEnabled() {
        return getGoogleOAuthKey() != null && getGoogleOAuthSecret() != null;
    }

    public boolean isGitHubOAuthRegistrationEnabled() {
        return getGitHubOAuthKey() != null && getGitHubOAuthSecret() != null;
    }

    public boolean isGlobusOAuthRegistrationEnabled() {
        return getGlobusOAuthKey() != null && getGlobusOAuthSecret() != null;
    }

    public boolean isLocalAccountRegistrationEnabled() {
        return Boolean.parseBoolean(getValue("eurekaclinical.userwebapp.localregistrationenabled"));
    }

    public boolean isRegistrationEnabled() {
        return isLocalAccountRegistrationEnabled() || isOAuthRegistrationEnabled();
    }

    public String getGitHubCallbackUrl() {
        return getValue("eurekaclinical.userwebapp.githuboauthcallbackurl");
    }
    
    public String getGitHubOAuthKey() {
        return getValue("eurekaclinical.userwebapp.githuboauthkey");
    }

    public String getGitHubOAuthSecret() {
        return getValue("eurekaclinical.userwebapp.githuboauthsecret");
    }

    public String getGlobusCallbackUrl() {
        return getValue("eurekaclinical.userwebapp.globusoauthcallbackurl");
    }
    
    public String getGlobusOAuthKey() {
        return getValue("eurekaclinical.userwebapp.globusoauthkey");
    }

    public String getGlobusOAuthSecret() {
        return getValue("eurekaclinical.userwebapp.globusoauthsecret");
    }

    public String getGoogleCallbackUrl() {
        return getValue("eurekaclinical.userwebapp.googleoauthcallbackurl");
    }
    
    public String getGoogleOAuthKey() {
        return getValue("eurekaclinical.userwebapp.googleoauthkey");
    }

    public String getGoogleOAuthSecret() {
        return getValue("eurekaclinical.userwebapp.googleoauthsecret");
    }
}
