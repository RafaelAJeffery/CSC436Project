# CSC436 Library Project
Contributors: Noah Sugden, Zack Leibowitz, Rafael Jeffery, Haifeng Mai (check spelling)

## Installation Requirements
### Docker
Docker will be used to host Microsoft SQL Server in container. Docker can be installed [here](https://www.docker.com/).
### MS SQL
We will be using a MS SQL to store the information for the library management system.

Docker instructions (in command line)
```
(1) docker pull mcr.microsoft.com/mssql/server:2019-latest
- Installs docker container

(2) docker run --name libsql -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=L!b4r4y!sC0OL' -p 1433:1433 -d mcr.microsoft.com/mssql/server:2019-latest
- Starts MSSQL instance
- p x:y (docker port x, localhost port y)

(3) docker exec -it libsql /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'L!b4r4y!sC0OL'
- Connects to SQL server using sqlcmd tools
```

## Maven Repository
The project is set up a Maven project. This will allow the application to be modular with its imports.

### Parts of the project
1. [POM.XML](POM.XML)
   - Contains the dependencies as well as a helper for building the project.
2. File structure
   - Code is structured in groups src/main/java (in organizations, you'd typically see "org/main/java"),
   - Groups
     - src/main/java: Holds project code. Library is the folder presenting the "Artifact" (more Maven jargon)
       - Imports follow "import Library.MVC.class"
     - src/test/java: Holds any testing files (JUnit tests will go here)

### How to Run??
Maven projects are integrated into Eclipse. We're wanting to (1) clean the project files and then (2) run them.

Typically, Maven is called like so via the command line:
```
mvn clean
nvm run
```
However, because we're using a Javafx application, it will be different. We will be running the following command:
```
mvn clean javafx:run
```
In Eclipse, we can create "Run Configurations." For the Maven project, add "clean javafx:run" to the build goals. This will properly build our project. 