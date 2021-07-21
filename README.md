# SpringBootGradebook

A simple web API for managing a relational database.

---

## About

SpringBootGradebook is a student project that explores the use of Spring Data JPA and Spring Web, 
along with various other technologies. It aims to provide an *adequate* implementation of managing a
relational database with a web based API. The goal was to do this for a pre-existing database without modifying
the schema of that database and to provide a full set of CRUD operations.


All code in this project should be seen as an exploration of the concepts, and not a definitive example of them.

---

## Dependencies

> Docker  
> Docker-Compose

### Optional Dependencies
These are only required if you are running the application outside of Docker or against a local instance
of the database.

> Java 11  
> Maven  
> Microsoft SQL Server 2017

---

## Configuration

### Docker-Compose

While running the application with `docker-compose` *should* require no additional configuration, it is possible
that there is already something running at `localhost:8080` on your machine. If this is the case, the port mapping
can be configured in:

`dockerfile/docker-compose.yml`

The relevant section is:
```yaml
    ports:
      - "8080:8080"
```

> **Note:** The syntax for the mapping is `host:container` and only the left-hand portion should be modified.
>For example, mapping to `localhost:1234` would be:
> ```yaml
>    ports:
>      - "1234:8080"
> ```

### Running Locally 

The application has been configured to connect to a named Docker host. Running the application against a different
instance of SQL Server will require changing the database URL and rebuilding the project.

#### Setting The Database URL

The database URL can be configured in:

`src/main/resources/application.properties`

The relevant section is:

```
spring.datasource.url=jdbc:sqlserver://mssql;databaseName=Gradebook
```

The only modification necessary should be to change the host and port. For example, to connect to a
local instance of SQL Server running on a non-default port 8686, the configuration would be:  

```
spring.datasource.url=jdbc:sqlserver://localhost:8686;databaseName=Gradebook
```

> **Note:** Use `mssql` as the hostname to run via `docker-compose`. The hostname `mssql` comes from the 
> `docker-compose.yml` file, and is the name for the container running our database. The container database runs on the 
> default SQL Server port `1433` and so a port isn't needed.


#### Building With Maven

The easiest way to build the project is to use Maven. Simply navigate to the directory containing the project
`pom.xml` in your terminal and execute `mvn clean install`. This should build the project and place a fully functional,
self-contained `.jar` in the `target/` directory. 

This `.jar` will have a name such as `SpringBootGradebook-0.0.2-SNAPSHOT.jar` which is defined 
in the `pom.xml` and may change with new releases.

Alternatively, it is likely that you can open and build the project as a Maven project in your IDE of choice.


---

## Running the application

### With Docker-Compose
Navigate to the `docker` directory in your terminal and run
```
docker-compose build
docker-compose up -d
```

That's it, you should now have access to the API via `localhost:8080`

> **Note:** The `-d` at the end of the command indicates that it should run detached from the terminal
> that spawned it. The application is configured to output the queries it executes to terminal, so if you would
> like to see those queries along with other log information, you can omit the `-d`. It is important
> to note however that the application will exit if you close the terminal window it is attached to.

### Without Docker-Compose

You must provide your own SQL Server database.  
Ensure that you have built the project with a correct database URL that matches your database.
Once you have built the project, navigate to the folder containing the `.jar` and execute:  
`java -jar SpringBootGradebook-0.0.2-SNAPSHOT.jar &`

> **Note:** The `&` at the end of the command indicates that it should run detached from the terminal
> that spawned it. The application is configured to output the queries it executes to terminal, so if you would
> like to see those queries along with other log information, you can omit the `&`. It is important
> to note however that the application will exit if you close the terminal window it is attached to.

---

## Help

A partial list of available endpoints can be found [here](ENDPOINTS.md).  

---


## Todo

- [ ] Unit and integration testing.
- [ ] Improve documentation.
- [ ] Improve exception handling.
  - [ ] Implement custom exceptions.
- [ ] Migrate to Postgres.
- [ ] Improve endpoints.

---

## Technologies Used In This Project

### Java

- [Spring Boot](https://spring.io/projects/spring-boot)
  - [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
  - [Spring Web](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html)
- [Lombok](https://projectlombok.org/)
- [Apache Maven](https://maven.apache.org/)
- [OpenJDK 11](https://openjdk.java.net/projects/jdk/11/)

### Database

- [Microsoft SQL Server 2017](https://www.microsoft.com/en-us/sql-server/sql-server-2017)

### Containerization

- [Docker](https://www.docker.com/)
  - [Docker-Compose](https://docs.docker.com/compose/)

---

## License

>Licensed under the Apache License, Version 2.0 (the "License");
>you may not use this file except in compliance with the License.
>You may obtain a copy of the License at
>
>   http://www.apache.org/licenses/LICENSE-2.0
>
>Unless required by applicable law or agreed to in writing, software
>distributed under the License is distributed on an "AS IS" BASIS,
>WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
>See the License for the specific language governing permissions and
>limitations under the License.