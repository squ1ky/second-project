import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<SubTask> epicSubTasks = new ArrayList<>();

    Epic() {
        this.title = "";
        this.description = "";
        this.id = 0;
        this.status = TaskStatus.NEW;
    }
    Epic(String title, String description, int id) {
        this.title = title;
        this.description = description;
        this.id = id;
    }

    public void addSubTask(SubTask subTask) {
        epicSubTasks.add(subTask);
    }

    public ArrayList<SubTask> getSubTasks() {
        return epicSubTasks;
    }

}
