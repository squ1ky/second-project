public class Main {
    // TEST
    public static void main(String[] args) {
        InMemoryTaskManager manager = new InMemoryTaskManager();

        manager.createTask();
        manager.createTask();
        
        manager.createEpic();
        manager.createEpic();

        manager.getAllTasks();
        manager.getAllEpicSubTasks(3);
        manager.getAllEpicSubTasks(6);

        int[] history = manager.getHistory();
        for (int i = 0; i < history.length; i++) {
            if (history[i] == 0) {
                System.out.print("-" +  " ");
            }
            System.out.print(history[i] + " ");
        }
    }
}