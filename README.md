# SpringBootGradebook

A simple web API for managing a relational database.

---

- [About](#about)
- [Quick Start](#quick-start)
- [Dependencies](#dependencies)
  * [Optional Dependencies](#optional-dependencies)
- [Configuration](#configuration)
  * [Docker-Compose](#docker-compose)
  * [Running Locally](#running-locally)
    + [Setting The Database URL](#setting-the-database-url)
    + [Building With Maven](#building-with-maven)
- [Running the application](#running-the-application)
  * [With Docker-Compose](#with-docker-compose)
  * [Without Docker-Compose](#without-docker-compose)
- [Help](#help)
  * [SQL Server](#sql-server)
- [Resources](#resources)
  * [Endpoints](#endpoints)
  * [Technologies Used](#technologies-used)
    + [Java](#java)
    + [Database](#database)
    + [Containerization](#containerization)
- [Todo](#todo)
- [Discaimers](#disclaimers)
- [License](#license)

---

## About

SpringBootGradebook is a student project exploring the use of Spring Data JPA and Spring Web, 
along with various other technologies. It aims to provide an *adequate* implementation of managing a
relational database with a web based API. The goal was to do this for a pre-existing database without modifying
the schema of that database, and to provide a full set of CRUD operations.


---

## Quick Start

Current project images are hosted on Docker Hub and provide a quick way to get started.  
If you are pulling the project from GitHub you can [quickstart with docker-compose.](#with-docker-compose)

```shell
#Create a network for our containers
docker network create -d bridge gradebook-network

# Get the latest seeded database image, assign it to the network and give it the correct name name
docker run -d --network gradebook-network --name mssql jseabolt/gradebook-database

# Get the latest application image, assign it to the network and map the API to a port on the host.
docker run -d --network gradebook-network -p 8585:8080 jseabolt/springbootgradebook

## Test that the api is accessible with curl
curl -i -X GET http://localhost:8585/Student


```

---

## Dependencies

```
 Docker  
 Docker-Compose
```

### Optional Dependencies
These are only required if you are building and running the application outside of Docker or against a local instance
of the database.

```
 Java 11  
 Maven  
 Microsoft SQL Server 2017
```

---

## Configuration

| File                   | Path      | Use |
|------------------------|-----------|-----|
| docker-compose.yml     | ./docker/ | Configure the localhost port used to access the API when running with `docker-compose`. |
| application.properties | ./src/main/resources/ | Customize the database connection for running without `docker-compose`.|

### Docker-Compose


While running the application with `docker-compose` *should* require no additional configuration, it is possible
that there is already something running at `localhost:8080` on your machine. If this is the case, the port mapping
can be configured in:

`docker-compose.yml`

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

The application has been configured to connect to a named container through a Docker network.
Running the application against a different instance of SQL Server will require changing the database configuration and rebuilding the project.

#### Setting The Database Configuration

> Please see the [disclaimer on credentials.](#credentials)

The database URL and credentials can be configured in:

`application.properties`

The relevant section is:

```
spring.datasource.url=jdbc:sqlserver://mssql;databaseName=Gradebook
spring.datasource.username=sa
spring.datasource.password=Password1234
```

The modifications necessary are to change the host:port, username, and password.  
For example, to connect to a local instance of SQL Server running on a non-default port 8686, with a "gradebook" user and
"SuperSecurePassword#1234" the configuration would be:  

```
spring.datasource.url=jdbc:sqlserver://localhost:8686;databaseName=Gradebook
spring.datasource.username=gradebook
spring.datasource.password=SuperSecurePassword#1234
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

That's it, you should now have access to the API via `localhost:8080` or a custom port
if you've configured it in your [docker-compose.](#docker-compose)

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


### SQL Server
 
 - The file used to seed the database can be found in `docker/gradebook.sql`.

For convenience in development and testing, a pre-seeded SQL Server image is
available through Docker Hub via `jseabolt/gradebook-database`. 
To use it run
```shell
docker run -d -p <host-port>:1433 jseabolt/gradebook-database
```
where ` <host-port>` is the port you want to access the database through on your local machine.

>Be sure to [set the database URL!](#setting-the-database-url)

This is useful when you are developing and :

1. Frequently rebuilding the entire project for docker-compose is cumbersome.
2. You already have SQL Server but want to keep the gradebook database isolated.
3. Do not have SQL Server and do not wish to install it.

If you do not wish to pull a non-official image from a remote, you can build and run the
equivalent image by navigating to the `/docker` directory and running :  
```shell
#Build the image from the local dockerfile.
docker build -t gradebook-database -f sql_Dockerfile .

#Start a container instance for the image, detached and listening on the <host-port>.
docker run -d -p <host-port>1433 gradebook-database
```

This will pull the latest [official Microsoft image](https://hub.docker.com/_/microsoft-mssql-server)
and seed the database locally, allowing you to verify, secure, and modify the image. 


---

 ## Resources

### Endpoints
A partial list of available endpoints can be found [here](ENDPOINTS.md).

### Technologies Used

#### Java

- [Spring Boot](https://spring.io/projects/spring-boot)
  - [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
  - [Spring Web](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html)
- [Lombok](https://projectlombok.org/)
- [Apache Maven](https://maven.apache.org/)
- [OpenJDK 11](https://openjdk.java.net/projects/jdk/11/)

#### Database

- [Microsoft SQL Server 2017](https://www.microsoft.com/en-us/sql-server/sql-server-2017)

#### Containerization

- [Docker](https://www.docker.com/)
  - [Docker-Compose](https://docs.docker.com/compose/)

---



## Todo

- [ ] Unit and integration testing.
- [ ] Improve documentation.
- [ ] Improve exception handling.
  - [ ] Implement custom exceptions.
- [ ] Migrate to Postgres.
- [ ] Improve endpoints.

---

## Disclaimers

>  ### Credentials
>  This project currently uses a hard-coded "dummy" username and password for database administration.
>  These values can be found in `application.properties` and configured for the gradebook database image in`docker/sql_dockerfile`.  
>  In general, it is a very bad practice to store credentials in plain-text or to upload them.  
>  These files should be used for educational purposes only.  
>  This project may be updated in the future to implement best practices for these credentials. 



> ### Personal Information 
> All entries provided in the seeded database are fictional and do not represent real people.  
> You should not use or share personal information without the express consent of the person/entity.  

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
