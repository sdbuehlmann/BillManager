package ch.buhls.billmanager.gui.view.container.form;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.view.elements.LabledSwitchableControlContainer;
import ch.buhls.billmanager.gui.view.elements.NumberField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class ManagePositionsFormContainer
{

    private final VBox view;

    private final LabledSwitchableControlContainer<TextField> tfArtTitleContainer;
    private final LabledSwitchableControlContainer<TextField> tfArtDescContainer;
    private final LabledSwitchableControlContainer<NumberField> nfArtPrizeContainer;
    private final LabledSwitchableControlContainer<TextField> tfArtCategorieContainer;

    private final LabledSwitchableControlContainer<NumberField> nfPositionContainer;
    private final LabledSwitchableControlContainer<NumberField> nfAmountContainer;

    public ManagePositionsFormContainer() {
        tfArtTitleContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.ART_TITLE,
                GUIStringCollection.EDIT,
                new TextField());
        tfArtDescContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.ART_DESCRIPTION,
                GUIStringCollection.EDIT,
                new TextField());
        nfArtPrizeContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.ART_PRICE,
                GUIStringCollection.EDIT,
                new NumberField());
        tfArtCategorieContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.ART_CATEGORIE,
                GUIStringCollection.EDIT,
                new TextField());
        nfAmountContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.POSITION_AMOUNT,
                GUIStringCollection.EDIT,
                new NumberField());
        nfPositionContainer = new LabledSwitchableControlContainer<>(
                GUIStringCollection.POSITION_POS,
                GUIStringCollection.EDIT,
                new NumberField());

        view = new VBox(
                tfArtTitleContainer.getView(),
                tfArtDescContainer.getView(),
                nfArtPrizeContainer.getView(),
                tfArtCategorieContainer.getView(),
                nfAmountContainer.getView(),
                nfPositionContainer.getView());
    }

    public VBox getView() {
        return view;
    }

    public LabledSwitchableControlContainer<TextField> getTfArtTitleContainer() {
        return tfArtTitleContainer;
    }

    public LabledSwitchableControlContainer<TextField> getTfArtDescContainer() {
        return tfArtDescContainer;
    }

    public LabledSwitchableControlContainer<NumberField> getNfArtPrizeContainer() {
        return nfArtPrizeContainer;
    }

    public LabledSwitchableControlContainer<TextField> getTfArtCategorieContainer() {
        return tfArtCategorieContainer;
    }

    public LabledSwitchableControlContainer<NumberField> getNfPositionContainer() {
        return nfPositionContainer;
    }

    public LabledSwitchableControlContainer<NumberField> getNfAmountContainer() {
        return nfAmountContainer;
    }

    
}
