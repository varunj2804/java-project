# To-Do Manager with Deadlines (Java Console Application)

A console-based task management application written in Java.  
Users can add tasks with deadlines, view all tasks, view pending tasks, mark tasks as done, and delete tasks.  
Tasks are saved to a file for persistence between runs.

------------------------------------------------------------

## Features

- Add a task with:
  - Title
  - Description
  - Deadline (YYYY-MM-DD format)
- View all tasks sorted by deadline
- View only pending tasks
- Mark tasks as DONE
- Delete tasks by ID
- Save tasks to "tasks.txt" on exit
- Load tasks from "tasks.txt" at startup
- Fully ASCII output for compatibility with all terminals

------------------------------------------------------------

## Project Structure
project-root/ ├─ src/ │   ├─ objects/ │   │    └─ Task.java │   ├─ manager/ │   │    └─ TaskService.java │   └─ app/ │        └─ TodoApp.java ├─ tasks.txt └─ out/

------------------------------------------------------------

## Requirements

- Java Development Kit (JDK 8 or above)
- Any terminal (CMD, PowerShell, etc.)

------------------------------------------------------------

## How to Compile
javac -d out src/objects/.java src/manager/.java src/app/TodoApp.java

## How to Run
java -cp out app.TodoApp

------------------------------------------------------------

## Concepts Used

- Classes and Objects
- Packages
- ArrayList
- File Handling (Java IO)
- Exception Handling
- Comparator Sorting
- Basic Layered Architecture

------------------------------------------------------------

## Future Enhancements

- Task priority levels
- Overdue task detection
- Edit existing tasks
- Search tasks
- CSV export
- GUI version using JavaFX

------------------------------------------------------------

## Author

Name: kesamreddy umeshchandrareddy  (24mim10053)
Project: To-Do Manager with Deadlines  
