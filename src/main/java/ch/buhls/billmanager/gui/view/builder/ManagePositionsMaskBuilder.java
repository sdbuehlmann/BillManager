
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIPosition;
import ch.buhls.billmanager.gui.view.container.form.ManagePositionsFormContainer;
import ch.buhls.billmanager.gui.view.container.table.PositionsTableContainer;
import ch.buhls.billmanager.gui.view.elements.LabledSwitchableControlContainer;
import ch.buhls.billmanager.gui.view.listener.IManagePositionsListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author simon
 */
public class ManagePositionsMaskBuilder
{
    private final VBox view;
    
    private final ToolBar mainBar, managePositionsBar, safePositionsBar;
    private final Button bSave, bRemovePosition, bAddMarkedArticle, bEditPosition;
    private final Button bSaveEditPosition, bAbortEditPosition;

    private final ManagePositionsFormContainer formContainer;
    private final PositionsTableContainer tableContainer;
    
    private final IManagePositionsListener listener;
    
    private GUIPosition editedPos;

    public ManagePositionsMaskBuilder(IManagePositionsListener listener, ObservableList<GUIPosition> positions, GUIPosition editedPos) {
        this.listener = listener;
        this.editedPos = editedPos;
        
        bSave = new Button(GUIStringCollection.SAVE);
        bRemovePosition = new Button(GUIStringCollection.POSITION_REMOVE_POSITION);
        bAddMarkedArticle = new Button(GUIStringCollection.PERSON_ADD_ART_TO_BILL);
        bEditPosition = new Button(GUIStringCollection.POSITION_EDIT_POSITION);
        bSaveEditPosition = new Button(GUIStringCollection.POSITION_SAVE_POSITION);
        bAbortEditPosition = new Button(GUIStringCollection.ABORT);
        
        mainBar = new ToolBar();
        mainBar.setPadding(new Insets(10));
        mainBar.getItems().addAll(bSave, bAddMarkedArticle);
        
        managePositionsBar = new ToolBar();
        managePositionsBar.setPadding(new Insets(10));
        managePositionsBar.getItems().addAll(bEditPosition,bRemovePosition);
        
        formContainer = new ManagePositionsFormContainer();
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getTfArtTitleContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getTfArtDescContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfArtPrizeContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getTfArtCategorieContainer());
        
        safePositionsBar = new ToolBar();
        safePositionsBar.setPadding(new Insets(10));
        safePositionsBar.getItems().addAll(bSaveEditPosition,bAbortEditPosition);
        
        tableContainer = new PositionsTableContainer();
        tableContainer.getTable().setItems(positions);
        
        view = new VBox(mainBar,tableContainer.getTable(),new Separator(),managePositionsBar,formContainer.getView(), safePositionsBar);
        view.setVgrow(tableContainer.getTable(), Priority.ALWAYS);
        
        connectListener();
        enableToAddMarkedPos(false);
        enableToChangePosProperties(false);
        enableToEditOrRemovePos(false);
    }

    private void connectListener(){
        bSave.setOnAction((ActionEvent event) -> {
            listener.save();
        });
        
        bSaveEditPosition.setOnAction((ActionEvent event) -> {
            listener.savePos(editedPos);
        });
        
        bRemovePosition.setOnAction((ActionEvent event) -> {
            listener.removePos(tableContainer.getTable().getSelectionModel().getSelectedItem());
        });
        
        bEditPosition.setOnAction((ActionEvent event) -> {
            listener.editPos(tableContainer.getTable().getSelectionModel().getSelectedItem());
        });
        
        bAddMarkedArticle.setOnAction((ActionEvent event) -> {
            listener.addMarkedArticle();
        });
        
        tableContainer.getTable().getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends GUIPosition> c) -> {
            listener.selectionChanged(tableContainer.getTable().getSelectionModel().getSelectedItem());
        });
        
        bAbortEditPosition.setOnAction((ActionEvent event) -> {
            listener.abortEditPos();
        });
    }
    
    private void bindProperties(){
        LabledSwitchableControlContainer.bindNf(formContainer.getNfAmountContainer(), editedPos.getAmount());
        LabledSwitchableControlContainer.bindNf(formContainer.getNfArtPrizeContainer(), editedPos.getGuiArticle().getCosts());
        LabledSwitchableControlContainer.bindNf(formContainer.getNfPositionContainer(), editedPos.getPosition());
        
        LabledSwitchableControlContainer.bindTf(formContainer.getTfArtCategorieContainer(), editedPos.getGuiArticle().getInternalCategorie());
        LabledSwitchableControlContainer.bindTf(formContainer.getTfArtDescContainer(), editedPos.getGuiArticle().getDescription());
        LabledSwitchableControlContainer.bindTf(formContainer.getTfArtTitleContainer(), editedPos.getGuiArticle().getTitle());
    }
    private void unbindProperties(){
        LabledSwitchableControlContainer.unbindNf(formContainer.getNfAmountContainer(), editedPos.getAmount());
        LabledSwitchableControlContainer.unbindNf(formContainer.getNfArtPrizeContainer(), editedPos.getGuiArticle().getCosts());
        LabledSwitchableControlContainer.unbindNf(formContainer.getNfPositionContainer(), editedPos.getPosition());
        
        LabledSwitchableControlContainer.unbindTf(formContainer.getTfArtCategorieContainer(), editedPos.getGuiArticle().getInternalCategorie());
        LabledSwitchableControlContainer.unbindTf(formContainer.getTfArtDescContainer(), editedPos.getGuiArticle().getDescription());
        LabledSwitchableControlContainer.unbindTf(formContainer.getTfArtTitleContainer(), editedPos.getGuiArticle().getTitle());
    }
    
    
    public final void enableToAddMarkedPos(boolean enable){
        bAddMarkedArticle.setDisable(!enable);
    }
    
    public final void enableToEditOrRemovePos(boolean enable){
        bEditPosition.setDisable(!enable);
        bRemovePosition.setDisable(!enable);
    }
    
    public final void enableToChangePosProperties(boolean enable){
        if(enable){
            LabledSwitchableControlContainer.changeToEditableState(formContainer.getNfAmountContainer());
            LabledSwitchableControlContainer.changeToEditableState(formContainer.getNfPositionContainer());
        }else{
            LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfAmountContainer());
            LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfPositionContainer());
        }
        
        bSaveEditPosition.setDisable(!enable);
        bAbortEditPosition.setDisable(!enable);
    }

    public GUIPosition getEditedPos() {
        return editedPos;
    }

    public void setEditedPos(GUIPosition editedPos) {
        unbindProperties();
        this.editedPos = editedPos;
        bindProperties();
    }
    
    
    
    public VBox getView() {
        return view;
    }
}
