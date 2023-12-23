import java.io.IOError;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Set;
import java.util.LinkedHashSet;
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

interface HistoryManager {
    void add(Task task);
    void remove(int id);
    List<Task> getHistory();
}

class InMemoryTaskManager implements TaskManager, HistoryManager {
    private final List<Task> historyTemp = new ArrayList<>();
    private int currentId = 1;
    public final HashMap<Integer, Task> tasksId = new HashMap<>();
    Scanner scanner = new Scanner(System.in);

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
        add(currentTask);
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
            tasksId.put(currentId, subTask);
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
            add(currentSubTasks.get(i));
        }
    }

    @Override
    public void clearAll() {
        tasksId.clear();
        System.out.println("Все задачи успешно удалены!");
        historyTemp.clear();
    }

    @Override
    public void deleteTask(int id) {
        int cnt = 1;
        tasksId.remove(id);
        for (Integer currentId : tasksId.keySet()) {
            currentId = cnt;
            cnt++;
        }
        for (Task task : historyTemp) {
            if (task.getId() == id) {
                historyTemp.remove((Integer) id);
            }
        }
        System.out.println("Задача №" + id +  " удалена!");
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


    // HISTORY MANAGER SECTION

    private Set<Task> getTasks() {
        Set<Task> taskSet = new LinkedHashSet<>(historyTemp);
        return taskSet;
    }
    @Override
    public void add(Task task) {
        historyTemp.add(task);
    }

    @Override
    public void remove(int id) {
        historyTemp.remove(id);
    }

    @Override
    public List<Task> getHistory() {
        final List<Task> history = new LinkedList<>(getTasks());
        int n = history.size();
        if (n <= 10) {
            return history;
        }
        int i = 0;
        while (i != n - 10) {
            history.removeFirst();
            i++;
        }
        return history;

        // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11] SIZE = 11 I = 0
    }

}
