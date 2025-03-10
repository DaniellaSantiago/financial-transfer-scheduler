# Financial Transfer Scheduling System

## Overview
This project is an application for scheduling financial transfers. The system displays the transfer statement and allows users to register future transfers between bank accounts, applying taxes according to the scheduled date.

## Technologies Used

- **Backend:** Java 11 com Spring Boot 2.4.2
- **Dependency Manager:** Maven
- **Database:** H2 (in memory)
- **Frontend:** Vue.js

## Features

1. Registration of bank transfers with the following data:
   - Source account
   - Destination account
   - Transfer amount
   - Transfer date
   - Scheduled date (automatic for the current date)
   - Calculation of the tax according to the defined table
2. Display of the statement with all scheduled transfers.
3. Validation of the tax rules according to the transfer date.

## Tax Rules
The tax on the amount to be transferred depends on the interval of days between the scheduling date and the transfer date:

| Scheduling Days | Fixed Tax (R$) | Percentage Tax (%) |
| ------------------- |----------------|--------------------|
| 0                   | 3,00           | 2,5%               |
| 1 - 10              | 12,00          | 0,0%               |
| 11 - 20             | 0,00           | 8,2%               |
| 21 - 30             | 0,00           | 6,9%               |
| 31 - 40             | 0,00           | 4,7%               |
| 41 - 50             | 0,00           | 1,7%               |

If there is no applicable tax, the transfer will not be allowed and a warning will be displayed to the user.

## How to Run the Project

### Backend (Spring Boot)

1. Make sure you have **Java 11** installed.
2. Clone the repository and navigate to the project directory.
3. Run the following command to compile and package the project:
    ```sh
    mvn clean package
    ```
4. Run the following command to start the application:
    ```sh
    mvn spring-boot:run
    ```
5. The backend will be running on port **8080**.

### Accessing the H2 Database
The project uses the in-memory H2 database for persistence. To access the H2 console:
1. After starting the backend, open a browser and go to:
    ```
    http://localhost:8080/h2
    ```
2. Use the following credentials:
    - **JDBC URL:** jdbc:h2:mem:transfers_db
    - **User:** admin
    - **Password:** (leave blank)
3. Click Connect to access the database and view the data.

### Frontend (Vue.js)

1. Make sure you have **Node.js** and **npm** installed.
2. Navigate to your frontend directory and install the dependencies:
    ```sh
    npm install
    ```
3. Run the command to start the application:
    ```sh
    npm run serve
    ```
4. The frontend will be accessible on port **3000**.

## Architectural Decisions
- **Spring Boot** was chosen for the backend due to its ease of configuration and integration with the H2 database.
- **Maven** was used to manage dependencies and facilitate building the project.
- The **H2 database** was used for data persistence, allowing for quick testing without the need for external configurations.
- **Vue.js** was used for the frontend due to its simplicity and componentization, facilitating the creation of a responsive and intuitive interface.

## Project Structure

```
/
|-- main/    # Backend source code (Spring Boot)
|-- frontend/   # Frontend source code (Vue.js)
|-- README.md   # Project documentation
|-- pom.xml     # Maven Configuration File
```
