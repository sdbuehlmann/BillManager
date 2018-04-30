
package ch.buhls.billmanager.gui.view.container;

import ch.buhls.billmanager.gui.view.elements.NumberField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author simon
 */
public class FilterPersonsFormContainer
{
    private final GridPane view;
    private final CheckBox cbBirthdayLaterThan, cbBirthdayEarlierThan, cbTeammember, cbOlderThan, cbYoungerThan;
    private final TextField tfTeammember;
    private final NumberField nfOlderThan, nfYoungerThan;
    private final DatePicker dpBirthdayLaterThan, dpBirthdayEarlierThan, dpOlderThan, dpYoungerThan;
    private final Label lOlderThan, lYoungerThan;
    
    public FilterPersonsFormContainer()
    {
        view = new GridPane();
        
        cbBirthdayLaterThan = new CheckBox("Geburtstag nach");
        cbBirthdayEarlierThan = new CheckBox("Geburtstag vor");
        cbTeammember = new CheckBox("Teammitglied von");
        cbYoungerThan = new CheckBox("Jünger als");
        cbOlderThan = new CheckBox("Älter/Gleich als");
        
        lOlderThan = new Label(" am ");
        lYoungerThan = new Label(" am ");
        
        tfTeammember = new TextField();
        
        nfOlderThan = new NumberField();
        nfYoungerThan = new NumberField();
        
        dpBirthdayLaterThan = new DatePicker();
        dpBirthdayEarlierThan = new DatePicker();
        dpOlderThan = new DatePicker();
        dpYoungerThan = new DatePicker();
        
        view.add(cbBirthdayLaterThan, 0, 0);
        view.add(cbBirthdayEarlierThan, 0, 1);
        view.add(cbTeammember, 0, 2);
        view.add(cbOlderThan, 0, 3);
        view.add(cbYoungerThan, 0, 4);
        
        view.add(dpBirthdayLaterThan, 1, 0);
        view.add(dpBirthdayEarlierThan, 1, 1);
        view.add(tfTeammember, 1, 2);
        view.add(nfOlderThan, 1, 3);
        view.add(nfYoungerThan, 1, 4);
        
        //view.add(null, 2, 0);
        //view.add(null, 2, 1);
        //view.add(null, 2, 2);
        view.add(lOlderThan, 2, 3);
        view.add(lYoungerThan, 2, 4);
        
        //view.add(null, 3, 0);
        //view.add(null, 3, 1);
        //view.add(null, 3, 2);
        view.add(dpOlderThan, 3, 3);
        view.add(dpYoungerThan, 3, 4);
    }

    public CheckBox getCbBirthdayLaterThan()
    {
        return cbBirthdayLaterThan;
    }

    public CheckBox getCbBirthdayEarlierThan()
    {
        return cbBirthdayEarlierThan;
    }

    public CheckBox getCbTeammember()
    {
        return cbTeammember;
    }

    public TextField getTfTeammember()
    {
        return tfTeammember;
    }

    public DatePicker getDpBirthdayLaterThan()
    {
        return dpBirthdayLaterThan;
    }

    public DatePicker getDpBirthdayEarlierThan()
    {
        return dpBirthdayEarlierThan;
    }

    public CheckBox getCbOlderThan()
    {
        return cbOlderThan;
    }

    public CheckBox getCbYoungerThan()
    {
        return cbYoungerThan;
    }

    public NumberField getNfOlderThan()
    {
        return nfOlderThan;
    }

    public NumberField getNfYoungerThan()
    {
        return nfYoungerThan;
    }

    public DatePicker getDpOlderThan()
    {
        return dpOlderThan;
    }

    public DatePicker getDpYoungerThan()
    {
        return dpYoungerThan;
    }
    
    

    public GridPane getView()
    {
        return view;
    }
}
