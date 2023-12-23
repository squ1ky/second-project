public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager manager = new InMemoryTaskManager();

        manager.createTask(); // ID = 1
        manager.createTask(); // ID = 2

        manager.createEpic(); // 10 подзадач EPIC ID = 3 SubTASKS ID = 4 - 13
        manager.createEpic(); // 4 подзадачи EPIC ID = 14 SubTASKS ID = 15 - 18

        manager.printTask(1);
        manager.printTask(2);
        manager.printTask(3);
        manager.printTask(4);
        manager.printTask(5);
        manager.printTask(6);
        manager.printTask(7);
        manager.printTask(8);
        manager.printTask(9);
        manager.printTask(10);
        manager.printTask(10);
        manager.printTask(11);
        manager.printTask(1);
        manager.printTask(12);
        manager.printTask(2);
        manager.printTask(16);
        manager.printTask(15);

        manager.deleteTask(14);
        manager.deleteTask(2);
        for (Task task : manager.getHistory()) {
            System.out.print(task.getId() + " ");
        }
    }
}


