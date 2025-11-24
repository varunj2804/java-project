package manager;

import objects.Task;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskService {
    private static final String DATA_FILE = "tasks.txt";

    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public TaskService() {
        loadTasks();
    }

    // ---------- CRUD operations ----------

    public Task addTask(String title, String description, LocalDate deadline) {
        Task t = new Task(nextId++, title, description, deadline, false);
        tasks.add(t);
        return t;
    }

    public List<Task> getAllTasksSortedByDeadline() {
        List<Task> copy = new ArrayList<>(tasks);
        Collections.sort(copy, Comparator.comparing(Task::getDeadline));
        return copy;
    }

    public List<Task> getPendingTasksSortedByDeadline() {
        List<Task> pending = new ArrayList<>();
        for (Task t : tasks) {
            if (!t.isDone()) {
                pending.add(t);
            }
        }
        Collections.sort(pending, Comparator.comparing(Task::getDeadline));
        return pending;
    }

    public Task findById(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    public boolean markDone(int id) {
        Task t = findById(id);
        if (t == null) return false;
        t.setDone(true);
        return true;
    }

    public boolean removeTask(int id) {
        Task t = findById(id);
        if (t == null) return false;
        tasks.remove(t);
        return true;
    }

    // ---------- File load/save ----------

    private void loadTasks() {
        File f = new File(DATA_FILE);
        if (!f.exists()) {
            return; // no file yet, first run
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            int maxId = 0;
            while ((line = br.readLine()) != null) {
                Task t = Task.fromLine(line);
                if (t != null) {
                    tasks.add(t);
                    if (t.getId() > maxId) maxId = t.getId();
                }
            }
            nextId = maxId + 1;
        } catch (IOException e) {
            System.out.println("  Failed to load tasks: " + e.getMessage());
        }
    }

    public void saveTasks() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Task t : tasks) {
                bw.write(t.toLine());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("  Failed to save tasks: " + e.getMessage());
        }
    }
}