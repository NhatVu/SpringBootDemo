# Demo Spring boot
This is a demo for using Spring boot. Along with other backend technique that can be use directly in production environment. 

- **[Done]** Learn how to use Spring boot to build RESTful API by following a tutorial on Youtube. Link at: https://www.youtube.com/watch?v=5VUjP1wMqoE. Overview of content:
    - Follow MVC architecture: **Repository** (Model) layer will talk to database, **Service** layer will implement business logic and call Repository. **Resource** (Controller) layer will handle incoming request 
    - Technology: Spring Boot, JDBC, Token
    - /api/users:
      - [POST] /login: login by username + password. Return JWT token
      - [POST] /register: create new user. Password is hashed by BCrypt library. 
    - /api/categories:
      - [Get] / : List all category 
      - [POST] / : create new category 
      - [GET][PUT][DELETE] /{categoryId}: get, update and delete specific category 
    - /api/categories/{categoryId}/transactions
    - Handle CORS (Cross-Origin Resource Sharing) for all api. For security reasons, browsers restrict cross-origin HTTP requests initiated from scripts. 

  - **Issue** :
      - What is @transactional annotation? when and how to use it.
      - Use jdbcTempalte to connect database. Don't fully understand about it.
    
- **[Done]** Demo v2
    - Using Hibernate instead of raw JDBC. Rearch about database connection pooling
      - [Note] JPA derived simple queries by Java Persistence Query Language (JPQL): https://www.baeldung.com/spring-data-derived-queries. For complex query that doesn't fit to exist rules, try 4.6. Custom Implementations for Spring Data Repositories at https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
      - [Note] Config spring.jpa.hibernate.ddl-auto=update, spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect for auto create table in postgres. IMPORTANT: don't use postgres keyword for table name

    - Pagination when using ORM. Avoid load too much data from database.
      - Ref: https://www.baeldung.com/spring-data-jpa-pagination-sorting
    - Input validation.
      - Bean Validation: https://www.baeldung.com/spring-boot-bean-validation
      - PathVariable, RequestPram validation: https://www.baeldung.com/spring-validate-requestparam-pathvariable
    - Adding Logger. Use SLF4j. Config log per package level. 
    - Envelope the response. Json response has format like: 
```json
{
  "status": "ok",
  "data": [],
  "error": []
}
```
     
     

- **[Done]** Demo v3:
    - Permission for API: create two role: admin + normal role. Flexible permission per api and per path  
    - Http compression: reduce the amount of data is sent over the network. Use gzip
    - Handle repository exception, don't log all stack to client response.
      - DBConnection exception has to catch CannotCreateTransactionException at Controller level or use @ControllerAcvice
    - References: 
        - Spring security tutorial: https://www.toptal.com/spring/spring-security-tutorial
        - https://stackoverflow.com/questions/9787409/what-is-the-default-authenticationmanager-in-spring-security-how-does-it-authen

- [Doing] Demo v4
  - [Done] Change log format: show line number for easy debugging
  - [Doing] Custom application.properties, spring boot profiles (dev/production). Config Bean only run at dev/test profile.
  - [Done] Add TestContainers. Using docker to start real database for testing, not using H2/in-memory db.
  - Use Junit 5 and Mockito to test REST API
  - NonUniqueResultException if duplicate email. How to solve: unique email or change return result 
    - F.I.R.S.T principles for testing: 
    - F - Fast
    - I - Independent
    - R - Repeatable
    - S - Self-Validating
    - T - Timely
  - createUser: check duplicate email

- Deployment
    - Environment variable to seperate dev/deploy mode
    - Deploy GCP, config to deploy production, config SSL
    - Create Docker image for this app. Deploy using Kubernetes
    - Apply CI/CD
  
- Demo v5
  - Adding LRU cache, use MemCached (how to monitor)
  - Using RabitMQ for pub/sub event. Use it to clear local cache when deploy >= 2 server. Demo how to use RabitMQ
  - For Token, save it on database to verify token again. Don't include sensitive on jwt. Only include noiseTokenId. Read at: https://auth0.com/docs/secure/tokens/token-best-practices
  - Security: injection, XSS. Using library of owasp.       - Create Custom XSSFilter. How to list all default filter



- Monitor service after deploy
    - Stress test service
    - UI for monitor traffic, request rate
    - auto restart service when it dies

- Rate Limiter
    - Set RateLimit for API.
    - Use Redis to create RateLimit service

- Advanced Logger + other task
    - Config rotation log, File appender log. Avoid full disk problem.
    - Define log format to make the grepping log or debugging from log more easily
    - Config log for showing line number 
    - Encode id, don't use database's id explicitly. Ex: don't use categoryId, use encode(categoryId)

- *[Option]* Elasticsearch. Center log for aggregation and build QoS (Quality of Serivce)
- *[Option]* Login with facebook, google
- Techical debt
  - ORM relationship: OneToOne, ManyToOne and ManyToMany. Should we use auto incremental id? I think we shouldn't use ForeignKey on production environment because of performance.
  - Spring filter
  - Set timeout for connecting to database. Tend to use circuit breaker pattern
  - Custom annotation? 

Resources:
1. Spring Boot Quick Start: https://www.youtube.com/playlist?list=PLqq-6Pq4lTTbx8p2oCgcAQGQyqN8XeA1x