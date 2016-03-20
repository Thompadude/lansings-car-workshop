package nu.lansingcarworkshop.models.person;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue(value = "CUSTOMER")
public class Customer extends Person {

    public Customer(String name, ContactInformation contactInformation, LocalDate birthdate, Sex sex) {
        super(name, contactInformation, birthdate, sex);
    }

    public Customer() {
    }

}