# Eureka! Clinical User Service
RESTful APIs for managing user profiles

# Version history
No final releases yet

# What does it do?
It provides RESTful APIs for users to manage their profile and change their password.

# REST endpoints
## `/api/userrequests`
Manages user account requests.

### Role-based authorization
None

### Requires successful authentication
No

### UserRequest object
Properties:
* `id`: unique number identifying the request.
* `username`: the requested username string. Required.
* `firstName`: the requesting user's first name string. Optional.
* `lastName`: the requesting user's last name string. Optional.
* `fullName`: the requesting user's full name string. Optional.
* `email`: the requesting user's email address string. Optional.
* `verifyEmail`: the same email address string. Must match the value of `email`. Optional unless `email` has a value.
* `organization`: the requesting user's organization string. Optional.
* `title`: the requesting user's title. Optional.
* `department`: the requesting user's department. Optional.
* `loginType`: Required. Whether the requested account is:
  * `INTERNAL`: using Eureka! Clinical's login screen.
  * `PROVIDER`: using a trusted third party provider's login mechanism.
* `type`: Required. The authentication method of the account request:
  * `LOCAL`: using Eureka! Clinical's authentication mechanism.
  * `OAUTH`: using an OAuth provider.
  * `LDAP`: using an LDAP server.

## `/api/protected/users`

## `/api/protected/oauthproviders`
Retrieves information about available OAuth providers.

### Role-based authorization
None

### Requires successful authentication
Yes

### OAuthProvider object
Properties:
* `id`: unique number identifying the OAuth provider.
* `name`: unique string containing the OAuth provider's name.
* `description`: string containing a longer description of the OAuth provider.

### Calls
All calls use standard names, return values and status codes as specified in the [Eureka! Clinical microservice specification](https://github.com/eurekaclinical/dev-wiki/wiki/Eureka%21-Clinical-microservice-specification)

#### `/api/protected/oauthproviders`
Returns an array of all OAuthProvider objects.

#### `/api/protected/oauthproviders/{id}`
Returns a specified OAuthProvider object by the value of its id property, which is unique.

#### `/api/protected/oauthproviders/byname/{name}`
Returns a specified OAuthProvider object by the value of its name property, which is unique.

## `/api/protected/logintypes`
Retrieves information about available login types, which include 1) authenticating using Eureka! Clinical's built-in login screen or 2) authenticating with a trusted third party provider like an OAuth provider.

### Role-based authorization
None

### Requires successful authentication
Yes

### LoginType object
Properties:
* `id`: unique number identifying the object.
* `name`: unique string containing the login type's name. Allowed values are
  * `INTERNAL`: Authentication using Eureka! Clinical's built-in login screen.
  * `PROVIDER`: Authentication using an external provider for login.
* `description`: string containing the login type's description.

### Calls
All calls use standard names, return values and status codes as specified in the [Eureka! Clinical microservice specification](https://github.com/eurekaclinical/dev-wiki/wiki/Eureka%21-Clinical-microservice-specification)

#### `/api/protected/logintypes`
Returns an array of all LoginType objects.

#### `/api/protected/logintypes/{id}`
Returns a specified LoginType object by the value of its id property, which is unique.

#### `/api/protected/logintypes/byname/{name}`
Returns a specified LoginType object by the value of its name property, which is unique.

## `/api/protected/authenticationmethods`
Retrieves information about available authentication methods.

### Role-based authorization
None

### Requires successful authentication
Yes

### AuthenticationMethod object
Properties:
* `id`: unique number identifying the object.
* `name`: unique string containing the authentication method's name. Allowed values are
  * `LOCAL`: Uses Eureka! Clinical's built-in authentication.
  * `LDAP`: Uses an LDAP server.
  * `OAUTH`: Uses OAuth.
* description: string containing the authentication method's description.

### Calls
All calls use standard names, return values and status codes as specified in the [Eureka! Clinical microservice specification](https://github.com/eurekaclinical/dev-wiki/wiki/Eureka%21-Clinical-microservice-specification)

#### `/api/protected/authenticationmethods`
Returns an array of all AuthenticationMethod objects.

#### `/api/protected/authenticationmethods/{id}`
Returns a specified AuthenticationMethod object by the value of its id property, which is unique.

#### `/api/protected/authenticationmethods/byname/{name}`
Returns a specified AuthenticationMethod object by the value of its name property, which is unique.

# Building it
The project uses the maven build tool. Typically, you build it by invoking `mvn clean install` at the command line. For simple file changes, not additions or deletions, you can usually use `mvn install`. See https://github.com/eurekaclinical/dev-wiki/wiki/Building-Eureka!-Clinical-projects for more details.

# Performing system tests
You can run this project in an embedded tomcat by executing `mvn tomcat7:run -Ptomcat` after you have built it. It will be accessible in your web browser at https://localhost:8443/eurekaclinical-user-service/. Your username will be `superuser`.

# Releasing it
First, ensure that there is no uncommitted code in your repo. Release it by invoking `mvn release:prepare` followed by `mvn release:perform`. See https://github.com/eurekaclinical/dev-wiki/wiki/Project-release-process for more details.
