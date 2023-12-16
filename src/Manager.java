import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;


interface TaskManager {
    void createTask();
    void printTask(int id);
    void createEpic();
    void getAllEpicSubTasks(int id);
    void clearAll();
    void deleteTask(int id);
    void updateTask(Task task, TaskStatus status);
    void getAllTasks();

}

class InMemoryTaskManager implements TaskManager {
    private final int[] history = new int[10];
    private int historyPtr = 0;

    private int currentId = 1;
    private final HashMap<Integer, Task> tasksId = new HashMap<>();
    Scanner scanner = new Scanner(System.in);

    private void historyAdd(int id) {

        if (historyPtr == 10) {
            historyPtr = 0;
        }

        history[historyPtr] = id;
        historyPtr++;
    }

    @Override
    public void createTask() {

        System.out.print("Название задачи: ");
        String title = scanner.nextLine();
        System.out.print("Описание задачи: ");
        String description = scanner.nextLine();

        Task task = new Task(title, description, currentId);
        tasksId.put(currentId, task);
        currentId++;
    }

    @Override
    public void printTask(int id) {
        Task currentTask = tasksId.get(id);
        System.out.println("Задача: " + currentTask.getTitle());
        System.out.println("Описание: " + currentTask.getDescription());
        System.out.println("Статус: " + currentTask.getStatus());
        System.out.println("Id = " + currentTask.getId());
        historyAdd(id);
    }

    @Override
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

    @Override
    public void getAllEpicSubTasks(int id) {
        Epic epic = (Epic) tasksId.get(id);
        ArrayList<SubTask> currentSubTasks = epic.getSubTasks();
        for (int i = 0; i < currentSubTasks.size(); i++) {
            System.out.println("Название задачи: " + currentSubTasks.get(i).getTitle());
            System.out.println("Описание: " + currentSubTasks.get(i).getDescription());
            System.out.println("Статус: " + currentSubTasks.get(i).getStatus());
            System.out.println("ID: " + currentSubTasks.get(i).getId());
            historyAdd(currentSubTasks.get(i).getId());
        }
    }

    @Override
    public void clearAll() {
        tasksId.clear();
        System.out.println("Все задачи успешно удалены!");
    }

    @Override
    public void deleteTask(int id) {
        int cnt = 1;
        tasksId.remove(id);
        for (Integer currentId : tasksId.keySet()) {
            currentId = cnt;
            cnt++;
        }
        System.out.println("Задача удалена!");

    }

    @Override
    public void updateTask(Task task, TaskStatus status) {
        task.setId(currentId);
        if (status == TaskStatus.IN_PROGRESS || status == TaskStatus.DONE) {
            task.setStatus(status);
        }
        currentId++;
    }

    @Override
    public void getAllTasks() {
        for (Integer i : tasksId.keySet()) {
            this.printTask(i);
        }
    }

    public int[] getHistory() {
        return history;
    }
}
