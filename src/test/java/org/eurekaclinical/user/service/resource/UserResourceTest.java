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

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.WebResource;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.List;
import org.eurekaclinical.user.client.comm.LocalUserRequest;
import org.eurekaclinical.user.client.comm.PasswordChangeRequest;
import org.eurekaclinical.user.client.comm.User;

import static org.junit.Assert.assertEquals;

/**
 * Test cases related to the {@link UserResource} class.
 *
 * @author hrathod
 *
 */
public class UserResourceTest extends AbstractServiceResourceTest {

	/**
	 * Simply call super().
	 */
	public UserResourceTest() {
		super();
	}

	/**
	 * Test that proper number of users are returned from the resource.
	 */
	@Test
	public void testUserList() {
		List<User> users = this.getUserList();
		assertEquals(2, users.size());
	}
	
	/**
	 * Test that a new user request sent to the resource returns a proper OK
	 * response.
	 */
	@Test
	public void testUserRequest() {
		WebResource webResource = this.resource();

		String email = "test@emory.edu";
		String username = "userservicetestuser";
		String verifyEmail = "test@emory.edu";
		String firstName = "Joe";
		String lastName = "Schmoe";
		String organization = "Emory University";
		String password = "password";
		String verifyPassword = "password";
		String title = "Software Engineer";
		String department = "CCI";

		LocalUserRequest userRequest = new LocalUserRequest();

		userRequest.setFirstName(firstName);
		userRequest.setLastName(lastName);
		userRequest.setUsername(username);
		userRequest.setEmail(email);
		userRequest.setVerifyEmail(verifyEmail);
		userRequest.setOrganization(organization);
		userRequest.setPassword(password);
		userRequest.setVerifyPassword(verifyPassword);
		userRequest.setDepartment(department);
		userRequest.setTitle(title);

		ClientResponse response = webResource.path("/api/userrequests")
				.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, userRequest);

		assertEquals(Status.CREATED, response.getClientResponseStatus());

	}

	/**
	 * Test the password changing functionality.
	 */
	@Test
	public void testPasswordChange() {
		WebResource resource = this.resource();
		PasswordChangeRequest request = new PasswordChangeRequest();
		request.setOldPassword("testpassword");
		request.setNewPassword("newPassword");
		ClientResponse response = resource
				.path("/api/protected/users/passwordchange")
				.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, request);
		assertEquals(Status.NO_CONTENT, response.getClientResponseStatus());
	}

	/**
	 * Test the "find user by name" functionality in UserResource.
	 */
	@Test
	public void testFindByName() {
		WebResource resource = this.resource();
		List<User> users = this.getUserList();
		User user = users.get(0);
		ClientResponse response = 
				resource.path("/api/protected/users/me")
						.accept(MediaType.APPLICATION_JSON)
						.get(ClientResponse.class);
		assertEquals(Status.OK, response.getClientResponseStatus());

		User responseUser = response.getEntity(User.class);
		assertEquals(user.getEmail(), responseUser.getEmail());
	}
}
