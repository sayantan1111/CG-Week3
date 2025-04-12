import java.util.ArrayList;
import java.util.List;

abstract class Patient {
    private String patientId;
    private String name;
    private int age;

    public Patient(String patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void getPatientDetails() {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }

    public abstract double calculateBill();
}

class InPatient extends Patient {
    private int daysAdmitted;
    private double roomChargePerDay;

    public InPatient(String patientId, String name, int age, int daysAdmitted, double roomChargePerDay) {
        super(patientId, name, age);
        this.daysAdmitted = daysAdmitted;
        this.roomChargePerDay = roomChargePerDay;
    }

    public int getDaysAdmitted() {
        return daysAdmitted;
    }

    public double getRoomChargePerDay() {
        return roomChargePerDay;
    }

    @Override
    public double calculateBill() {
        return daysAdmitted * roomChargePerDay;
    }
}

class OutPatient extends Patient {
    private double consultationFee;
    private double treatmentCost;

    public OutPatient(String patientId, String name, int age, double consultationFee, double treatmentCost) {
        super(patientId, name, age);
        this.consultationFee = consultationFee;
        this.treatmentCost = treatmentCost;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public double getTreatmentCost() {
        return treatmentCost;
    }

    @Override
    public double calculateBill() {
        return consultationFee + treatmentCost;
    }
}

interface MedicalRecord {
    void addRecord(String record);
    void viewRecords();
}

class PatientMedicalRecord implements MedicalRecord {
    private String patientId;
    private List<String> records;

    public PatientMedicalRecord(String patientId) {
        this.patientId = patientId;
        this.records = new ArrayList<>();
    }

    public String getPatientId() {
        return patientId;
    }

    @Override
    public void addRecord(String record) {
        records.add(record);
    }

    @Override
    public void viewRecords() {
        System.out.println("Medical Records for Patient ID: " + patientId);
        for (String record : records) {
            System.out.println("- " + record);
        }
    }
}

class Hospital {
    public static void main(String[] args) {
        List<Patient> patients = new ArrayList<>();
        patients.add(new InPatient("IP001", "Alice Smith", 35, 5, 150.0));
        patients.add(new OutPatient("OP002", "Bob Johnson", 60, 75.0, 200.0));

        for (Patient patient : patients) {
            patient.getPatientDetails();
            System.out.println("Total Bill: " + patient.calculateBill());
            System.out.println("--------------------");
        }

        Patient inpatient = new InPatient("IP003", "Charlie Brown", 45, 10, 175.0);
        inpatient.getPatientDetails();
        System.out.println("Total Bill for " + inpatient.getName() + ": " + inpatient.calculateBill());

        Patient outpatient = new OutPatient("OP004", "Diana Miller", 28, 60.0, 120.0);
        outpatient.getPatientDetails();
        System.out.println("Total Bill for " + outpatient.getName() + ": " + outpatient.calculateBill());

        PatientMedicalRecord record1 = new PatientMedicalRecord("IP001");
        record1.addRecord("Admitted for pneumonia.");
        record1.addRecord("Started on antibiotics.");
        record1.viewRecords();

        PatientMedicalRecord record2 = new PatientMedicalRecord("OP002");
        record2.addRecord("Follow-up consultation.");
        record2.addRecord("Prescribed pain medication.");
        record2.viewRecords();
    }
}