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
package org.eurekaclinical.user.services.dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.eurekaclinical.eureka.client.comm.authentication.AuthenticationMethod;
import org.eurekaclinical.user.common.entity.AuthenticationMethodEntity;
import org.eurekaclinical.user.common.entity.AuthenticationMethodEntity_;

import javax.persistence.EntityManager;
import org.eurekaclinical.standardapis.dao.GenericDao;

/**
 *
 */
public class JpaAuthenticationMethodDao extends
		GenericDao<AuthenticationMethodEntity, Long> implements AuthenticationMethodDao {

	@Inject
	protected JpaAuthenticationMethodDao(Provider<EntityManager>
															 inManagerProvider) {
		super(AuthenticationMethodEntity.class, inManagerProvider);
	}

	@Override
	public AuthenticationMethodEntity getByName(AuthenticationMethod inName) {
		return this.getUniqueByAttribute(AuthenticationMethodEntity_.name, inName);
	}
}
