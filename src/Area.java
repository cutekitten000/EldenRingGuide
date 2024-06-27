import java.io.Serializable;
import java.util.List;

class Area implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private List<Task> tasks;
	private String detailedDescription;
	private String questsDescription;

	public Area(String name, List<Task> tasks, String detailedDescription, String questsDescription) {
		this.name = name;
		this.tasks = tasks;
		this.detailedDescription = detailedDescription;
		this.questsDescription = questsDescription;
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

	public String getQuestsDescription() {
		return questsDescription;
	}
}