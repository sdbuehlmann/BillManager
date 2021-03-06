package ch.buhls.billmanager.persistance.database.services;

import ch.buhls.billmanager.persistance.database.ContainerFactory;
import ch.buhls.billmanager.persistance.database.container.PositionContainer;
import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.persistance.database.entities.PersonBaseData;
import ch.buhls.billmanager.persistance.database.entities.Position;
import ch.buhls.billmanager.persistance.database.entities.Role;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author simon
 */
public class PersonService extends AService<Person>
{

    private PositionService positionService;
    private PositionContainer positionContainer;

    public PersonService(ContainerFactory factory) {
        super(factory, factory.getPersonContainer());

        this.positionService = new PositionService(factory);
        this.positionContainer = factory.getPositionContainer();
    }

    @Override
    public void add(Person entity) throws ServiceException {
        // check start conditions
        if (!entity.getBills().isEmpty()
                && !entity.getBusket().isEmpty()) {
            throw new ServiceException("Bills list or busket not empty (required to store new Person)");
        }

        super.add(entity);
    }

    @Override
    public void remove(Person entity) throws ServiceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void updateManagedEntity(Person managedEntity, Person entity) throws ServiceException {
        managedEntity.setPersonBaseData(entity.getPersonBaseData());

        this.handlePositions(managedEntity, entity);
        this.handleRoles(managedEntity, entity);
        this.handleBills(managedEntity, entity);
    }

    
    
    private void handlePositions(Person managedEntity, Person entity) throws ServiceException {
        if (listContainsDeltas(managedEntity.getBusket(), entity.getBusket())) {
            ListDeltasTO<Position> deltas = getDeltasInList(entity.getBusket(), managedEntity.getBusket());

//            // add new positions to db
//            for (Position pos : deltas.getNewElements()) {
//                positionService.add(pos);
//                managedEntity.getBusket().add(pos);
//            }
//
//            // handle changes in existing positions
//            for (UpdatableEntityPaire<Position> posPaire : deltas.getExistingElements()) {
//                positionService.update(posPaire.getSrcEntity());
//            }

            // remove unused positions
            for (Position pos : deltas.getRemovedElements()) {
                //Position managedPosition = positionContainer.findByID(pos.getId());
                positionService.remove(pos);
            }
            
            managedEntity.getBusket().clear();
            managedEntity.getBusket().addAll(entity.getBusket());
            
        }
    }

    private void handleRoles(Person managedEntity, Person entity) throws ServiceException {
        if (listContainsDeltas(managedEntity.getRoles(), entity.getRoles())) {
            managedEntity.getRoles().clear();
            managedEntity.getRoles().addAll(entity.getRoles());
        }
    }

    private void handleBills(Person managedEntity, Person entity) throws ServiceException {
        if (listContainsDeltas(managedEntity.getBills(), entity.getBills())) {
            managedEntity.getBills().clear();
            managedEntity.getBills().addAll(entity.getBills());
        }
    }
}
