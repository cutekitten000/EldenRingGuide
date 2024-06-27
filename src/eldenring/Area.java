package eldenring;

import java.io.Serializable;
import java.util.List;

public class Area implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private List<Task> tasks;
    private String detailedDescription;
    private String questsDescription;

    public Area(String name, List<Task> tasks, String detailedDescription) {
        this.name = name;
        this.tasks = tasks;
        this.detailedDescription = detailedDescription;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

}
