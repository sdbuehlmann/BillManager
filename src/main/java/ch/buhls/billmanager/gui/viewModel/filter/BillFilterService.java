package ch.buhls.billmanager.gui.viewModel.filter;

import ch.buhls.billmanager.gui.viewModel.criteria.BillRoleCriteria;
import ch.buhls.billmanager.gui.viewModel.criteria.BillStatusCriteria;
import ch.buhls.billmanager.persistance.database.entities.Bill;
import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.Role;
import ch.buhls.billmanager.gui.viewModel.criteria.ICriteria;
import ch.buhls.billmanager.persistance.database.container.EntityNotFoundException;
import ch.buhls.billmanager.persistance.database.container.PersonContainer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author simon
 */
public class BillFilterService implements IFilterService<Bill>
{

    private static final Logger LOGGER = Logger.getLogger(BillFilterService.class.getName());

    private final IBillFilterServiceCallback callback;

    public BillFilterService(IBillFilterServiceCallback callback) {
        this.callback = callback;
    }

    @Override
    public boolean doesElementMatchTheCriteria(ICriteria<Bill> criteria, Bill element) {
        if (criteria instanceof BillRoleCriteria) {
            return this.doesEntityMatchTheCriteria((BillRoleCriteria) criteria, element);
        }
        if (criteria instanceof BillStatusCriteria) {
            return this.doesEntityMatchTheCriteria((BillStatusCriteria) criteria, element);
        }

        return false;
    }

    private boolean doesEntityMatchTheCriteria(BillRoleCriteria criteria, Bill bill) {
        Role role = criteria.getRole();

        // Caution: This is a dirty hack which costs a lot of db power.
        // ToDo: Editing the data model to access the person of a bill on a easier way.
        Person person;
        try {
            person = callback.getPersonByBaseData(bill.getPersonBaseData());
        }
        catch (EntityNotFoundException ex) {
            LOGGER.log(Level.SEVERE,
                    String.format("Can not find the person with id %d to check roles", bill.getPersonBaseData().getPersonId()),
                    ex);

            return false;
        }

        if (!person.getRoles().contains(role)) {
            return false;
        }

        return true;
    }

    private boolean doesEntityMatchTheCriteria(BillStatusCriteria criteria, Bill bill) {
        switch (criteria.getStatus()) {
            case ALL:
                return true;
            case PAID:
                return (bill.getBillState() == Bill.BillState.PAID);
            case SENDET:
                return (bill.getBillState() == Bill.BillState.SENDET);
            case STORNO:
                return (bill.getBillState() == Bill.BillState.CANCELED);
        }

        return false;
    }

}
