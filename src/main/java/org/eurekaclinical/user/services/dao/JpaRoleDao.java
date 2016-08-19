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

import javax.persistence.EntityManager;


import com.google.inject.Inject;
import com.google.inject.Provider;

import org.eurekaclinical.user.common.entity.RoleEntity;
import org.eurekaclinical.standardapis.dao.AbstractJpaRoleDao;

/**
 * A {@link RoleDao} implementation, backed by JPA entities and queries.
 *
 * @author hrathod
 *
 */
public class JpaRoleDao extends AbstractJpaRoleDao<RoleEntity> implements RoleDao {

	/**
	 * Create a new object with the given entity manager.
	 *
	 * @param inManagerProvider A provider for entity manager instances.
	 */
	@Inject
	public JpaRoleDao(Provider<EntityManager> inManagerProvider) {
		super(RoleEntity.class, inManagerProvider);
	}

}
