**Spring Boot Overview**

- **Spring Boot**: A project built on top of the Spring Framework to simplify the setup, configuration, and running of Spring applications, providing a faster approach for web-based and stand-alone applications.
- **RAD Feature**: Enables Rapid Application Development within the Spring Framework.
- **Minimal Configuration**: Used to create Spring-based applications with minimal setup, combining Spring Framework with an embedded server.

**Advantages**
- Simple to understand and develop Spring applications
- Increases productivity
- Reduces development time

**Why Use Spring Boot?**
- Create stand-alone Spring applications with `java -jar` command
- Embedded HTTP servers (e.g., Tomcat, Jetty) eliminate the need to deploy WAR files
- Opinionated starters simplify Maven configuration
- Production-ready features (metrics, health checks, external configuration)
- No need for XML configuration

**Key Features**
- **Web Development**: `spring-boot-starter-web` module for quick web application setup.
- **SpringApplication**: A convenient way to bootstrap Spring applications.
- **Application Events & Listeners**: Uses events for various tasks; supports custom listeners via `ApplicationListener` in `META-INF/spring.factories`.
- **Admin Features**: Enable remote application management with `spring.application.admin.enabled`.
- **External Configuration**: Supports YAML files for environment-specific settings.
- **Properties Files**: Configure properties like `server.port=8082`.
- **YAML Support**: An alternative to properties files for hierarchical configuration.
- **Logging**: Common logging is managed by default; minimal configuration required.
- **Security**: Basic authentication is enabled by default on all HTTP endpoints.

**Quick Start**
- Use [Spring Initializer](https://start.spring.io/) or IDEs like IntelliJ for project setup.
  
**Main Classes and Modules**
- Web: `<artifactId>spring-boot-starter-web</artifactId>`
- Core: `<artifactId>spring-boot-starter</artifactId>`

**SpringApplication.run Execution Flow**
1. Start time recorded
2. Call Listeners
3. Prepare Environment
4. Print Banner
5. Create Application Context (Initialize IOC)
6. Refresh Context
7. Record Stop time
8. Log output
9. Trigger Runner
10. Return Application reference (IOC Container)

