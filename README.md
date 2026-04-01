# Student Management System

A **Java desktop application** for managing student records with authentication, built using **Swing** and **MySQL** with a **layered architecture**.

![Java](https://img.shields.io/badge/Java-Swing-orange)
![Database](https://img.shields.io/badge/Database-MySQL-blue)
![Architecture](https://img.shields.io/badge/Architecture-Layered-green)

---

## Overview

The app is all about allowing administrators to securely log in and perform full **student record management** through an intuitive dashboard.

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

<img width="982" height="589" alt="image" src="https://github.com/user-attachments/assets/cedd1bcc-5128-4bd3-bfea-4bb5f21731e4" />


### Dashboard
The main dashboard provides tools to manage student records, including:

- Create, update, and delete students
- Sort and filter student lists
- Display records using **JTable**

  <img width="979" height="585" alt="image" src="https://github.com/user-attachments/assets/3c422150-d29c-4bad-ad02-da544bb85d5b" />


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
Backend architecture, database integration, and application logic.
### Happy Radouce Imbabazi
User interface design, including the **Login** and **Dashboard** forms and validation utilities.
