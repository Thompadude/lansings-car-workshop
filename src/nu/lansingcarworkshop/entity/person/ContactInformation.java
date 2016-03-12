package nu.lansingcarworkshop.entity.person;

import javax.persistence.Embeddable;

@Embeddable
public class ContactInformation {

    private String address, phonenumber;

    public ContactInformation(String address, String phonenumber) {
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public ContactInformation() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

}