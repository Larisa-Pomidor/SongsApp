# SongsApp
This project is a SongsApp, an application for managing and organizing songs. It allows users to create, edit, and delete songs, as well as view a list of all the songs available.

## Features
- Add a new song: Users can create a new song by providing the title, artist, and duration.
- Edit song details: Users can modify the title, artist, and duration of existing songs.
- Delete a song: Users can remove a song from the list.
- View all songs: Users can see a comprehensive list of all the songs in the application.
- User authentication: Implemented Spring Security for user authentication and authorization.
- Email confirmation: Users receive an email confirmation to verify their account during the registration process.

## Database schema
![db-scheme](https://raw.githubusercontent.com/Larisa-Pomidor/SongsApp/master/songs_db_3.png)

## Technologies Used
- Backend: Java, Spring Boot, Spring Security
- Frontend: Vue.js
- Template Engine: Thymeleaf
- Database: MySQL
- Email Confirmation: JavaMail API

## Getting Started
To run this project locally, follow these steps:
### To run backend:
- Clone the repository: git clone https://github.com/Larisa-Pomidor/SongsApp.git
- Navigate to the project directory: cd SongsApp
- Set up a MySQL database and update the database configuration in application.properties file.
- Build the project: mvn clean install
- Run the application: mvn spring-boot:run

### To run frontend:
- Navigate to the project directory /frontend
- Run the application: npm run serve
- Access the application in your web browser at: http://localhost:3000

## Screenshots
![songs-app](https://raw.githubusercontent.com/Larisa-Pomidor/SongsApp/master/songs-app.jpg)
