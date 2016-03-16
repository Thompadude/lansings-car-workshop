package nu.lansingcarworkshop.entity.person;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Entity
@DiscriminatorValue(value = "STAFF")
public class Employee extends Person {

    @Enumerated(EnumType.ORDINAL)
    Role role;

    public Employee(String name, ContactInformation contactInformation, LocalDate birthdate, Sex sex, Role role) {
        super(name, contactInformation, birthdate, sex);
        this.role = role;
    }

    public Employee() {
    }

    public Role getRole() {
        return role;
    }

    public String getRoleFormatted() {
        switch (role) {
            case SPECIALIST:
                return "Specialist";
            case TECHNICIAN:
                return "Technician";
        }
        return null;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}