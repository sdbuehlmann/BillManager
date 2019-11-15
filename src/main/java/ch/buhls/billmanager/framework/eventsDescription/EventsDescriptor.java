package ch.buhls.billmanager.framework.eventsDescription;

import ch.buhls.billmanager.framework.propertyDescription.PropertiesView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author buhls
 * @param <TOwner>
 */
public class EventsDescriptor<TOwner>
{
    private PropertiesView<TOwner> properties;
    
    private final List<IUserIntent> intents;

    public EventsDescriptor()
    {
        intents = new ArrayList<>();
    }
    
    
    
    public void addUserIntent(String key, IUserIntent intent){
        
    }
}
