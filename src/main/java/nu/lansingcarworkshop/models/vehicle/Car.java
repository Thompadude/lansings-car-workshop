package nu.lansingcarworkshop.models.vehicle;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue(value = "CAR")
public class Car extends Vehicle {

    public Car(String registrationPlate, String make, LocalDate modelYear, String fuel) {
        super(registrationPlate, make, modelYear, fuel);
    }

    public Car() {
    }

}