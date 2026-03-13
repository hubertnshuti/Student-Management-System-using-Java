# Student Management System

A **Java desktop app** for managing student records with login. Built with **Java Swing** for the interface, **SQLite** for data storage, and organized so the code is clean and easy to maintain.

![Java](https://img.shields.io/badge/Java-Swing-orange)
![Database](https://img.shields.io/badge/Database-SQLite-blue)
![Architecture](https://img.shields.io/badge/Architecture-Layered-green)

## Features

- Secure login authentication
- Add student records
- Update student information
- Delete students
- Case-insensitive search
- Sort by name, marks, or ID
- Filter students by marks
- JTable-based record display
- Input validation and formatting utilities

## Tech Stack

- **Java**
- **Java Swing**
- **SQLite**
- **JDBC**
- **FlatLaf**

## Architecture

```text
models       → Data structures
dao          → Database access
service      → Business logic
controller   → UI interaction logic
util         → Validation & helper utilities
ui           → Swing user interfaces
db           → Database initialization
```

## Interface

### Login Interface
Modern login screen with **FlatLaf** styling.

### Dashboard
Student management dashboard with **CRUD operations**, **sorting**, **filtering**, and **table display**.

## Project Structure

```text
src
├── controller
├── dao
├── db
├── models
├── service
├── ui
└── util
```

## Contributors

### Hubert Nshuti
Backend architecture, database integration, and application logic.

### Happy Radouce Imbabazi
User interface design, including the **Login** and **Dashboard** forms.
