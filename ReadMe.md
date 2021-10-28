#Ile-iwe  project
Ile-iwe project is an e-learning project built with
spring framework,spring security, java, mySql and maven as a build tool.
#Features
Add courses, students and instructor  to database

Find  by id

Find all courses, students, instructor

Delete courses, students and instructors

Spring MVC with thymeleaf template engine for viewing product details

#installation

Step 1: Clone repository

Step 2: Install dependencies: cd into the root folder and run the following command:
`mvn install`

Step 3: Setup database: run sql script in the db folder
`src/main/resources/db/setup-db.sql`

Step 4: Run the project
`mvn spring-boot-run`