# Final Project(Fleming App)

## Description
This project is a Java application that uses MongoDB for data storage and JCalendar for calendar functionality. It is designed to efficiently manage data and provide an interactive user interface for viewing and manipulating dates.

## Project Structure
The project is organized into various packages to separate different functionalities:

- **src/main/java/Calendar**: Contains the `Calendario` class responsible for managing events and dates.
- **src/main/java/clasesCredenciales**: Includes classes related to authentication and credential management.
- **src/main/java/conexionMongoDB**: Contains classes for handling MongoDB connections and operations.
- **src/main/java/bitacoraLocal**: Contains classes for managing local logs where events and changes are recorded.

## Dependencies
To run the project correctly, ensure that the following dependencies are installed:

- **MongoDB Java Driver**: For connecting and interacting with the MongoDB database.
- **JCalendar**: For implementing calendar components in the user interface.


## Configuration
1. **Install MongoDB**: Ensure that MongoDB is installed and running on `localhost:27017`. You can download and install MongoDB from its [official website](https://www.mongodb.com/try/download/community).
2. **Configure the Connection**: Verify that the connection configuration in the `conexionMongoDB` package points to `localhost:27017`. If your MongoDB is running on another host or port, update this configuration accordingly.

## Execution
To run the application, follow these steps:

1. **Compile**: Compile the project from your development environment or using tools like `Maven` or `Gradle`.
2. **Run**: Execute the main class `ProyectoFinal` located in the `m1.proyectofinal` package.
3. **Interact**: Use the graphical interface to add, edit, or delete events in the calendar.

```bash
java -cp target/ProyectoFinal-1.0.jar m1.proyectofinal.ProyectoFinal
```

## Known Issues
- **MongoDB Connection Issues**: If you experience connection issues, ensure that the MongoDB service is running and accessible on `localhost:27017`. Also, check that your firewall or network configuration is not blocking the connection.
- **JCalendar Compatibility**: If you encounter errors related to JCalendar, ensure that you are using the correct version of the library and that it is properly configured in your environment.

## Author
Juan Andrés Corredor
