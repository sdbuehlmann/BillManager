
package ch.buhls.billmanager.persistance.database.services;

import ch.buhls.billmanager.persistance.database.ContainerFactory;
import ch.buhls.billmanager.persistance.database.entities.PersonBaseData;

/**
 *
 * @author simon
 */
public class PersonBaseDataService extends AService<PersonBaseData>
{

    public PersonBaseDataService(ContainerFactory factory) {
        super(factory, factory.getPersonBaseDataContainer());
    }

    @Override
    protected void updateManagedEntity(PersonBaseData managedEntity, PersonBaseData entity) throws ServiceException {
        // update attributes
        managedEntity.setName(entity.getName());
        managedEntity.setPrename(entity.getPrename());
        managedEntity.setStreet(entity.getStreet());
        managedEntity.setPostalcode(entity.getPostalcode());
        managedEntity.setCity(entity.getCity());
        managedEntity.setPhoneM(entity.getPhoneM());
        managedEntity.setPhoneP(entity.getPhoneP());
        managedEntity.setSalutation(entity.getSalutation());
        managedEntity.setTitle(entity.getTitle());
        managedEntity.setBirthday(entity.getBirthday());
        managedEntity.setEmail(entity.getEmail());
        managedEntity.setIban(entity.getIban());
        managedEntity.setPersonId(entity.getPersonId());
        
        managedEntity.setPreviousVersion(entity.getPreviousVersion());
        managedEntity.setFollowingVersion(entity.getFollowingVersion());
        managedEntity.setChangeTxt(entity.getChangeTxt());        
    }
    
}
