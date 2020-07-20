package org.docreg.app;
import java.io.Serializable;
import java.util.Date;

class AppointmentModel implements Serializable {
    private final int appointmentNumber;
    private final String doc1;
    private final Date registrationDateTime;
    private final Date expiryDateTime;
    private final String problem;
    private final Date appointmentDate;

    public AppointmentModel(int appointmentNumber, String doc1, Date registrationDateTime, Date expiryDateTime, String problem, Date appointmentDate) {
        this.appointmentNumber = appointmentNumber;
        this.doc1 = doc1;
        this.registrationDateTime = registrationDateTime;
        this.expiryDateTime = expiryDateTime;
        this.problem = problem;
        this.appointmentDate = appointmentDate;
    }

    public int getAppointmentNumber() {
        return appointmentNumber;
    }

    public String getDoc1() {
        return doc1;
    }

    public Date getRegistrationDateTime() {
        return registrationDateTime;
    }

    public Date getExpiryDateTime() {
        return expiryDateTime;
    }

    public String getProblem() {
        return problem;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }
}
