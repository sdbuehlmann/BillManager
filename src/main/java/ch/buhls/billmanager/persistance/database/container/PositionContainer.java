
package ch.buhls.billmanager.persistance.database.container;

import ch.buhls.billmanager.persistance.database.entities.Position;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author simon
 */
public class PositionContainer extends AContainer<Position>
{

    public PositionContainer(EntityManager em)
    {
        super(Position.class, "Position", em);
    }

}
