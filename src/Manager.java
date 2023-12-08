import java.util.HashMap;
import java.util.ArrayList;
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

    public void createEpic() {

        System.out.print("Название эпика: ");
        String title = scanner.nextLine();
        System.out.print("Описание эпика: ");
        String description = scanner.nextLine();

        Epic epic = new Epic(title, description, currentId);
        tasksId.put(currentId, epic);
        currentId++;

        int cntSubTasks;
        System.out.print("Количество подзадач: ");
        cntSubTasks = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < cntSubTasks; i++) {
            System.out.print("Название подзадачи: ");
            String titleSub = scanner.nextLine();
            System.out.print("Описание подзадачи: ");
            String descripSub = scanner.nextLine();
            SubTask subTask = new SubTask(titleSub, descripSub, currentId);
            epic.addSubTask(subTask);
            currentId++;
        }

    }

    public void getAllEpicSubTasks(int id) {
        Epic epic = (Epic) tasksId.get(id);
        ArrayList<SubTask> currentSubTasks = epic.getSubTasks();
        for (int i = 0; i < currentSubTasks.size(); i++) {
            System.out.println("Название задачи: " + currentSubTasks.get(i).getTitle());
            System.out.println("Описание: " + currentSubTasks.get(i).getDescription());
            System.out.println("Статус: " + currentSubTasks.get(i).getStatus());
            System.out.println("ID: " + currentSubTasks.get(i).getId());
        }
    }

    public void clearAll() {
        tasksId.clear();
        System.out.println("Все задачи успешно удалены!");
    }

    public void deleteTask(int id) {
        int cnt = 1;
        tasksId.remove(id);
        for (Integer currentId : tasksId.keySet()) {
            currentId = cnt;
            cnt++;
        }
        System.out.println("Задача удалена!");

    }

    public void updateTask(Task task, String status) {
        task.setId(currentId);
        if (status.equals("IN PROGRESS") || status.equals("Done")) {
            task.setStatus(status);
        }
        currentId++;
    }
    public void getAllTasks() {
        for (Integer i : tasksId.keySet()) {
            this.printTask(i);
        }
    }

}
