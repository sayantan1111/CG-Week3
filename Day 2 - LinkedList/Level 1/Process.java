class Process {
    int processId;
    int burstTime;
    int priority;
    int remainingTime;
    int waitingTime;
    int turnAroundTime;
    Process next;

    public Process(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
        this.waitingTime = 0;
        this.turnAroundTime = 0;
        this.next = null;
    }
}

class RoundRobinScheduler {
    Process head;
    Process current;
    int numberOfProcesses;
    int currentTime;

    public RoundRobinScheduler() {
        this.head = null;
        this.current = null;
        this.numberOfProcesses = 0;
        this.currentTime = 0;
    }

    public void addProcess(int processId, int burstTime, int priority) {
        Process newProcess = new Process(processId, burstTime, priority);
        if (head == null) {
            head = newProcess;
            head.next = head;
            current = head;
        } else {
            Process tail = head;
            while (tail.next != head) {
                tail = tail.next;
            }
            tail.next = newProcess;
            newProcess.next = head;
        }
        numberOfProcesses++;
    }

    public void removeProcess(int processId) {
        if (head == null) {
            return;
        }
        if (head.processId == processId) {
            if (head.next == head) {
                head = null;
                current = null;
            } else {
                Process tail = head;
                while (tail.next != head) {
                    tail = tail.next;
                }
                head = head.next;
                tail.next = head;
                if (current.processId == processId) {
                    current = head;
                }
            }
            numberOfProcesses--;
            return;
        }
        Process prev = head;
        Process curr = head.next;
        while (curr != head) {
            if (curr.processId == processId) {
                prev.next = curr.next;
                if (current.processId == processId) {
                    current = prev.next == head ? head : prev.next;
                }
                numberOfProcesses--;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    public void simulateRoundRobin(int timeQuantum) {
        if (head == null) {
            System.out.println("No processes to schedule.");
            return;
        }
        System.out.println("Starting Round Robin Scheduling with Time Quantum: " + timeQuantum);
        int completedProcesses = 0;
        int[] finishTime = new int[numberOfProcesses];
        java.util.Map<Integer, Integer> processIndexMap = new java.util.HashMap<>();
        Process temp = head;
        for (int i = 0; i < numberOfProcesses; i++) {
            processIndexMap.put(temp.processId, i);
            temp = temp.next;
        }

        Process currentProcess = head;
        while (completedProcesses < numberOfProcesses) {
            System.out.println("Current Time: " + currentTime);
            displayProcesses();
            if (currentProcess.remainingTime > 0) {
                int executionTime = Math.min(timeQuantum, currentProcess.remainingTime);
                currentProcess.remainingTime -= executionTime;
                currentTime += executionTime;
                if (currentProcess.remainingTime == 0) {
                    completedProcesses++;
                    finishTime[processIndexMap.get(currentProcess.processId)] = currentTime;
                }
            }
            currentProcess = currentProcess.next;
        }

        calculateTimes(finishTime, processIndexMap);
    }

    private void calculateTimes(int[] finishTime, java.util.Map<Integer, Integer> processIndexMap) {
        Process temp = head;
        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;
        int[] waitingTimeArray = new int[numberOfProcesses];
        int[] turnAroundTimeArray = new int[numberOfProcesses];
        java.util.Map<Integer, Integer> burstTimeMap = new java.util.HashMap<>();
        Process tempBurst = head;
        do {
            burstTimeMap.put(tempBurst.processId, tempBurst.burstTime);
            tempBurst = tempBurst.next;
        } while (tempBurst != head);

        do {
            int index = processIndexMap.get(temp.processId);
            turnAroundTimeArray[index] = finishTime[index];
            waitingTimeArray[index] = turnAroundTimeArray[index] - burstTimeMap.get(temp.processId);
            totalWaitingTime += waitingTimeArray[index];
            totalTurnAroundTime += turnAroundTimeArray[index];
            temp = temp.next;
        } while (temp != head);

        System.out.println("\nProcess Scheduling Results:");
        System.out.println("--------------------------------------------------");
        System.out.println("Process ID | Burst Time | Waiting Time | Turnaround Time");
        System.out.println("--------------------------------------------------");
        Process resultTemp = head;
        do {
            System.out.printf("%-12d|%-12d|%-14d|%-16d\n", resultTemp.processId, resultTemp.burstTime, waitingTimeArray[processIndexMap.get(resultTemp.processId)], turnAroundTimeArray[processIndexMap.get(resultTemp.processId)]);
            resultTemp = resultTemp.next;
        } while (resultTemp != head);
        System.out.println("--------------------------------------------------");

        double averageWaitingTime = (double) totalWaitingTime / numberOfProcesses;
        double averageTurnAroundTime = (double) totalTurnAroundTime / numberOfProcesses;

        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnAroundTime);
    }

    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in the queue.");
            return;
        }
        System.out.println("Processes in Queue:");
        Process currentProcess = head;
        do {
            System.out.println("ID: " + currentProcess.processId + ", Remaining Time: " + currentProcess.remainingTime + ", Priority: " + currentProcess.priority);
            currentProcess = currentProcess.next;
        } while (currentProcess != head);
        System.out.println("--------------------");
    }

    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler();
        scheduler.addProcess(1, 10, 2);
        scheduler.addProcess(2, 5, 1);
        scheduler.addProcess(3, 8, 3);
        scheduler.simulateRoundRobin(2);
    }
}