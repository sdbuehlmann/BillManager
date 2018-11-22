package ch.buhls.billmanager.persistance.database.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author simon
 */
@Entity
public class Person extends AEntity<Person>
{

    @OneToOne
    private PersonBaseData personBaseData;

    //@OneToMany
    private List<Position> busket;

    @OneToMany
    private List<Role> roles;

    //@OneToMany
    private List<Bill> bills;

    public Person() {
    }

    public Person(Person other) {
        other.copyData(this);
    }

    public PersonBaseData getPersonBaseData() {
        return personBaseData;
    }

    public void setPersonBaseData(PersonBaseData personBaseData) {
        this.personBaseData = personBaseData;
    }

    public List<Position> getBusket() {
        if (busket == null) {
            busket = new ArrayList<>();
        }

        return busket;
    }

    public void setBusket(List<Position> busket) {
        this.busket = busket;
    }

    public List<Role> getRoles() {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Bill> getBills() {
        if (bills == null) {
            bills = new ArrayList<>();
        }
        
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    @Override
    public void copyData(Person other) {
        super.copyData(other);
        
        other.getBills().addAll(this.getBills());
        other.getBusket().addAll(this.getBusket());
        other.getRoles().addAll(this.getRoles());
        
        other.setPersonBaseData(new PersonBaseData(this.getPersonBaseData()));
    }
    
    
}
