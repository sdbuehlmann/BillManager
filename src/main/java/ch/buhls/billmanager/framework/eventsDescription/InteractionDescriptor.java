package ch.buhls.billmanager.framework.eventsDescription;

import ch.buhls.billmanager.framework.propertyDescription.PropertiesView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author buhls§
 * @param <THandler>
 */
public class InteractionDescriptor<THandler>
{
    private final Class typeHandler;
    private final List<IntentDescriptor> intents;

    /**
     *
     * @param key
     * @param typeHandler The generic type THandler will be erased by compiler. But
     * this type will be used to render GUI elements, so the type needs to be
     * defined again.
     */
    public InteractionDescriptor(String key, Class typeHandler) {
        this.typeHandler = typeHandler;
        this.intents = new ArrayList<>();
    }

    public void addUserInteraction(String key, IUserIntent<THandler> intent){
        this.intents.add(new IntentDescriptor(this.typeHandler, key, intent, EIntentType.NORMAL));
    }

    public void addUserInteraction(String key, EIntentType type, IUserIntent<THandler> intent){
        this.intents.add(new IntentDescriptor(this.typeHandler, key, intent, type));
    }

    public void addUserInteraction(String key, EIntentType type, IUserIntent<THandler> intent, IExceptionHandler customExceptionHandler){
        this.intents.add(new IntentDescriptor(this.typeHandler, key, intent, type, customExceptionHandler));
    }

    // getters

    public Class getTypeHandler() {
        return typeHandler;
    }

    public List<IntentDescriptor> getIntents() {
        return intents;
    }
}
