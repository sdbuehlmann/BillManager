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
public class AgePersonFilter implements IFilter<Person>
{

    public enum AgeFilterType
    {
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
    public void filterList(List<Person> persons) {
        persons.removeAll(this.getElementsToRemoveSublist(persons));
    }
    
    @Override
    public List<Person> getElementsToRemoveSublist(List<Person> list) {
        List<Person> personsToRemove = new ArrayList<>();
        for (Person pers : list) {
            if (pers.getPersonBaseData().getBirthday() != null) {
                LocalDate birthday = pers.getPersonBaseData().getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate firstDay = year.getFirstDay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate lastDay = year.getLastDay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                switch (ageFilterType) {
                    case EQUAL:
                        if (!(Period.between(birthday, lastDay).getYears() == age)) {
                            personsToRemove.add(pers);
                        }
                        break;
                    case OLDER:
                        if (!(Period.between(birthday, lastDay).getYears() < age)) {
                            personsToRemove.add(pers);
                        }
                        break;
                    case YOUNGER:
                        if (!(Period.between(birthday, firstDay).getYears() > age)) {
                            personsToRemove.add(pers);
                        }
                        break;
                    case OLDER_OR_EQUAL:
                        if (!(Period.between(birthday, lastDay).getYears() >= age)) {
                            personsToRemove.add(pers);
                        }
                        break;
                    case YOUNGER_OR_EQUAL:
                        if (!(Period.between(birthday, lastDay).getYears() <= age)) {
                            personsToRemove.add(pers);
                        }
                        break;
                }
            }
        }
        return personsToRemove;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof AgePersonFilter)) {
            return false;
        }

        AgePersonFilter other = (AgePersonFilter) obj;

        if (!this.getYear().equals(other.getYear())
                || this.getAgeFilterType() != other.getAgeFilterType()
                || this.getAge() != other.getAge()) {
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
