import java.util.ArrayList;
import java.util.List;

interface Department {
    void assignDepartment(String department);
    String getDepartmentDetails();
}

abstract class Employee implements Department {
    private int employeeId;
    private String name;
    private double baseSalary;
    private String department;

    public Employee(int employeeId, String name, double baseSalary) {
        this.employeeId = employeeId;
        this.name = name;
        this.baseSalary = baseSalary;
        this.department = "Unassigned";
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public void assignDepartment(String department) {
        this.department = department;
    }

    @Override
    public String getDepartmentDetails() {
        return this.department;
    }

    public abstract double calculateSalary();

    public void displayDetails() {
        System.out.println("Employee ID: " + getEmployeeId());
        System.out.println("Name: " + getName());
        System.out.println("Department: " + getDepartmentDetails());
        System.out.println("Base Salary: " + getBaseSalary());
        System.out.println("Calculated Salary: " + calculateSalary());
    }
}

class FullTimeEmployee extends Employee {

    public FullTimeEmployee(int employeeId, String name, double baseSalary) {
        super(employeeId, name, baseSalary);
    }

    @Override
    public double calculateSalary() {
        // Full-time salary is typically the fixed base salary + potential bonuses (not implemented here)
        return getBaseSalary();
    }

     @Override
    public void displayDetails() {
        System.out.println("--- Full-Time Employee ---");
        super.displayDetails();
    }
}

class PartTimeEmployee extends Employee {
    private double hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(int employeeId, String name, double baseSalary, double hoursWorked, double hourlyRate) {
        super(employeeId, name, baseSalary); // Base salary might be nominal or 0 for PT
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        // Part-time salary based on hours worked and hourly rate
        return hoursWorked * hourlyRate;
    }

    @Override
    public void displayDetails() {
        System.out.println("--- Part-Time Employee ---");
        super.displayDetails(); // Display common details
        System.out.println("Hours Worked: " + getHoursWorked());
        System.out.println("Hourly Rate: " + getHourlyRate());
    }
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        FullTimeEmployee ftEmp1 = new FullTimeEmployee(101, "Alice Wonder", 60000.0);
        ftEmp1.assignDepartment("Engineering");

        PartTimeEmployee ptEmp1 = new PartTimeEmployee(102, "Bob The Builder", 0, 85.5, 22.50);
        ptEmp1.assignDepartment("Operations");

        FullTimeEmployee ftEmp2 = new FullTimeEmployee(103, "Charlie Chaplin", 75000.0);
        ftEmp2.assignDepartment("Finance");
        
        PartTimeEmployee ptEmp2 = new PartTimeEmployee(104, "Diana Ross", 0, 120, 30.00);
        ptEmp2.assignDepartment("Marketing");

        employees.add(ftEmp1);
        employees.add(ptEmp1);
        employees.add(ftEmp2);
        employees.add(ptEmp2);

        System.out.println("Processing Employee Details:");
        System.out.println("===========================");

        for (Employee emp : employees) {
            emp.displayDetails();
            System.out.println("---------------------------");
        }
    }
}