
package ch.buhls.billmanager.persistance.database.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author simon
 */
@Entity
public class PersonBaseData extends ATrackedEntity<PersonBaseData>
{
    private String name, prename;
    private String street, city;
    private String salutation, title;
    private String phoneP, phoneM, email;
    private String iban;
    
    private int postalcode;
    private int personalID;
    
    @Temporal(TemporalType.DATE)
    private Date birthday;

    public PersonBaseData() {
    }
    
    public PersonBaseData(PersonBaseData other){
        other.copyData(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoneP() {
        return phoneP;
    }

    public void setPhoneP(String phoneP) {
        this.phoneP = phoneP;
    }

    public String getPhoneM() {
        return phoneM;
    }

    public void setPhoneM(String phoneM) {
        this.phoneM = phoneM;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(int postalcode) {
        this.postalcode = postalcode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getPersonalID() {
        return personalID;
    }

    public void setPersonalID(int personalID) {
        this.personalID = personalID;
    }
    
    @Override
    public void copyData(PersonBaseData other) {
        super.copyData(other);
        
        other.name = name;
        other.prename = prename;
        other.street = street;
        other.city = city;
        other.salutation = salutation;
        other.title = title;
        other.phoneM = phoneM;
        other.phoneP = phoneP;
        other.email = email;
        other.iban = iban;
        other.postalcode = postalcode;
        other.personalID = personalID;
        
        if(birthday != null){
            other.birthday = new Date(birthday.getTime());
        }
    }
}
