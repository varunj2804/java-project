Project Title:
To-Do Manager with Deadlines (Java Console Application)

1. Introduction
This project is a console-based task management system written in Java. It allows users to create tasks, assign deadlines, track pending work, and store tasks using file handling. The program follows object-oriented design and uses a menu-driven structure.

2. Problem Statement
Users often forget important tasks because they rely only on memory. There is a need for a simple system that records tasks, deadlines, and status. This project provides a solution by using a Java console application that manages tasks efficiently.

3. Objectives
- To build a simple and efficient task manager using Java.
- To create tasks with title, description, and deadline.
- To store tasks using a file to ensure persistence.
- To allow updating and deleting tasks.
- To organize tasks using object-oriented programming.

4. Functional Requirements
FR1: Add Task  
FR2: View All Tasks  
FR3: View Pending Tasks  
FR4: Mark Task as Done  
FR5: Delete Task  
FR6: Save and Load Tasks  

5. Non-functional Requirements
NFR1: Usability  
NFR2: Reliability  
NFR3: Performance  
NFR4: Maintainability  
NFR5: Portability  

6. System Architecture
The system uses a three-layer architecture:
Application Layer (TodoApp)
Logic Layer (TaskService)
Data Layer (Task objects and tasks.txt file)

7. Design Diagrams
Use Case Diagram:
User -> Add Task
User -> View Tasks
User -> Mark Task Done
User -> Delete Task

Workflow Diagram:
Start -> Menu -> Actions -> Save -> Exit

Sequence Diagram:
User -> TodoApp -> TaskService -> Task -> TodoApp -> User

Class Diagram:
Task
TaskService
TodoApp

ER Diagram:
TASK(TaskID, Title, Description, Deadline, Status)

8. Design Decisions and Rationale
- ASCII-only output for universal terminal compatibility.
- ArrayList used for dynamic task storage.
- File storage chosen over database for simplicity.
- Separated logic, UI, and data into packages for modularity.
- LocalDate used for reliable date parsing.

9. Implementation Details
- Java language with package-based structure.
- Collections used for managing tasks.
- File handling for persistence.
- Exception handling for invalid input.
- Sorting performed using Comparator.

10. Screenshots / Results
(Insert screenshots here based on your program output)

11. Testing Approach
- Unit testing for add, delete, and mark-done operations.
- Input validation tests for invalid IDs and dates.
- File loading and saving verification.
- Sorting validation by deadline.

12. Challenges Faced
- Handling date parsing errors.
- Ensuring correct package compilation.
- Managing ASCII-only display formatting.
- Implementing file persistence.

13. Learnings and Key Takeaways
- Understanding Java packages and OOP structure.
- Working with ArrayList and Comparator.
- File handling basics.
- Designing simple console interfaces.
- Implementing clean architecture in console projects.

14. Future Enhancements
- Add priority levels.
- Detect overdue tasks.
- Provide editing functionality.
- Add search capability.
- Build graphical interface.

15. References
- Java Documentation (Oracle)
- Java Tutorials (JDK)
- Classroom lecture notes
- Basic file handling examples