# MyBooks - A Case Study

## Problem Statement

Build a system to search for a book title, show results and add books to favourite list.

## Requirements

1. The application needs to fetch book details from the following Open Library API.
https://openlibrary.org/developers/api

Refer the following URLs to explore more on the Books open library APIs.
https://openlibrary.org/dev/docs/api/search
https://openlibrary.org/dev/docs/api/books

2. A frontend where the user can register/login to the application, search books by string, title or author, get books details, add book to favourite list and view recommended books.
  - On launching the application the user should get the login page. The login page should have a link for registration using which the user should be able to register. On successful registration the user should be taken to the login page. Upon login, the user should be taken to the home page.
  - Proper navigation links for all the pages should be available within pages.
  - Error handling should be implemented across pages. Appropriate messages should be    displayed for the same. Success messages should be displayed for the User Registration.
  - Logout feature should be implemented.

3. User can add a book to favourite list and should be able to view the favourite list.

## Microservices/Modules
- UI (User interface) -  should be able to
    - Search a book by string, title or author
    - View book details
    - Add a book to favourite list
    - should be able to view favourite books
    - UI should be responsive which can run smoothly on various devices 
    - UI is appealing and user friendly.

- UserService - should be able to manage user accounts. 
- FavouriteService - should be able to store and retrieve all the favourite books for a user 

## Tech Stack
- Spring Boot
- MySQL, MongoDB
- REACT

## Flow of Modules

### Building frontend:
  1. Building responsive views:
  - Register/Login
  - Search for a book
  - Book list - populating from external API
  - Build a view to show favourite books
  2. Using Services to populate these data in views
  3. Making the UI Responsive
  4. E2E scripts should be created for the functional flows

### Building the UserService
  1. Creating a server in Spring Boot to facilitate user registration and login with MySQL as backend. Upon login, JWT token has to be generated. It has to be used in the Filters set in other services.

### Apply JWT Authentication for FavouriteService for all the incoming requests

### Building the Favourite Service
  1. Building a server in Spring Boot to facilitate CRUD operation over favourite books    stored in MongoDB
  2. Writing Swagger Documentation


#### Build CI pipeline
#### Dockerize all microservices and build docker-compose
#### Build Eureka service Registry


