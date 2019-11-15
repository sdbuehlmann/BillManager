package ch.buhls.billmanager.framework.eventsDescription;

import java.util.Optional;

public class IntentDescriptor {
    private final Class typeHandler;
    private final String key;
    private final IUserIntent intent;
    private final EIntentType type;
    private final Optional<IExceptionHandler> optionalExceptionHandler;

    public IntentDescriptor(Class typeHandler, String key, IUserIntent intent, EIntentType type) {
        this.typeHandler = typeHandler;
        this.key = key;
        this.intent = intent;
        this.type = type;
        this.optionalExceptionHandler = Optional.empty();
    }

    public IntentDescriptor(Class typeHandler, String key, IUserIntent intent, EIntentType type, IExceptionHandler exceptionHandler) {
        this.typeHandler = typeHandler;
        this.key = key;
        this.intent = intent;
        this.type = type;
        this.optionalExceptionHandler = Optional.of(exceptionHandler);
    }

    public Class getTypeHandler() {
        return typeHandler;
    }

    public String getKey() {
        return key;
    }

    public IUserIntent getIntent() {
        return intent;
    }

    public Optional<IExceptionHandler> getOptionalExceptionHandler() {
        return optionalExceptionHandler;
    }

    public EIntentType getType() {
        return type;
    }
}
