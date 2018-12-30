package ch.buhls.billmanager.gui.viewModel.criteria;

import ch.buhls.billmanager.persistance.database.entities.FinancialYear;
import ch.buhls.billmanager.persistance.database.entities.Person;

/**
 *
 * @author simon
 */
public class AgePersonCriteria implements ICriteria<Person>
{

    private final AgeFilterType ageFilterType;
    private final FinancialYear year;
    private final int age;

    public AgePersonCriteria(AgeFilterType ageFilterType, FinancialYear year, int age) {
        this.ageFilterType = ageFilterType;
        this.year = year;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof AgePersonCriteria)) {
            return false;
        }

        AgePersonCriteria other = (AgePersonCriteria) obj;

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

    public enum AgeFilterType
    {
        EQUAL,
        OLDER,
        YOUNGER,
        OLDER_OR_EQUAL,
        YOUNGER_OR_EQUAL
    }
}
