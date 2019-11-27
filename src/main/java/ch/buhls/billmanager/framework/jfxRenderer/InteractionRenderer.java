package ch.buhls.billmanager.framework.jfxRenderer;

import ch.buhls.billmanager.framework.eventsDescription.IntentDescriptor;
import ch.buhls.billmanager.framework.eventsDescription.InteractionDescriptor;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import javafx.scene.control.ButtonBar;

public class InteractionRenderer {
    public static <THandler> ButtonBar render(InteractionDescriptor<THandler> interactions, THandler handler, IGUIFramework framework)
    {
        ButtonBarContainer barContainer = new ButtonBarContainer();

        for (IntentDescriptor intentDescriptor : interactions.getIntents()) {
            switch (intentDescriptor.getType()){
                case NORMAL:
                    barContainer.addButton(intentDescriptor.getKey(), event -> intentDescriptor.getIntent().DoOnHandler(handler));
                    break;

                case CONFIRMATION_EXPECTED:
                    barContainer.addButton(intentDescriptor.getKey(), event -> {
                        if (framework.showConfirmToStoreDialoque("Möchtest Du wirklich speichern?")) {
                            intentDescriptor.getIntent().DoOnHandler(handler);
                        }
                    });
                    break;

                case ASYNC:
                    break;
            }
        }

        return barContainer.getBar();
    }
}
