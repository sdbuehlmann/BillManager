package ch.buhls.billmanager.framework.jfxRenderer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;

public class ButtonBarContainer {

    private final ButtonBar bar;

    public ButtonBarContainer() {
        bar = new ButtonBar();
        bar.setPadding(new Insets(10));
    }

    public void addButton(String text, EventHandler<ActionEvent> eventHandler)
    {
        Button button = new Button(text);
        button.setOnAction(eventHandler);
        bar.getButtons().add(button);
    }

    public ButtonBar getBar() {
        return bar;
    }
}
