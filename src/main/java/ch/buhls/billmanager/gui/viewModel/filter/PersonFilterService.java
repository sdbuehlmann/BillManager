
package ch.buhls.billmanager.gui.viewModel.filter;

import ch.buhls.billmanager.gui.viewModel.criteria.AgePersonCriteria;
import ch.buhls.billmanager.gui.viewModel.criteria.RolePersonCriteria;
import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.Role;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import ch.buhls.billmanager.gui.viewModel.criteria.ICriteria;


/**
 *
 * @author simon
 */
public class PersonFilterService implements IFilterService<Person>
{
    
    @Override
    public boolean doesElementMatchTheCriteria(ICriteria<Person> criteria, Person element) {
        if(criteria instanceof AgePersonCriteria){
            return this.doesEntityMatchTheCriteria((AgePersonCriteria)criteria, element);
        }
        if(criteria instanceof RolePersonCriteria){
            return this.doesEntityMatchTheCriteria((RolePersonCriteria)criteria, element);
        }
        
        return false;
    }
    
    // private methods
    private boolean doesEntityMatchTheCriteria(AgePersonCriteria criteria, Person pers) {
        if (pers.getPersonBaseData().getBirthday() == null) {
            return false;
        }
        LocalDate birthday = pers.getPersonBaseData().getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate lastDay = criteria.getYear().getLastDay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int age = criteria.getAge();

        switch (criteria.getAgeFilterType()) {
            case EQUAL:
                if (!(Period.between(birthday, lastDay).getYears() == age)) {
                    return false;
                }
                break;
            case OLDER:
                if (!(Period.between(birthday, lastDay).getYears() > age)) {
                    return false;
                }
                break;
            case YOUNGER:
                if (!(Period.between(birthday, lastDay).getYears() < age)) {
                    return false;
                }
                break;
            case OLDER_OR_EQUAL:
                if (!(Period.between(birthday, lastDay).getYears() >= age)) {
                    return false;
                }
                break;
            case YOUNGER_OR_EQUAL:
                if (!(Period.between(birthday, lastDay).getYears() <= age)) {
                    return false;
                }
                break;
        }

        return true;
    }

    private boolean doesEntityMatchTheCriteria(RolePersonCriteria criteria, Person pers) {
        Role role = criteria.getRole();

        switch (criteria.getRoleFilterType()) {
            case HIDE:
                if (pers.getRoles().contains(role)) {
                    return false;
                }
                break;
            case SHOW:
                if (!pers.getRoles().contains(role)) {
                    return false;
                }
                break;
        }

        return true;
    }
}
