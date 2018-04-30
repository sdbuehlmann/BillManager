
package ch.buhls.billmanager.persistance.database.services;

import ch.buhls.billmanager.persistance.database.ContainerFactory;
import ch.buhls.billmanager.persistance.database.entities.Position;


/**
 *
 * @author simon
 */
public class PositionService extends AService<Position>
{
    public PositionService(ContainerFactory factory)
    {
        super(factory, factory.getPositionContainer());
    }

    @Override
    protected void updateManagedEntity(Position managedEntity, Position entity)
    {
        managedEntity.setNumber(entity.getNumber());
        managedEntity.setPosition(entity.getPosition());
        managedEntity.setArticle(entity.getArticle());
    }
    
}
