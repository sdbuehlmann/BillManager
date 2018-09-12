
package ch.buhls.billmanager.gui;

import ch.buhls.billmanager.persistance.database.entities.FinancialYear;
import ch.buhls.billmanager.persistance.database.entities.Person;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simon
 */
public class AgePersonFilter implements IPersonFilter
{
    public enum AgeFilterType{
        EQUAL,
        OLDER,
        YOUNGER,
        OLDER_OR_EQUAL,
        YOUNGER_OR_EQUAL
    }
    
    private final AgeFilterType ageFilterType;
    private final FinancialYear year;
    private final int age;

    public AgePersonFilter(AgeFilterType ageFilterType, FinancialYear year, int age) {
        this.ageFilterType = ageFilterType;
        this.year = year;
        this.age = age;
    }

    @Override
    public List<Person> filterList(List<Person> persons) {
        List<Person> filteredPersons = new ArrayList<>();
        for(Person pers : persons){
            LocalDate tempBirthday = pers.getPersonBaseData().getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate tempFirstDay = year.getFirstDay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate tempLastDay = year.getLastDay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            
            switch(ageFilterType){
                case EQUAL:
                    if(Period.between(tempBirthday, tempLastDay).getYears() == age){
                        filteredPersons.add(pers);
                    }
                    break;
                case OLDER:
                    if(Period.between(tempBirthday, tempLastDay).getYears() < age){
                        filteredPersons.add(pers);
                    }
                    break;
                case YOUNGER:
                    if(Period.between(tempBirthday, tempFirstDay).getYears() > age){
                        filteredPersons.add(pers);
                    }
                    break;
                case OLDER_OR_EQUAL:
                    if(Period.between(tempBirthday, tempLastDay).getYears() >= age){
                        filteredPersons.add(pers);
                    }
                    break;
                case YOUNGER_OR_EQUAL:
                    if(Period.between(tempBirthday, tempLastDay).getYears() <= age){
                        filteredPersons.add(pers);
                    }
                    break;
            }
        }
        return filteredPersons;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        
        if(!(obj instanceof AgePersonFilter)){
            return false;
        }
        
        AgePersonFilter other = (AgePersonFilter)obj;
        
        if(!this.getYear().equals(other.getYear()) ||
                this.getAgeFilterType()!= other.getAgeFilterType() ||
                this.getAge() != other.getAge()){
            return false;
        }
        
        return true;
    }
    
    // getter

    public AgeFilterType getAgeFilterType() {
        return ageFilterType;
    }

    public FinancialYear getYear() {
        return year;
    }

    public int getAge() {
        return age;
    }
    
    
}
