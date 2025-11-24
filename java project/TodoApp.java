package app;

import manager.TaskService;
import objects.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class TodoApp {
    private static final Scanner sc = new Scanner(System.in);
    private static final TaskService service = new TaskService();

    public static void main(String[] args) {
        printHeader();
        boolean running = true;
        while (running) {
            printMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": addTask(); break;
                case "2": listAllTasks(); break;
                case "3": listPendingTasks(); break;
                case "4": markTaskDone(); break;
                case "5": removeTask(); break;
                case "6":
                    service.saveTasks();
                    System.out.println("\n Tasks saved. See you later!\n");
                    running = false;
                    break;
                default:
                    System.out.println("\n  Unknown option. Please choose from 1–6.");
            }
        }
    }

    private static void printHeader() {
        System.out.println("────────────────────────────────────────────────");
        System.out.println("TO-DO MANAGER WITH DEADLINES (Console) ");
        System.out.println("────────────────────────────────────────────────");
    }

    private static void printMenu() {
        System.out.println("\n MAIN MENU");
        System.out.println("────────────────────────────────────────────────");
        System.out.println(" [1]  Add a new task");
        System.out.println(" [2]  Show all tasks (by deadline)");
        System.out.println(" [3]  Show only pending tasks");
        System.out.println(" [4]  Mark a task as done");
        System.out.println(" [5]  Delete a task");
        System.out.println(" [6]  Save & Exit");
        System.out.println("────────────────────────────────────────────────");
        System.out.print("Your choice: ");
    }

    // ---------- Add ----------
    private static void addTask() {
        System.out.println("\nCREATE NEW TASK");
        System.out.println("────────────────────────────────────────────────");
        System.out.print("Title           : ");
        String title = sc.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }

        System.out.print("Description     : ");
        String desc = sc.nextLine().trim();

        System.out.print("Deadline (yyyy-MM-dd): ");
        String d = sc.nextLine().trim();

        try {
            LocalDate deadline = LocalDate.parse(d); // yyyy-MM-dd
            Task t = service.addTask(title, desc, deadline);
            System.out.println("\n Task created with ID: " + t.getId());
        } catch (DateTimeParseException e) {
            System.out.println(" Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    // ---------- List all ----------
    private static void listAllTasks() {
        List<Task> all = service.getAllTasksSortedByDeadline();
        if (all.isEmpty()) {
            System.out.println("\nNo tasks yet. Add something with option [1].");
            return;
        }
        System.out.println("\nALL TASKS (earliest deadlines first)");
        System.out.println("────────────────────────────────────────────────");
        for (Task t : all) {
            System.out.println(t);
        }
    }

    // ---------- List pending ----------
    private static void listPendingTasks() {
        List<Task> pending = service.getPendingTasksSortedByDeadline();
        if (pending.isEmpty()) {
            System.out.println("\n No pending tasks. You’re all caught up!");
            return;
        }
        System.out.println("\nPENDING TASKS");
        System.out.println("────────────────────────────────────────────────");
        for (Task t : pending) {
            System.out.println(t);
        }
    }

    // ---------- Mark done ----------
    private static void markTaskDone() {
        System.out.println("\nMARK TASK AS DONE");
        System.out.print("Enter task ID: ");
        String idStr = sc.nextLine().trim();
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
            return;
        }

        boolean ok = service.markDone(id);
        System.out.println(ok ? "Task #" + id + " marked as DONE."
                              : "Task ID not found.");
    }

    // ---------- Remove ----------
    private static void removeTask() {
        System.out.println("\nDELETE TASK");
        System.out.print("Enter task ID: ");
        String idStr = sc.nextLine().trim();
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
            return;
        }

        boolean ok = service.removeTask(id);
        System.out.println(ok ? "Task #" + id + " removed."
                              : "Task ID not found.");
    }
}