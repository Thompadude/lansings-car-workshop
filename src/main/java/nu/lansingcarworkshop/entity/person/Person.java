package nu.lansingcarworkshop.entity.person;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "RELATION")

public abstract class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private Sex sex;

    @Embedded
    private ContactInformation contactInformation;

    private LocalDate birthdate;
    private String name;

    public Person(String name, ContactInformation contactInformation, LocalDate birthdate, Sex sex) {
        super();
        this.name = name;
        this.contactInformation = contactInformation;
        this.birthdate = birthdate;
        this.sex = sex;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getSexFormatted() {
        switch (sex) {
            case FEMALE:
                return "Female";
            case MALE:
                return "Male";
        }
        return null;
    }

}