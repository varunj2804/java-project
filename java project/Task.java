package objects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private int id;
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean done;

    public Task(int id, String title, String description, LocalDate deadline, boolean done) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        String statusIcon = done ? " " : " ";
        String statusText = done ? "DONE" : "PENDING";

        StringBuilder sb = new StringBuilder();
        sb.append("┌───────────────────────────────────────────────\n");
        sb.append(String.format("│ #%d  %s  %s%n", id, statusIcon, title));
        sb.append(String.format("│     🗓  %s  •  %s%n", deadline, statusText));
        if (description != null && !description.isEmpty()) {
            sb.append(String.format("│       %s%n", description));
        }
        sb.append("└───────────────────────────────────────────────");
        return sb.toString();
    }

    // ---------- File format helpers ----------

    // Convert to a line for file storage
    public String toLine() {
        // Use | as separator and escape | in text as \|
        String safeTitle = title.replace("|", "\\|");
        String safeDesc = (description == null ? "" : description).replace("|", "\\|");
        return id + "|" + safeTitle + "|" + safeDesc + "|" + deadline + "|" + done;
    }

    // Create Task from a stored line
    public static Task fromLine(String line) {
        // Split by | but handle escaped \|
        List<String> parts = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean esc = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '\\' && !esc) {
                esc = true;
                continue;
            }
            if (c == '|' && !esc) {
                parts.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
                esc = false;
            }
        }
        parts.add(current.toString());

        if (parts.size() < 5) return null;

        int id = Integer.parseInt(parts.get(0));
        String title = parts.get(1);
        String desc = parts.get(2);
        LocalDate deadline = LocalDate.parse(parts.get(3));
        boolean done = Boolean.parseBoolean(parts.get(4));
        return new Task(id, title, desc, deadline, done);
    }
}