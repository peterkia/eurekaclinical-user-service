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
		return this.getValue("eurekaclinical.userservice.callbackserver");
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
		return this.getValue(
				"eurekaclinical.userservice.email.verify.subject");
	}

	/**
	 * Get the activation email subject line.
	 *
	 * @return The subject for the activation email.
	 */
	public String getActivationEmailSubject() {
		return this.getValue(
				"eurekaclinical.userservice.email.activation.subject");
	}

	/**
	 * Get the password change email subject line.
	 *
	 * @return The email subject line.
	 */
	public String getPasswordChangeEmailSubject() {
		return this.getValue(
				"eurekaclinical.userservice.email.password.subject");
	}

	/**
	 * Gets the subject line for a password reset email.
	 *
	 * @return The subject line.
	 */
	public String getPasswordResetEmailSubject() {
		return this.getValue("eurekaclinical.userservice.email.reset.subject");
	}

	/**
	 * Get email address in the From header.
	 *
	 * @return an email address.
	 */
	public String getFromEmailAddress() {
		return this.getValue("eurekaclinical.userservice.email.from");
	}

	@Override
	public String getUrl() {
		return getValue("eurekaclinical.userservice.url");
	}

	public String getUserWebappUrl() {
		return getValue("eurekaclinical.userwebapp.url");
	}
}
