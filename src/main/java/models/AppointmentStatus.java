package models;

public enum AppointmentStatus {
    AVAILABLE,  // Appointment has not been booked
    BOOKED,     // Appointment is booked by a patient
    CANCELED,   // Appointment was canceled
    ATTENDED    // Patient attended the appointment
}