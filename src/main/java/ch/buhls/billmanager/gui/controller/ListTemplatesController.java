/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUITemplate;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.ListTemplatesBuilder;
import ch.buhls.billmanager.gui.view.listener.IListTemplatesListener;
import ch.buhls.billmanager.model.Project;
import javafx.collections.ObservableList;

/**
 *
 * @author simon
 */
public class ListTemplatesController extends AController implements IListTemplatesListener
{
    private final ListTemplatesBuilder builder;
    
    public ListTemplatesController(IGUIFramework framework, DataHandler dataHandler) {
        super(framework, dataHandler, GUIStringCollection.TEMPLATE);
        
        this.builder = new ListTemplatesBuilder(this, dataHandler.getTemplatesBuffer());
        
        framework.displayMask(builder.getView(), tabName, IGUIFramework.Area.LEFT).focus();
    }

    @Override
    public void create() {
        new CreateTemplateController(framework, dataHandler);
    }

    @Override
    public void edit(GUITemplate selected) {
        new EditTemplateController(framework, dataHandler, selected);
    }

    @Override
    public void show(GUITemplate selected) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void contextMenuOpened(ObservableList<GUITemplate> selected) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
