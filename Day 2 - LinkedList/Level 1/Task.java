class Task {
    int taskId;
    String taskName;
    int priority;
    String dueDate;
    Task next;

    public Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class TaskScheduler {
    Task head;
    Task currentTask;

    public TaskScheduler() {
        this.head = null;
        this.currentTask = null;
    }

    public void addTaskAtBeginning(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.next = head;
            currentTask = head;
            return;
        }
        newTask.next = head;
        Task tail = head;
        while (tail.next != head) {
            tail = tail.next;
        }
        tail.next = newTask;
        head = newTask;
        if (currentTask == null) {
            currentTask = head;
        }
    }

    public void addTaskAtEnd(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.next = head;
            currentTask = head;
            return;
        }
        Task tail = head;
        while (tail.next != head) {
            tail = tail.next;
        }
        tail.next = newTask;
        newTask.next = head;
        if (currentTask == null) {
            currentTask = head;
        }
    }

    public void addTaskAtPosition(int position, int taskId, String taskName, int priority, String dueDate) {
        if (position <= 0) {
            addTaskAtBeginning(taskId, taskName, priority, dueDate);
            return;
        }
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            if (position == 1) {
                head = newTask;
                head.next = head;
                currentTask = head;
            }
            return;
        }
        if (position == 1) {
            addTaskAtBeginning(taskId, taskName, priority, dueDate);
            return;
        }
        Task current = head;
        int count = 1;
        while (count < position - 1 && current.next != head) {
            current = current.next;
            count++;
        }
        if (count == position - 1) {
            newTask.next = current.next;
            current.next = newTask;
        }
    }

    public void removeTask(int taskId) {
        if (head == null) {
            return;
        }
        if (head.taskId == taskId) {
            if (head.next == head) {
                head = null;
                currentTask = null;
            } else {
                Task tail = head;
                while (tail.next != head) {
                    tail = tail.next;
                }
                head = head.next;
                tail.next = head;
                if (currentTask != null && currentTask.taskId == taskId) {
                    currentTask = head;
                }
            }
            return;
        }
        Task current = head;
        Task prev = null;
        while (current.next != head) {
            prev = current;
            current = current.next;
            if (current.taskId == taskId) {
                prev.next = current.next;
                if (currentTask != null && currentTask.taskId == taskId) {
                    currentTask = prev.next == head ? head : prev.next;
                }
                return;
            }
        }
    }

    public void viewCurrentTask() {
        if (currentTask == null) {
            System.out.println("No tasks in the scheduler.");
            return;
        }
        System.out.println("Current Task:");
        System.out.println("Task ID: " + currentTask.taskId);
        System.out.println("Task Name: " + currentTask.taskName);
        System.out.println("Priority: " + currentTask.priority);
        System.out.println("Due Date: " + currentTask.dueDate);
        System.out.println("--------------------");
    }

    public void moveToNextTask() {
        if (currentTask != null) {
            currentTask = currentTask.next;
        }
    }

    public void displayAllTasks() {
        if (head == null) {
            System.out.println("No tasks in the scheduler.");
            return;
        }
        System.out.println("All Tasks:");
        Task current = head;
        do {
            System.out.println("Task ID: " + current.taskId);
            System.out.println("Task Name: " + current.taskName);
            System.out.println("Priority: " + current.priority);
            System.out.println("Due Date: " + current.dueDate);
            System.out.println("--------------------");
            current = current.next;
        } while (current != head);
    }

    public void searchTaskByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks in the scheduler.");
            return;
        }
        boolean found = false;
        Task current = head;
        do {
            if (current.priority == priority) {
                System.out.println("Task ID: " + current.taskId);
                System.out.println("Task Name: " + current.taskName);
                System.out.println("Priority: " + current.priority);
                System.out.println("Due Date: " + current.dueDate);
                System.out.println("--------------------");
                found = true;
            }
            current = current.next;
        } while (current != head);
        if (!found) {
            System.out.println("No tasks found with priority " + priority);
        }
    }

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        scheduler.addTaskAtEnd(1, "Write Report", 2, "2025-04-20");
        scheduler.addTaskAtBeginning(2, "Prepare Presentation", 1, "2025-04-18");
        scheduler.addTaskAtPosition(2, 3, "Review Code", 3, "2025-04-22");
        scheduler.displayAllTasks();
        scheduler.viewCurrentTask();
        scheduler.moveToNextTask();
        scheduler.viewCurrentTask();
        scheduler.removeTask(2);
        System.out.println("After removing task 2:");
        scheduler.displayAllTasks();
        scheduler.searchTaskByPriority(3);
    }
}