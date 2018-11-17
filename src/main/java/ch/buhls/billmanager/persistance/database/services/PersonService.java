package ch.buhls.billmanager.persistance.database.services;

import ch.buhls.billmanager.persistance.database.ContainerFactory;
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

    public PersonService(ContainerFactory factory) {
        super(factory, factory.getPersonContainer());

        this.positionService = new PositionService(factory);
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

        //this.handlePositions(managedEntity, entity);
        this.handleRoles(managedEntity, entity);
        this.handleBills(managedEntity, entity);
    }

    public List<Person> getPersonsByRole(Role role) {
        Query query = this.factory.getEntityManager().
                createQuery("SELECT p FROM Person p JOIN p.roles r WHERE r.id = " + role.getId(), Person.class);
        List<Person> list = query.getResultList();

        return list;
    }

    public Person getPersonByBaseData(PersonBaseData baseData){
        TypedQuery<Person> personQuery = this.factory.getEntityManager().
                createQuery("SELECT p FROM Person p WHERE p.personBaseData.id = " + baseData.getId(), Person.class);
        List<Person> list = personQuery.getResultList();
        
        if(!list.isEmpty()){
            return list.get(0);
        }
        
        // is a old bas data -> find newest
        TypedQuery<PersonBaseData> baseDataQuery = this.factory.getEntityManager().
                createQuery("SELECT pbd FROM PersonBaseData pbd WHERE pbd.previousVersion.id = " + baseData.getId(), PersonBaseData.class);
        
        PersonBaseData newest = null;
        
        for(PersonBaseData dbEntry : baseDataQuery.getResultList()){
            if(dbEntry.getFollowingVersion() == null){
                // newest version
                newest = dbEntry;
                break;
            }
        }
        
        personQuery = this.factory.getEntityManager().
                createQuery("SELECT p FROM Person p WHERE p.personBaseData.id = " + newest.getId(), Person.class);
        
        return personQuery.getSingleResult();
    }
    
    private void handlePositions(Person managedEntity, Person entity) throws ServiceException {
        if (listContainsDeltas(managedEntity.getBusket(), entity.getBusket())) {
            ListDeltasTO<Position> deltas = getDeltasInList(entity.getBusket(), managedEntity.getBusket());

            // add new positions to db
            for (Position pos : deltas.getNewElements()) {
                positionService.add(pos);
                managedEntity.getBusket().add(pos);
            }

            // handle changes in existing positions
            for (UpdatableEntityPaire<Position> posPaire : deltas.getExistingElements()) {
                positionService.update(posPaire.getSrcEntity());
            }

            // remove unused positions
            for (Position pos : deltas.getRemovedElements()) {
                managedEntity.getBusket().remove(pos);
                positionService.remove(pos);
            }
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
