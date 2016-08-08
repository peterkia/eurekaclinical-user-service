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
package org.eurekaclinical.user.services.config;

import java.util.Map;
import org.eurekaclinical.common.config.AbstractAuthorizingJerseyServletModuleWithPersist;

/**
 * Configure web related items for Guice and Jersey.
 * 
 * @author miaoai
 */
public class ServletModule extends AbstractAuthorizingJerseyServletModuleWithPersist {

	private static final String PACKAGE_NAMES = "org.eurekaclinical.user.services.resource;org.eurekaclinical.user.common.json";
	private final UserServiceProperties properties;

	public ServletModule(UserServiceProperties inProperties) {
		super(inProperties, PACKAGE_NAMES);
		this.properties = inProperties;
	}    
    
	@Override
	public Map<String, String> getCasValidationFilterInitParams() {
		Map<String, String> params = super.getCasValidationFilterInitParams();
		params.put("proxyCallbackUrl", getCasProxyCallbackUrl());
		params.put("proxyReceptorUrl", getCasProxyCallbackPath());
		return params;
	}
    
}
