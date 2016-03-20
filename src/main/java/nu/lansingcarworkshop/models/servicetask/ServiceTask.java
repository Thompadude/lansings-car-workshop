package nu.lansingcarworkshop.models.servicetask;

import nu.lansingcarworkshop.models.person.Employee;
import nu.lansingcarworkshop.models.vehicle.Vehicle;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table
public class ServiceTask implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDateTime time;
    private String note;
    private boolean isCompleted;

    @OneToOne
    private Employee responsibleEmployee;

    @OneToOne
    private Vehicle vehicle;

    public ServiceTask(LocalDateTime time, String note, Employee responsibleEmployee, Vehicle vehicle) {
        this.time = time;
        this.note = note;
        this.responsibleEmployee = responsibleEmployee;
        this.vehicle = vehicle;
        this.isCompleted = false;
    }

    public ServiceTask() {
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Employee getResponsibleEmployee() {
        return responsibleEmployee;
    }

    public void setResponsibleEmployee(Employee responsibleEmployee) {
        this.responsibleEmployee = responsibleEmployee;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getAppointmentTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return this.time.format(dateTimeFormatter);
    }

}