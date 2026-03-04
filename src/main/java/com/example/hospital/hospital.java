package com.example.hospital;
 
import java.util.Scanner;
 
class Doctor {
    int id;
    String name;
    String specialty;
}
 
class Patient {
    int id;
    String name;
    int age;
    String gender;
    boolean admitted;
}
 
class Appointment {
    int id;
    int patientId;
    int doctorId;
    String date;
}
 
class Visit {
    int id;
    int patientId;
    int doctorId;
    String diagnosis;
    String prescription;
}
 
class Bill {
    int id;
    int patientId;
    double amount;
}
 
public class hospital {
    static Scanner sc = new Scanner(System.in);
 
    static Doctor[] doctors = new Doctor[50];
    static Patient[] patients = new Patient[100];
    static Appointment[] appointments = new Appointment[200];
    static Visit[] visits = new Visit[200];
    static Bill[] bills = new Bill[200];
 
    static int docCount = 0, patCount = 0, apptCount = 0, visitCount = 0, billCount = 0;
 
    static int inputInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(sc.nextLine());
    }
 
    static void addDoctor() {
        Doctor d = new Doctor();
        d.id = docCount + 1;
        System.out.print("Doctor Name: ");
        d.name = sc.nextLine();
        System.out.print("Specialty: ");
        d.specialty = sc.nextLine();
        doctors[docCount++] = d;
        System.out.println("Doctor added.\n");
    }
 
    static void addPatient() {
        Patient p = new Patient();
        p.id = patCount + 1;
        System.out.print("Patient Name: ");
        p.name = sc.nextLine();
        p.age = inputInt("Age: ");
        System.out.print("Gender: ");
        p.gender = sc.nextLine();
        System.out.print("Admit patient? (y/n): ");
        p.admitted = sc.nextLine().equalsIgnoreCase("y");
        patients[patCount++] = p;
        System.out.println("Patient added.\n");
    }
 
    static void listDoctors() {
        for (int i = 0; i < docCount; i++)
            System.out.println(doctors[i].id + ". " + doctors[i].name + " (" + doctors[i].specialty + ")");
        System.out.println();
    }
 
    static void listPatients() {
        for (int i = 0; i < patCount; i++)
            System.out.println(patients[i].id + ". " + patients[i].name + " - " + (patients[i].admitted ? "Admitted" : "OPD"));
        System.out.println();
    }
 
    static void scheduleAppointment() {
        listPatients();
        int pid = inputInt("Select Patient ID: ");
        listDoctors();
        int did = inputInt("Select Doctor ID: ");
 
        Appointment a = new Appointment();
        a.id = apptCount + 1;
        a.patientId = pid;
        a.doctorId = did;
        System.out.print("Appointment Date: ");
        a.date = sc.nextLine();
        appointments[apptCount++] = a;
 
        System.out.println("Appointment scheduled.\n");
    }
 
    static void recordVisit() {
        listPatients();
        int pid = inputInt("Patient ID: ");
        listDoctors();
        int did = inputInt("Doctor ID: ");
 
        Visit v = new Visit();
        v.id = visitCount + 1;
        v.patientId = pid;
        v.doctorId = did;
        System.out.print("Diagnosis: ");
        v.diagnosis = sc.nextLine();
        System.out.print("Prescription: ");
        v.prescription = sc.nextLine();
        visits[visitCount++] = v;
 
        System.out.println("Visit recorded.\n");
    }
 
    static void generateBill() {
        listPatients();
        int pid = inputInt("Patient ID: ");
 
        Bill b = new Bill();
        b.id = billCount + 1;
        b.patientId = pid;
        System.out.print("Enter bill amount: ");
        b.amount = Double.parseDouble(sc.nextLine());
        bills[billCount++] = b;
 
        System.out.println("Bill generated.\n");
    }
 
    static void dischargePatient() {
        listPatients();
        int pid = inputInt("Enter patient ID to discharge: ");
        for (int i = 0; i < patCount; i++) {
            if (patients[i].id == pid) {
                patients[i].admitted = false;
                System.out.println("Patient discharged.\n");
                return;
            }
        }
        System.out.println("Patient not found.\n");
    }
 
    static void showReports() {
        System.out.println("1. All Appointments");
        System.out.println("2. All Bills");
        System.out.println("3. All Visits");
        int ch = inputInt("Choose: ");
 
        if (ch == 1) {
            for (int i = 0; i < apptCount; i++)
                System.out.println("Appt " + appointments[i].id + ": Patient " + appointments[i].patientId + ", Doctor " + appointments[i].doctorId + ", Date " + appointments[i].date);
        } else if (ch == 2) {
            for (int i = 0; i < billCount; i++)
                System.out.println("Bill " + bills[i].id + ": Patient " + bills[i].patientId + ", Amount " + bills[i].amount);
        } else if (ch == 3) {
            for (int i = 0; i < visitCount; i++)
                System.out.println("Visit " + visits[i].id + ": Patient " + visits[i].patientId + ", Doctor " + visits[i].doctorId + ", Diagnosis " + visits[i].diagnosis);
        }
        System.out.println();
    }
 
    public static void main(String[] args) {
        while (true) {
            System.out.println("=== Hospital Menu ===");
            System.out.println("1. Add Doctor");
            System.out.println("2. Add Patient");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. Record Visit");
            System.out.println("5. Generate Bill");
            System.out.println("6. Discharge Patient");
            System.out.println("7. Reports");
            System.out.println("0. Exit");
 
            int ch = inputInt("Choose: ");
            if (ch == 1) addDoctor();
            else if (ch == 2) addPatient();
            else if (ch == 3) scheduleAppointment();
            else if (ch == 4) recordVisit();
            else if (ch == 5) generateBill();
            else if (ch == 6) dischargePatient();
            else if (ch == 7) showReports();
            else if (ch == 0) break;
            else System.out.println("Invalid choice.\n");
        }
    }
}