public class Main {
    // TEST
    public static void main(String[] args) {
        Manager manager = new Manager();

        manager.createTask();
        manager.createTask();

        manager.createEpic();
        manager.createEpic();

        manager.getAllTasks();
        manager.getAllEpicSubTasks(3);
        manager.getAllEpicSubTasks(6);
    }
}