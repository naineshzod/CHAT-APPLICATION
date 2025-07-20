Real-Time Chat Application
This repository contains the source code for a real-time chat application built for an internship project. The project has two versions:

Desktop Application: A classic client-server application built with JavaFX, Java Sockets, and a MySQL database.

Web Application: A modern, serverless web-based chat room using HTML, JavaScript, and Google's Firebase Firestore for real-time data synchronization.

Version 1: Java Desktop Application
A multi-user chat application where users can communicate in a shared chat room. The application features a server that handles multiple client connections simultaneously and a JavaFX-based client for the user interface.

Features
Multi-client server architecture using Java Sockets.

User-friendly graphical interface built with JavaFX.

MySQL database integration for future enhancements like user authentication and message history.

Technologies Used
Backend: Java (Sockets for networking)

Frontend (GUI): JavaFX

Database: MySQL

Build Tool: Apache Maven

Prerequisites
Before you begin, ensure you have the following installed:

Java Development Kit (JDK): Version 11 or higher.

JavaFX SDK: Version 17 or higher.

MySQL Server & MySQL Workbench: Version 8.0 or higher.

Apache Maven: Integrated into your IDE (like VS Code or IntelliJ).

Visual Studio Code with the "Extension Pack for Java".

Setup and Installation
1. Database Setup

Open MySQL Workbench and connect to your local MySQL server.

Create a new query tab and run the following SQL script to create the chat_app database and its tables.

-- Create the database for the chat application
CREATE DATABASE IF NOT EXISTS chat_app;

-- Use the newly created database
USE chat_app;

-- Create the 'users' table
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create the 'messages' table
CREATE TABLE IF NOT EXISTS messages (
    message_id INT AUTO_INCREMENT PRIMARY KEY,
    chat_id INT NOT NULL,
    sender_id INT NOT NULL,
    message_text TEXT NOT NULL,
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

2. Project Configuration

Clone this repository to your local machine.

Open the project folder (MyChatApp) in Visual Studio Code.

Update Database Credentials: Open src/main/java/chat/DatabaseConnection.java and replace "your_password" with your actual MySQL root password.

Configure launch.json:

Go to the "Run and Debug" view in VS Code and create a launch.json file if one doesn't exist.

Ensure the file contains the following configuration.

Crucially, you must update the vmArgs path to point to the lib folder of your JavaFX SDK installation.

{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Launch Server",
            "request": "launch",
            "mainClass": "chat.Server",
            "projectName": "MyChatApp"
        },
        {
            "type": "java",
            "name": "Launch Client App",
            "request": "launch",
            "mainClass": "chat.App",
            "projectName": "MyChatApp",
            "vmArgs": "--module-path \"C:/path/to/your/javafx-sdk/lib\" --add-modules javafx.controls,javafx.fxml"
        }
    ]
}

How to Run
The application must be run in a specific order: Server first, then Client(s).

Start the Server:

In VS Code, go to the "Run and Debug" panel.

Select "Launch Server" from the dropdown menu and click the green play button (▶).

The terminal should display Server started on port 12345.

Start the Client:

After the server is running, select "Launch Client App" from the dropdown menu.

Click the green play button (▶) again.

The chat application's login window will appear.

You can launch the client multiple times to simulate a conversation between different users.

Version 2: Web Application
A simple, elegant, and modern web-based chat application that runs entirely in the browser. It uses Firebase Firestore for real-time messaging, meaning messages appear instantly for all users without needing to refresh the page.

Features
Serverless architecture using Firebase.

Clean and responsive user interface built with Tailwind CSS.

Real-time message synchronization.

No installation required for users; just a web browser.

Technologies Used
Frontend: HTML, Tailwind CSS, JavaScript

Backend & Database: Google Firebase (Firestore)

How to Run
Save the code as an HTML file (e.g., chat.html).

Open the chat.html file in any modern web browser (like Chrome, Firefox, or Edge).

Enter a username to join the chat.

To test the real-time functionality, open the same chat.html file in another browser tab or on a different device. Messages will sync instantly between them.
