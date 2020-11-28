# CSC436 Library Project
Contributors: Noah Sugden, Zack Leibowitz, Rafael Jeffery, Haifeng Mai (check spelling)

## Installation Requirements
### Docker
Docker will be used to host Microsoft SQL Server in container. Docker can be installed [here](https://www.docker.com/).
### MS SQL
We will be using a MS SQL to store the information for the library management system.

Docker instructions (in command line)
```
Note: If you're using Windows, run this in Windows Powershell with Admin priviledges. If you're using MacOSx, run these commands with the "sudo" prefix.
(1) docker pull mcr.microsoft.com/mssql/server:2019-latest
- Installs docker container

(2) docker run -p 1433:1433 --name libsql -h libsql -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=L!b4r4y!sC0OL' -d mcr.microsoft.com/mssql/server:2019-latest
- Starts MSSQL instance
- p x:y (docker port x, localhost port y)

(3) docker exec -it libsql "bash"
- Connects to a bash shell

(4) /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'L!b4r4y!sC0OL'
- Connects to a SQL shell (sqlcmd)
- No need to use sudo from this point on

(5) Set up SQL
- Copy the following statements onto the SQL shell
CREATE DATABASE library
USE library
CREATE TABLE books (title NVARCHAR(50), isbn NVARCHAR(50), format NVARCHAR(50), genre NVARCHAR(50), publisher NVARCHAR(50))
INSERT INTO books VALUES ("Cooking Chicken: The Bird", "30303020193", "paperback", "cooking", "Mama Mia Cooking House")
INSERT INTO books VALUES ("Physics: The Textbook", "75984301378", "ebook", "education", "Atomic Pages")
INSERT INTO books VALUES ("Physics: The Handbook", "75984301379", "ebook", "education", "Atomic Pages")
INSERT INTO books VALUES ("Rattle Snakes", "75984301379", "hardcover", "non-fiction", "Slither Reads")
GO
```

## Maven Repository
The project is set up a Maven project. This will allow the application to be modular with its imports.

### Parts of the project
1. [POM.XML](POM.XML)
   - Contains the dependencies as well as a helper for building the project.
2. File structure
   - Code is structured in groups src/main/java (in organizations, you'd typically see "org/main/java"),
   - Groups
     - [src/main/java](src/main/java): Holds project code. Library is the folder presenting the "Artifact" (more Maven jargon)
       - Imports follow "import Library.MVC.class"
     - [src/test/java](src/test/java): Holds any testing files (JUnit tests will go here)
3. JDBC Driver
   - Accessed via [SQLConnection.java](src/main/java/Library/SQLConnection/SQLConnection.java), the JDBC driver manages the connections to the SQL server.
   - Documentation on connecting to the SQL Server: [here](https://docs.microsoft.com/en-us/sql/connect/jdbc/building-the-connection-url?view=sql-server-ver15)
   - DriverManager Documentation: [here](https://docs.oracle.com/javase/8/docs/api/java/sql/DriverManager.html#getConnection-java.lang.String-java.lang.String-java.lang.String-)
   - Connection Examples: [here](https://docs.microsoft.com/en-us/sql/connect/jdbc/step-3-proof-of-concept-connecting-to-sql-using-java?view=sql-server-ver15)
   

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
