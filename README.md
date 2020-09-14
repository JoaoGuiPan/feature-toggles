# Feature-toggles

Feature Toggle Manager Web App

# Building

First, you will need to have node.js and Java 11 installed on your machine.
To build the code, simply run `npm install` in 'feature-toggles-front' folder and then `./mvnw clean install` in 'feature-toggles-api' folder.

# Running

To run the app, first run `./mvnw spring-boot:run` in 'feature-toggles-api' folder and then `ng serve` in 'feature-toggles-front' folder. The app will be available at `http://localhost:4200/`.

# Documentation

The API documentation is available at `http://localhost:8080/swagger-ui.html`.
