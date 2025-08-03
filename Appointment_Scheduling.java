package appoinment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppointmentSchedulingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Clinic2 clinic = new Clinic2("AMRITA Clinic");
        clinic.addDoctor(new Doctor("1.Dr. John Doe", "Cardiologist"));
        clinic.addDoctor(new Doctor("2.Dr. Jane Smith", "Dermatologist"));
        clinic.addDoctor(new Doctor("3.Dr. Mike Johnson", "Pediatrician"));
        clinic.addDoctor(new Doctor("4.Dr. Sarah Williams", "Neurologist"));
        clinic.addDoctor(new Doctor("5.Dr. Emily Brown", "Oncologist"));
        clinic.addDoctor(new Doctor("6.Dr. David Davis", "Orthopedic Surgeon"));
        clinic.addDoctor(new Doctor("7.Dr. Laura Lee", "Psychiatrist"));
        clinic.addDoctor(new Doctor("8.Dr. Mark Miller", "Endocrinologist"));
        clinic.addDoctor(new Doctor("9.Dr. Anna Anderson", "Gynecologist"));
        clinic.addDoctor(new Doctor("10.Dr. Robert Robinson", "Urologist"));

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Schedule Appointment");
            System.out.println("2. View Doctor's Appointments");
            System.out.println("3. View Patient's Appointments");
            System.out.println("4. View Clinic's Doctors");
            System.out.println("5. Appoinment Cancellation");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Schedule appointment logic
                    System.out.print("Enter patient name: ");
                    String patientName = scanner.nextLine();
                    System.out.print("Enter patient age: ");
                    String patientAge = scanner.nextLine();
                    clinic.displayDoctors();
                    System.out.print("Enter doctor's number: ");
                    int doctorNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Doctor doctor = clinic.getDoctors().get(doctorNumber - 1);
                    System.out.print("Enter appointment date (YYYY-MM-DD): ");
                    String appointmentDate = scanner.nextLine();
                    System.out.print("Enter appointment time (HH:MM): ");
                    String appointmentTime = scanner.nextLine();
                    clinic.scheduleAppointment(doctor, patientName, appointmentDate, appointmentTime);
                    System.out.println("Appointment scheduled successfully!");
                    break;
                case 2:
                    // View doctor's appointments logic
                    System.out.println("Doctors:");
                    clinic.displayDoctors();
                    System.out.print("Enter the number of the doctor to view appointments: ");
                    int doctorIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (doctorIndex >= 1 && doctorIndex <= clinic.getDoctors().size()) {
                        Doctor doctor1 = clinic.getDoctors().get(doctorIndex - 1);
                        System.out.println("Appointments for " + doctor1.getName() + " - " + doctor1.getSpecialization() + ":");
                        doctor1.displayAppointments();    
                    } else {
                        System.out.println("Invalid doctor selection.");
                    }
                    break;

                case 3:
                    // View patient's appointments logic
                    System.out.print("Enter patient name: ");
                    String patientNameInput = scanner.nextLine();
                    
                    System.out.println("Appointments for patient: " + patientNameInput);
                    boolean foundAppointments = false;
                    for (Appointment appointment : clinic.getAppointments()) {
                        if (appointment.getPatientName().equalsIgnoreCase(patientNameInput)) {
                            foundAppointments = true;
                            Doctor doctorForAppointment = appointment.getDoctor();
                            System.out.println("Doctor: " + doctorForAppointment.getName());
                            System.out.println("Specialization: " + doctorForAppointment.getSpecialization());
                            System.out.println("Date: " + appointment.getAppointmentDate());
                            System.out.println("Time: " + appointment.getAppointmentTime());
                            System.out.println("--------------------------------------------");
                        }
                    }
                    if (!foundAppointments) {
                        System.out.println("No appointments found for patient: " + patientNameInput);
                    }
                    break;

                case 4:
                    clinic.displayDoctors();
                    System.out.print("Enter the number of the doctor you want to choose: ");
                    int doctorChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Doctor selectedDoctor = clinic.getDoctors().get(doctorChoice - 1);
                    System.out.println("You have chosen: " + selectedDoctor.getName() + " - " + selectedDoctor.getSpecialization());
                    break;
                case 5:
                    // Cancel appointment logic
                    System.out.println("Cancel Appointment");
                    clinic.displayDoctors();
                    System.out.print("Enter the number of the doctor: ");
                    int doctorChoiceCancel = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (doctorChoiceCancel >= 1 && doctorChoiceCancel <= clinic.getDoctors().size()) {
                        Doctor selectedDoctorCancel = clinic.getDoctors().get(doctorChoiceCancel - 1);
                        System.out.print("Enter the date of the appointment (YYYY-MM-DD): ");
                        String cancelDate = scanner.nextLine();
                        boolean appointmentCancelled = false;
                        for (Appointment appointment : clinic.getAppointments()) {
                            if (appointment.getDoctor().equals(selectedDoctorCancel) && appointment.getAppointmentDate().equals(cancelDate)) {
                                clinic.getAppointments().remove(appointment);
                                selectedDoctorCancel.getAppointments().remove(appointment);
                                appointmentCancelled = true;
                                System.out.println("Appointment successfully cancelled.");
                                break;
                            }
                        }
                        if (!appointmentCancelled) {
                            System.out.println("No appointment found for the specified doctor and date.");
                        }
                    } else {
                        System.out.println("Invalid doctor selection.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class Appointment {
    private Doctor doctor;
    private String patientName;
    private String appointmentDate;
    private String appointmentTime;

    public Appointment(Doctor doctor, String patientName, String appointmentDate, String appointmentTime) {
        this.doctor = doctor;
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }
}

class Doctor {
    private String name;
    private String specialization;
    private List<Appointment> appointments;

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
        this.appointments = new ArrayList<>();
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }


    

    public void displayAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled for this doctor.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println("Patient Name: " + appointment.getPatientName());
                System.out.println("Date: " + appointment.getAppointmentDate());
                System.out.println("Time: " + appointment.getAppointmentTime());
                System.out.println("--------------------------------------------");
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }
    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

	public void addAppointment(Appointment appointment) {
	    appointments.add(appointment);
	}

}

class Clinic2 {
    private List<Doctor> doctors;
    private List<Appointment> appointments;

    public Clinic2(String name) {
        this.doctors = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void displayDoctors() {
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". " + doctors.get(i).getName() + " - " + doctors.get(i).getSpecialization());
        }
    }

    public void scheduleAppointment(Doctor doctor, String patientName, String appointmentDate, String appointmentTime) {
        Appointment appointment = new Appointment(doctor, patientName, appointmentDate, appointmentTime);
        doctor.addAppointment(appointment);
        appointments.add(appointment);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}
