# VAT Validation Microservice

The VAT Validation Microservice is a web application consisting of two parts: a backend service and a frontend validation UI. The microservice allows users to validate VAT-IDs (Value Added Tax Identification Numbers) against the official EU VAT Information Exchange System (VIES) database.

## Features

- Validate VAT-IDs against the VIES database
- Display validation results including company information
- User-friendly web interface for easy interaction

## Architecture

The application follows a client-server architecture, with the following components:

- Backend Service: A RESTful API service built using Java and Spring Boot. It interacts with the VIES database to perform VAT-ID validations and provides endpoints for the frontend UI.
- Frontend Validation UI: A React-based user interface that allows users to enter VAT-IDs, initiate validation requests, and display the validation results.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Node.js and npm (Node Package Manager) installed on your system

## Backend Service

### Installation and Setup

1. Clone the repository:
   ```shell
   git clone https://github.com/your-username/vat-validation-microservice.git
   ```

2. Navigate to the `backend` directory:
   ```shell
   cd vat-validation-microservice/backend
   ```

3. Build the project:
   ```shell
   ./mvn install
   ```

### Configuration

- Configure the backend service by modifying the `application.properties` file located in `src/main/resources`. Set the necessary properties, such as the server port and VIES API endpoints.

### Running the Service

1. Start the backend service:

2. The service will be accessible at `http://localhost:8080`. You can test the endpoints using tools like cURL or Postman.

## Frontend Validation UI

### Installation and Setup

1. Navigate to the `frontend` directory:
   ```shell
   cd vat-validation-microservice/frontend
   ```

2. Install the dependencies:
   ```shell
   npm install
   ```

### Configuration

- Configure the frontend UI by modifying the `.env` file located in the project's root directory. Update the backend API URL if necessary.

### Running the UI

1. Start the frontend development server:
   ```shell
   npm start
   ```

2. Open your browser and visit `http://localhost:3000` to access the VAT validation UI.

## Contributing

Contributions are welcome! If you have any suggestions, bug reports, or feature requests, please open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgments

This application utilizes the official VIES API for VAT-ID validation.

## Disclaimer

This microservice and its components rely on the availability and accuracy of the VIES database. It is provided as-is without any warranty. Use it at your own risk.

Feel free to customize and enhance the README file based on your specific requirements and project details.
