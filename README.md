# SchoolAppMvc

School App MVC is a pragmatic school management application developed using Java EE and adhering to the Model-View-Controller (MVC) architectural pattern. This application provides users with the means to create accounts and login as either teachers or students. Teachers can readily execute CRUD operations related to student data. In the future, students will gain access to their course and meeting details. Furthermore, the application's functionality will be structured using an impending role-based system, granting specific teachers the ability to oversee teacher-related data as per their designated roles.

## Application Components

- **Authentication:** The application provides authentication for users, allowing them to log in as either teachers or students.

- **Controller Classes:** These classes handle user requests and route them to the appropriate actions.

- **Service Classes:** Service classes manage the business logic of the application.

- **DAO Classes:** Data Access Object (DAO) classes handle interactions with the database.

- **DTOs:** Data Transfer Objects (DTOs) facilitate the exchange of data between different parts of the application.

- **Model:** The application model represents the structure and organization of data, including students, teachers, and roles.

- **Validators:** Validators ensure data input meets the required criteria.

- **Utility Methods:** Utility methods include configurations for date formats, password encryption, database connection, and other helpful functions.

- **Filters:** Filters are used to control access to certain URIs, allowing or restricting access based on specific criteria.

- **JSP Pages:** These JavaServer Pages are the view of the application, presenting the user interface. Styling can be improved for a better user experience.

## Maven Configuration

The application is built using Apache Maven. The `pom.xml` file includes dependencies required for the project.

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <!-- Maven configuration details -->
</project>

## Dependencies

- **javax.servlet:** Servlet API for handling HTTP requests and responses.

- **junit-jupiter:** JUnit 5 for testing.

- **jbcrypt:** Library for password hashing.

- **mysql-connector-java:** Connector for MySQL database.

- **commons-dbcp2:** Database Connection Pooling library.

- **jstl:** JavaServer Pages Standard Tag Library.

## DevDependencies

- **maven-surefire-plugin:** Maven plugin for running tests.

## Future Enhancements

- Role-based access control for teachers.

- Course and meeting functionality for students.

## Getting Started

1. Clone this repository to your local machine.

2. Build and run the application using Maven.

3. Access the application in your browser.




