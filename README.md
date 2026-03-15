# Student Management System

A **Java desktop application** for managing student records with authentication and a clean user interface.  
Built using **Java Swing**, **SQLite**, and a **layered architecture** to keep the code modular, maintainable, and scalable.

![Java](https://img.shields.io/badge/Java-Swing-orange)
![Database](https://img.shields.io/badge/Database-SQLite-blue)
![Architecture](https://img.shields.io/badge/Architecture-Layered-green)

---

## Overview

This project demonstrates how a complete desktop system can be structured using **separation of concerns**.  
It allows administrators to securely log in and perform full **student record management** through an intuitive dashboard.

The system emphasizes **clean architecture**, **data validation**, and **organized project structure**.

---

## Features

- User login authentication
- Add new student records
- Update existing student information
- Delete students
- Case-insensitive search
- Sort students by **name**, **marks**, or **ID**
- Filter students based on marks
- Table-based data display using **JTable**
- Input validation and formatting utilities
- Automatic database initialization

---

## Tech Stack

- **Java**
- **Java Swing**
- **SQLite**
- **JDBC**
- **FlatLaf** (modern Swing UI styling)

---

## Architecture

The project follows a **layered architecture** to separate responsibilities:

```text
models       → Data entities
dao          → Database access layer
service      → Business logic
controller   → UI interaction logic
util         → Validation & helper utilities
ui           → Swing interfaces
db           → Database setup & connection
```

This structure keeps the system **organized, testable, and easy to extend**.

---

## Interface

### Login Screen
A modern login interface styled with **FlatLaf** that authenticates users before accessing the system.

### Dashboard
The main dashboard provides tools to manage student records, including:

- Create, update, and delete students
- Sort and filter student lists
- Display records using **JTable**

---

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

---

## Contributors

### Hubert Nshuti
Backend architecture, database integration, application logic, and validation utilities.

### Happy Radouce Imbabazi
User interface design, including the **Login** and **Dashboard** forms.
