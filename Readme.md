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
    
- **[Doing]** Demo v2
    - Using Hibernate instead of raw JDBC. Rearch about database connection pooling
    - Pagination. Avoid load too much data from database. 
    - Input validation.
    - **[Done]** Adding Logger. Use SLF4j. Config log per package level. 
    - **[Done]** Envelope the response. Json response has format like: 
```json
{
  "status": "ok",
  "data": [],
  "error": []
}
```
     
     

- Demo v3: 
    - Permission for API: create two role: admin + normal role. Flexible permission per api and per path  
    - Http compression: reduce the amount of data is sent over the network. Use gzip
    - Security: injection, XSS. Using library of owasp
    - Encode id, don't use database's id explicitly. Ex: don't use categoryId, use encode(categoryId)
  
- Demo v4
  - Adding LRU cache, use MemCached (how to monitor)
  - Using RabitMQ for pub/sub event. Use it to clear local cache when deploy >= 2 server. Demo how to use RabitMQ
  - For Token, save it on database to verify token again. Don't include sensitive on jwt. Only include noiseTokenId. Read at: https://auth0.com/docs/secure/tokens/token-best-practices

- Deployment
    - Environment variable to seperate dev/deploy mode
    - Deploy GCP, config to deploy production wsgi, config SSL
    - Create Docker image for this app. Deploy using Kubernetes
    - Apply CI/CD

- Monitor service after deploy
    - Stress test service
    - UI for monitor traffic, request rate
    - auto restart service when it dies

- Rate Limiter
    - Set RateLimit for API.
    - Use Redis to create RateLimit service

- Advanced Logger
    - Config rotation log, File appender log. Avoid full disk problem.
    - Define log format to make the grepping log or debugging from log more easily

- *[Option]* Elasticsearch
- *[Option]* Login with facebook, google
