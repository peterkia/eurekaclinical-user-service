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
package org.eurekaclinical.user.service.dao;

import com.google.inject.Inject;
import com.google.inject.Provider;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.persistence.EntityManager;

import org.eurekaclinical.standardapis.dao.GenericDao;

import org.eurekaclinical.user.service.entity.LocalUserEntity;
import org.eurekaclinical.user.service.entity.LocalUserEntity_;
import org.eurekaclinical.user.service.entity.UserEntity;
import org.eurekaclinical.user.service.entity.UserEntity_;
/**
 * An implementation of the {@link UserDao} interface, backed by JPA entities
 * and queries.
 *
 * @author miaoai
 */
public class JpaLocalUserDao extends GenericDao<LocalUserEntity, Long> implements LocalUserDao {

	/**
	 * Create an object with the give entity manager.
	 *
	 * @param inEMProvider The entity manager to be used for communication with
	 *                     the data store.
	 */
	@Inject
	public JpaLocalUserDao(Provider<EntityManager> inEMProvider) {
		super(LocalUserEntity.class, inEMProvider);
	}
	
	@Override
	public LocalUserEntity getByName(String name) {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<LocalUserEntity> criteriaQuery = builder.createQuery(LocalUserEntity.class);
		Root<LocalUserEntity> root = criteriaQuery.from(LocalUserEntity.class);
		
		Subquery<UserEntity> subquery = 
				criteriaQuery.subquery(UserEntity.class);
		Root<UserEntity> userEntity = subquery.from(UserEntity.class);
		Path<String> username = userEntity.get(UserEntity_.username);
		subquery.select(userEntity);
		subquery = subquery.where(builder.equal(username, name));
		
		TypedQuery<LocalUserEntity> query = 
				this.getEntityManager().createQuery(criteriaQuery.where(
				builder.equal(root, subquery)));
		return query.getSingleResult();
	}

	@Override
	public LocalUserEntity getByVerificationCode(String inCode) {
		return this.getUniqueByAttribute(LocalUserEntity_.verificationCode, inCode);
	}
	
}
