package appoinment;
import java.util.ArrayList;
import java.util.List;


// Abstract class to represent a user with appointments
abstract class User {
    private String name;
    private List<Appointment> appointments;

    public User(String name) {
        this.name = name;
        this.appointments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    // Abstract method to display user's appointments
    public abstract void displayAppointments();
}

// Doctor class extending User class
class Doctor extends User {
    private String specialization;

    public Doctor(String name, String specialization) {
        super(name);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public void displayAppointments() {
        List<Appointment> appointments = getAppointments();
        System.out.println("Appointments for Doctor " + getName() + " (" + getSpecialization() + "):");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }
}

// Patient class extending User class
class Patient extends User {
    private int age;

    public Patient(String name, int age) {
        super(name);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public void displayAppointments() {
        List<Appointment> appointments = getAppointments();
        System.out.println("Appointments for Patient " + getName() + " (Age: " + getAge() + "):");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }
}

// Appointment class
class Appointment {
    private String date;
    private String time;
    private String description;

    public Appointment(String date, String time, String description) {
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Time: " + time + ", Description: " + description;
    }
}

class Clinic {
    private String name;
    private List<Doctor> doctors;

    public Clinic(String name) {
        this.name = name;
        this.doctors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void displayDoctors() {
        System.out.println("Doctors at " + getName() + " clinic:");
        for (Doctor doctor : doctors) {
            System.out.println(doctor.getName() + " - " + doctor.getSpecialization());
        }
    }
}

// Main class to test the Appointment Scheduling System

