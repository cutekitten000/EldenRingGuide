package eldenring;

import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void toggleCompleted() {
        this.completed = !this.completed;
    }

    @Override
    public String toString() {
        return (completed ? "[x] " : "[ ] ") + description;
    }
}
