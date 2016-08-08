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

import com.google.inject.AbstractModule;
import javax.naming.Context;
import javax.naming.InitialContext;


import com.google.inject.TypeLiteral;
import com.google.inject.jndi.JndiIntegration;
import javax.mail.Session;
import org.eurekaclinical.common.comm.clients.WebResourceWrapperFactory;
import org.eurekaclinical.common.comm.clients.cassupport.CasWebResourceWrapperFactory;

import org.eurekaclinical.standardapis.dao.UserDao;
import org.eurekaclinical.standardapis.entity.RoleEntity;
import org.eurekaclinical.standardapis.entity.UserEntity;
import org.eurekaclinical.user.services.clients.I2b2Client;
import org.eurekaclinical.user.services.clients.I2b2RestClient;
import org.eurekaclinical.user.services.dao.AuthenticationMethodDao;
import org.eurekaclinical.user.services.dao.JpaAuthenticationMethodDao;
import org.eurekaclinical.user.services.dao.JpaLocalUserDao;
import org.eurekaclinical.user.services.dao.JpaLoginTypeDao;
import org.eurekaclinical.user.services.dao.JpaOAuthProviderDao;
import org.eurekaclinical.user.services.dao.JpaRoleDao;
import org.eurekaclinical.user.services.dao.JpaUserDao;
import org.eurekaclinical.user.services.dao.LocalUserDao;
import org.eurekaclinical.user.services.dao.LoginTypeDao;
import org.eurekaclinical.user.services.dao.OAuthProviderDao;
import org.eurekaclinical.user.services.dao.RoleDao;
import org.eurekaclinical.user.services.email.EmailSender;
import org.eurekaclinical.user.services.email.FreeMarkerEmailSender;

/**
 * Configure all the non-web related binding for Guice.
 * 
 * @author miaoai
 */
public class AppModule extends AbstractModule {

	AppModule() {
	}

	@Override
	protected void configure() {
		bind(I2b2Client.class).to(I2b2RestClient.class);        
		bind(new TypeLiteral<UserDao<? extends UserEntity<? extends RoleEntity>>>() {}).to(JpaUserDao.class);
		bind(org.eurekaclinical.user.services.dao.UserDao.class).to(JpaUserDao.class);
		bind(LocalUserDao.class).to(JpaLocalUserDao.class);
		bind(RoleDao.class).to(JpaRoleDao.class);
		bind(OAuthProviderDao.class).to(JpaOAuthProviderDao.class);
		bind(AuthenticationMethodDao.class).to(JpaAuthenticationMethodDao.class);
		bind(LoginTypeDao.class).to(JpaLoginTypeDao.class);
		bind(EmailSender.class).to(FreeMarkerEmailSender.class);


		bind(Context.class).to(InitialContext.class);
		bind(Session.class).toProvider(
				JndiIntegration.fromJndi(Session.class,
						"java:comp/env/mail/Session"));                
		//bind(PasswordGenerator.class).to(PasswordGeneratorImpl.class);    no need? 

		bind(WebResourceWrapperFactory.class).to(CasWebResourceWrapperFactory.class);
        }
    
}
