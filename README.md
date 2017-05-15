# Eureka! Clinical User Service
RESTful APIs for managing user accounts

## Version 1.0 development series
Latest release: [![Latest release](https://maven-badges.herokuapp.com/maven-central/org.eurekaclinical/eurekaclinical-user-service/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.eurekaclinical/eurekaclinical-user-service)

## Version history
No final releases yet

## What does it do?
It provides RESTful APIs for users to request an account, manage their profile and change their password. It also provides APIs for an administrator to create accounts.

## REST endpoints
### `/api/userrequests`
Manages user account requests.

#### Role-based authorization
None

#### Requires successful authentication
No

#### UserRequest object
Properties:
* `id`: unique number identifying the request (set by the server on object creation, and required thereafter).
* `username`: required username string.
* `firstName`: optional first name string.
* `lastName`: optional last name string.
* `fullName`: optional full name string.
* `email`: optional email address string.
* `verifyEmail`: the same email address string. Must match the value of `email`.
* `organization`: optional organization string.
* `title`: optional title.
* `department`: optional department.
* `loginType`: required unique string indicating the login screen that is requested:
  * `INTERNAL`: using Eureka! Clinical's login screen.
  * `PROVIDER`: using a trusted third party provider's login mechanism.
* `type`: required unique string indicating requested authentication method:
  * `LOCAL`: Eureka! Clinical's authentication mechanism.
  * `OAUTH`: An OAuth provider.
  * `LDAP`: An LDAP server.

#### Calls
##### POST /api/userrequests
Requests a user account. The UserRequest object is passed in as the body of the request. Returns the URI of the created UserRequest object. Uses status codes as specified in the [Eureka! Clinical microservice specification](https://github.com/eurekaclinical/dev-wiki/wiki/Eureka%21-Clinical-microservice-specification).

##### PUT /api/userrequests/verify/{code}
Marks the user as verified. The code to specify is provided to the user in an email.

### `/api/protected/users`
Manages created user accounts.

#### Role-based authorization
Call-dependent

#### Requires successful authentication
Yes

#### User object
Properties:
* `id`: unique number identifying the user (set by the server on object creation, and required thereafter).
* `username`: required username string.
* `firstName`: optional first name string.
* `lastName`: optional last name string.
* `fullName`: optional full name string.
* `email`: optional email address string.
* `organization`: optional organization string.
* `title`: optional title.
* `department`: optional department.
* `loginType`: required unique string indicating the login screen that is requested:
  * `INTERNAL`: using Eureka! Clinical's login screen.
  * `PROVIDER`: using a trusted third party provider's login mechanism.
* `type`: required unique string indicating requested authentication method:
  * `LOCAL`: Eureka! Clinical's authentication mechanism.
  * `OAUTH`: An OAuth provider.
  * `LDAP`: An LDAP server.
* `created`: required the timestamp string indicating when the account was created.
* `active`: required boolean indicating whether the account is active.

#### Calls
All calls use standard names, return values and status codes as specified in the [Eureka! Clinical microservice specification](https://github.com/eurekaclinical/dev-wiki/wiki/Eureka%21-Clinical-microservice-specification)

##### GET `/api/protected/users`
Returns an array of all User objects. Requires the `admin` role.

##### GET `/api/protected/users/{id}`
Returns a specified User object by the value of its id property, which is unique. Requires either the `researcher` role or the `admin` role.

##### GET `/api/protected/users/me`
Returns the User object for the currently authenticated user. Requires either the `researcher` role or the `admin` role.

##### POST `/api/protected/users/`
Creates a new user. The User object is passed in as the body of the request. Returns the URI of the created User object. Requires the `admin` role.

##### PUT `/api/protected/users/{id}`
Updates the user object with the specified id. The User object is passed in as the body of the request. Requires either the `researcher` role or the `admin` role.

##### POST `/api/protected/users/passwordchange`
Changes the user's password. Returns nothing. Requires either the `researcher` role or the `admin` role. This call is non-standard.

This call requires a different JSON object in the body of the request, a PasswordChangeRequest object. Its properties are:
* `oldPassword`: required old password string.
* `newPassword`: required new password string.

### `/api/protected/oauthproviders`
Retrieves information about available OAuth providers.

#### Role-based authorization
None

#### Requires successful authentication
Yes

#### OAuthProvider object
Properties:
* `id`: required unique number identifying the OAuth provider.
* `name`: required unique string containing the OAuth provider's name.
* `description`: optional string containing a longer description of the OAuth provider.

#### Calls
All calls use standard names, return values and status codes as specified in the [Eureka! Clinical microservice specification](https://github.com/eurekaclinical/dev-wiki/wiki/Eureka%21-Clinical-microservice-specification)

##### GET `/api/protected/oauthproviders`
Returns an array of all OAuthProvider objects.

##### GET `/api/protected/oauthproviders/{id}`
Returns a specified OAuthProvider object by the value of its id property, which is unique.

##### GET `/api/protected/oauthproviders/byname/{name}`
Returns a specified OAuthProvider object by the value of its name property, which is unique.

### `/api/protected/logintypes`
Retrieves information about available login types, which include 1) authenticating using Eureka! Clinical's built-in login screen or 2) authenticating with a trusted third party provider like an OAuth provider.

#### Role-based authorization
None

#### Requires successful authentication
Yes

#### LoginType object
Properties:
* `id`: required unique number identifying the object.
* `name`:  required unique string containing the login type's name. Allowed values are
  * `INTERNAL`: Authentication using Eureka! Clinical's built-in login screen.
  * `PROVIDER`: Authentication using an external provider for login.
* `description`: optional string containing the login type's description.

#### Calls
All calls use standard names, return values and status codes as specified in the [Eureka! Clinical microservice specification](https://github.com/eurekaclinical/dev-wiki/wiki/Eureka%21-Clinical-microservice-specification)

##### GET `/api/protected/logintypes`
Returns an array of all LoginType objects.

##### GET `/api/protected/logintypes/{id}`
Returns a specified LoginType object by the value of its id property, which is unique.

##### GET `/api/protected/logintypes/byname/{name}`
Returns a specified LoginType object by the value of its name property, which is unique.

### `/api/protected/authenticationmethods`
Retrieves information about available authentication methods.

#### Role-based authorization
None

#### Requires successful authentication
Yes

#### AuthenticationMethod object
Properties:
* `id`: required unique number identifying the object.
* `name`: required unique string containing the authentication method's name. Allowed values are
  * `LOCAL`: Uses Eureka! Clinical's built-in authentication.
  * `LDAP`: Uses an LDAP server.
  * `OAUTH`: Uses OAuth.
* description: optional string containing the authentication method's description.

#### Calls
All calls use standard names, return values and status codes as specified in the [Eureka! Clinical microservice specification](https://github.com/eurekaclinical/dev-wiki/wiki/Eureka%21-Clinical-microservice-specification)

##### GET `/api/protected/authenticationmethods`
Returns an array of all AuthenticationMethod objects.

##### GET `/api/protected/authenticationmethods/{id}`
Returns a specified AuthenticationMethod object by the value of its id property, which is unique.

##### GET `/api/protected/authenticationmethods/byname/{name}`
Returns a specified AuthenticationMethod object by the value of its name property, which is unique.

## Building it
The project uses the maven build tool. Typically, you build it by invoking `mvn clean install` at the command line. For simple file changes, not additions or deletions, you can usually use `mvn install`. See https://github.com/eurekaclinical/dev-wiki/wiki/Building-Eureka!-Clinical-projects for more details.

## Performing system tests
You can run this project in an embedded tomcat by executing `mvn tomcat7:run -Ptomcat` after you have built it. It will be accessible in your web browser at https://localhost:8443/eurekaclinical-user-service/. Your username will be `superuser`.

## Maven dependency
```
<dependency>
    <groupId>org.eurekaclinical</groupId>
    <artifactId>eurekaclinical-user-service</artifactId>
    <version>version</version>
</dependency>
```

## Developer documentation
* [Javadoc for latest development release](http://javadoc.io/doc/org.eurekaclinical/eurekaclinical-user-service) [![Javadocs](http://javadoc.io/badge/org.eurekaclinical/eurekaclinical-user-service.svg)](http://javadoc.io/doc/org.eurekaclinical/eurekaclinical-user-service)

## Getting help
Feel free to contact us at help@eurekaclinical.org.

