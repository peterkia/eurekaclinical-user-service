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
package org.eurekaclinical.user.services.clients;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.google.inject.Inject;

import org.eurekaclinical.user.common.comm.clients.UserClient;

import org.eurekaclinical.user.services.config.UserServiceProperties;

import org.eurekaclinical.standardapis.exception.HttpStatusException;

public class I2b2RestClient extends UserClient implements I2b2Client {


	private static final Logger LOGGER = LoggerFactory
			.getLogger(I2b2RestClient.class);
	private final UserServiceProperties userServiceProperties;
        private  String password;

	@Inject
	public I2b2RestClient(UserServiceProperties inProperties) {
		super();
		this.userServiceProperties = inProperties;
                this.password = "";
	}

	@Override
	protected String getResourceUrl() {
		return this.userServiceProperties.getI2b2URL();
	}

	@Override
	public void changePassword(String email, String passwd) throws HttpStatusException {
		this.password = passwd;

	}

}
