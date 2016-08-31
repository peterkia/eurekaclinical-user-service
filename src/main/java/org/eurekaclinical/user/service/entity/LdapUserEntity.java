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
package org.eurekaclinical.user.service.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author miaoai
 */
@Entity
@Table(name = "ldap_users")
public class LdapUserEntity extends UserEntity {
	
	public LdapUserEntity(LoginTypeEntity loginType, AuthenticationMethodEntity authenticationMethod) {
		super(loginType, authenticationMethod);
	}
	
	protected LdapUserEntity() {
		
	}

	@Override
	public void accept(UserEntityVisitor userEntityVisitor) {
		userEntityVisitor.visit(this);
	}
}
