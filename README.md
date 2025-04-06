# Wishlist Project

This is a simple web application for managing a user's wishlist. Users can register, log in, and manage items they want to buy or receive.

## Features

- User registration
- Login and authentication
- Add and remove wishlist items
- View wishlist

**Note**: User interaction features (such as sharing wishlists, following other users, etc.) are currently under development and will be added in future releases.

## iOS Application

An initial prototype of the iOS app is available in the repository. Please note that the iOS app is still under development, and some features may not be fully implemented or may have bugs.

## Technologies Used

- Java 21
- Spring Boot
- PostgreSQL
- Spring Security (optional for later)

## How to Run the Project

1. Clone the repository:
git clone https://github.com/Ne-kolay/wishlist.git

2. Navigate to the project folder:
cd wishlist

3. Ensure PostgreSQL is installed and running. You might need to create a database for the app.

4. Build and run the project:
- If you have Maven installed:
  ```
  mvn spring-boot:run
  ```
- Alternatively, use the Maven Wrapper if you don't have Maven globally installed:
  ```
  ./mvnw spring-boot:run
  ```

5. Access the app at `http://localhost:8080`

## License

This project is not licensed yet.
