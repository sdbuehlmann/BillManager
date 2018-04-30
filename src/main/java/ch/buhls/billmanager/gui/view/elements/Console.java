package ch.buhls.billmanager.gui.view.elements;

import java.io.IOException;
import java.io.OutputStream;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

/**
 *
 * @author sdb
 */
public class Console extends OutputStream
{

    private TextArea output;

    public Console(TextArea ta)
    {
        this.output = ta;
    }

    @Override
    public void write(int i) throws IOException
    {
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                output.appendText(String.valueOf((char) i));
            }
        });
    }
}
