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

## `/api/protected/logintypes`

## `/api/protected/authenticationmethods`
A read-only endpoint for retrieving information about available authentication methods.

### Role-based authorization
None

### Calls
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
