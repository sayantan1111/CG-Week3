class Student1 {
    int rollNumber;
    String name;
    int age;
    String grade;
    Student1 next;

    public Student1(int rollNumber, String name, int age, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentLinkedList {
    Student1 head;

    public StudentLinkedList() {
        this.head = null;
    }

    public void addAtBeginning(int rollNumber, String name, int age, String grade) {
        Student1 newStudent = new Student1(rollNumber, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }

    public void addAtEnd(int rollNumber, String name, int age, String grade) {
        Student1 newStudent = new Student1(rollNumber, name, age, grade);
        if (head == null) {
            head = newStudent;
            return;
        }
        Student1 current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newStudent;
    }

    public void addAtPosition(int position, int rollNumber, String name, int age, String grade) {
        if (position <= 0) {
            addAtBeginning(rollNumber, name, age, grade);
            return;
        }
        Student1 newStudent = new Student1(rollNumber, name, age, grade);
        if (head == null) {
            if (position == 1) {
                head = newStudent;
            }
            return;
        }
        Student1 current = head;
        int count = 1;
        while (count < position - 1 && current != null) {
            current = current.next;
            count++;
        }
        if (current == null) {
            return;
        }
        newStudent.next = current.next;
        current.next = newStudent;
    }

    public void deleteByRollNumber(int rollNumber) {
        if (head == null) {
            return;
        }
        if (head.rollNumber == rollNumber) {
            head = head.next;
            return;
        }
        Student1 current = head;
        while (current.next != null && current.next.rollNumber != rollNumber) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    public void searchByRollNumber(int rollNumber) {
        Student1 current = head;
        while (current != null) {
            if (current.rollNumber == rollNumber) {
                System.out.println("Roll Number: " + current.rollNumber);
                System.out.println("Name: " + current.name);
                System.out.println("Age: " + current.age);
                System.out.println("Grade: " + current.grade);
                return;
            }
            current = current.next;
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    public void displayAllStudents() {
        Student1 current = head;
        if (current == null) {
            System.out.println("No student records found.");
            return;
        }
        while (current != null) {
            System.out.println("Roll Number: " + current.rollNumber);
            System.out.println("Name: " + current.name);
            System.out.println("Age: " + current.age);
            System.out.println("Grade: " + current.grade);
            System.out.println("--------------------");
            current = current.next;
        }
    }

    public void updateGrade(int rollNumber, String newGrade) {
        Student1 current = head;
        while (current != null) {
            if (current.rollNumber == rollNumber) {
                current.grade = newGrade;
                System.out.println("Grade updated for Roll Number " + rollNumber + " to " + newGrade);
                return;
            }
            current = current.next;
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    public static void main(String[] args) {
        StudentLinkedList studentList = new StudentLinkedList();
        studentList.addAtEnd(101, "Alice", 20, "A");
        studentList.addAtBeginning(102, "Bob", 21, "B");
        studentList.addAtPosition(2, 103, "Charlie", 19, "C");
        studentList.displayAllStudents();
        studentList.searchByRollNumber(102);
        studentList.deleteByRollNumber(103);
        System.out.println("After deleting Charlie:");
        studentList.displayAllStudents();
        studentList.updateGrade(101, "A+");
        studentList.searchByRollNumber(101);
    }
}