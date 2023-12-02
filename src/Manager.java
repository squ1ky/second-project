import java.util.HashMap;
import java.util.Scanner;
class Manager {

    private int currentId = 1;
    private HashMap<Integer, Task> tasksId = new HashMap<>();

    Scanner scanner = new Scanner(System.in);

    public void createTask() {
        System.out.print("Название задачи: ");
        String title = scanner.nextLine();
        System.out.print("Описание задачи: ");
        String description = scanner.nextLine();
        Task task = new Task(title, description, currentId);
        tasksId.put(currentId, task);
        currentId++;
    }

    public void printTask(int id) {
        Task currentTask = tasksId.get(id);
        System.out.println("Задача: " + currentTask.getTitle());
        System.out.println("Описание: " + currentTask.getDescription());
        System.out.println("Статус: " + currentTask.getStatus());
        System.out.println("Id = " + currentTask.getId());
    }

    public void clearAll() {
        tasksId.clear();
        System.out.println("Все задачи успешно удалены!");
    }

    public void deleteTask(int id) {
        tasksId.remove(id);
        System.out.println("Задача удалена!");
    }

}
