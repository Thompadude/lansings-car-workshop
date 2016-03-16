package nu.lansingcarworkshop.entity.servicetask;

import nu.lansingcarworkshop.entity.person.Employee;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table
public class ServiceTask implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDateTime time;
    private String note;

    @OneToOne
    private Employee responsibleEmployee;

    // TODO add car... customer...

    public ServiceTask(LocalDateTime time, String note, Employee responsibleEmployee) {
        super();
        this.time = time;
        this.note = note;
        this.responsibleEmployee = responsibleEmployee;
    }

    public ServiceTask() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Employee getResponsibleEmployee() {
        return responsibleEmployee;
    }

    public void setResponsibleEmployee(Employee responsibleEmployee) {
        this.responsibleEmployee = responsibleEmployee;
    }

}