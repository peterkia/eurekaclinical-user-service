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
package org.eurekaclinical.user.service.resource;

import javax.ws.rs.Path;
import javax.inject.Inject;
import com.google.inject.persist.Transactional;

import org.eurekaclinical.common.comm.Role;
import org.eurekaclinical.common.resource.AbstractRoleResource;

import org.eurekaclinical.user.common.entity.RoleEntity;

import org.eurekaclinical.user.service.dao.RoleDao;
/**
 * A RESTful end-point for working with {@link RoleEntity} objects.
 *
 * @author miaoai
 *
 */
@Transactional
@Path("/protected/roles")
public class RoleResource extends AbstractRoleResource<RoleEntity, Role> {

	/**
	 * Create a RoleResource object with the given {@link RoleDao}
	 *
	 * @param inRoleDao The RoleDao object used to work with role objects in the
	 *            data store.
	 */
	@Inject
	public RoleResource(RoleDao inRoleDao) {
		super(inRoleDao);
	}

	@Override
	protected Role toRole(RoleEntity roleEntity) {
		Role role = new Role();
		role.setId(roleEntity.getId());
		role.setName(roleEntity.getName());
		return role;
	}

}
