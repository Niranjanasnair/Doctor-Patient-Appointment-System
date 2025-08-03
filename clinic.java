package appoinment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Clinic {
    private List<Doctor> doctors;
    private Map<Doctor, List<Appointment>> doctorAppointments;

    public Clinic(String name) {
        doctors = new ArrayList<>();
        doctorAppointments = new HashMap<>();
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        doctorAppointments.put(doctor, new ArrayList<>());
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
        Appointment appointment = new Appointment(patientName, appointmentDate, appointmentTime);
        List<Appointment> appointments = doctorAppointments.get(doctor);
        appointments.add(appointment);
        doctorAppointments.put(doctor, appointments);
    }
}
