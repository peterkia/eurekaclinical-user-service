# Eureka! Clinical User Service
RESTful APIs for managing user profiles

# Version history
No final releases yet

# What does it do?
It provides RESTful APIs for users to manage their profile and change their password.

# REST endpoints
## `/api/userrequests`

## `/api/protected/users`

## `/api/protected/roles`

## `/api/protected/oauthproviders`
A read-only endpoint for retrieving information about available login types, which include 1) authenticating using Eureka! Clinical's built-in login screen or 2) authenticating with a trusted third party provider like an OAuth provider.

### Role-based authorization
None

### OAuthProvider object
Properties:
* `id`: unique number identifying the OAuth provider.
* `name`: unique string containing the OAuth provider's name.
* `description`: string containing a longer description of the OAuth provider.

### Calls
All calls use standard names, return values and status codes as specified in the [Eureka! Clinical microservice specification](https://github.com/eurekaclinical/dev-wiki/wiki/Eureka%21-Clinical-microservice-specification)

#### `/api/protected/oauthproviders`
Returns an array of all LoginType objects.

#### `/api/protected/oauthproviders/{id}`
Returns a specified LoginType object by the value of its id property, which is unique.

#### `/api/protected/oauthproviders/byname/{name}`
Returns a specified LoginType object by the value of its name property, which is unique.

## `/api/protected/logintypes`
A read-only endpoint for retrieving information about available login types, which include 1) authenticating using Eureka! Clinical's built-in login screen or 2) authenticating with a trusted third party provider like an OAuth provider.

### Role-based authorization
None

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
A read-only endpoint for retrieving information about available authentication methods.

### Role-based authorization
None

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
