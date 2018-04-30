
package ch.buhls.billmanager.gui.data;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.properties.IPropertyData;
import ch.buhls.billmanager.gui.data.properties.IntegerAdapterProperty;
import ch.buhls.billmanager.persistance.database.entities.Position;
import javafx.beans.property.IntegerProperty;

/**
 *
 * @author simon
 */
public class GUIPosition extends AGUIData<Position>
{
    private final IntegerProperty amount, position;
    
    private final GUIArticle guiArticle;
    
    public GUIPosition(Position t, GUIArticle guiArticle) {
        super(t);
        
        this.guiArticle = guiArticle;
        
        amount = new IntegerAdapterProperty(new IPropertyData<Integer>()
        {
            @Override
            public Integer get() {
                return data.getNumber();
            }

            @Override
            public void set(Integer set) {
                data.setNumber(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return GUIStringCollection.POSITION_AMOUNT;
            }
        });
        position = new IntegerAdapterProperty(new IPropertyData<Integer>()
        {
            @Override
            public Integer get() {
                return data.getPosition();
            }

            @Override
            public void set(Integer set) {
                data.setPosition(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return GUIStringCollection.POSITION_POS;
            }
        });
    }

    public IntegerProperty getAmount() {
        return amount;
    }

    public IntegerProperty getPosition() {
        return position;
    }

    public GUIArticle getGuiArticle() {
        return guiArticle;
    }
    
    
}
