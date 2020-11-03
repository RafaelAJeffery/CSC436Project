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

(2) docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=L!b4r4y!sC0OL' -e 'MSSQL_PID=Express' -p 1433:1433 -d mcr.microsoft.com/mssql/server:2019-latest
- Starts MSSQL instance

(3) docker exec -it mcr.microsoft.com/mssql/server /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P 'L!b4r4y!sC0OL'
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
Maven projects are integrated into Eclipse. You want to clean and the project. In Eclipse, you can run the Maven project with the goal "clean javafx:run". This will run the project as a JavaFx project (see the build portion of POM.XML)