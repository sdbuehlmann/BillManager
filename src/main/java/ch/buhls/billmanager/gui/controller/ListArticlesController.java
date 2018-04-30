package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.framework.ITabHandle;
import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.view.builder.ListArticlesBuilder;
import ch.buhls.billmanager.gui.view.listener.IListArticlesListener;
import ch.buhls.billmanager.gui.view.listener.IListVersionsListener;
import java.util.List;
import javafx.collections.ObservableList;


/**
 *
 * @author simon
 */
public class ListArticlesController implements IListArticlesListener
{
    private final static String TAB_NAME = "Artikel";

    private final ListArticlesBuilder builer;

    private final DataHandler dataHandler;
    private final IGUIFramework framework;
    private final ITabHandle maskContainer;

    public ListArticlesController(IGUIFramework containerManager, DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        this.framework = containerManager;

        this.builer = new ListArticlesBuilder(this, dataHandler.getArticlesBuffer());

        this.maskContainer = containerManager.displayMask(builer.getView(), TAB_NAME, IGUIFramework.Area.LEFT);
        this.maskContainer.focus();
    }

    @Override
    public void create() {
        new CreateArticleController(framework, dataHandler);
    }

    @Override
    public void contextMenuOpened(ObservableList<GUIArticle> selected) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(List<GUIArticle> selected) {
        new EditArticleController(selected.get(0), framework, dataHandler);
    }

    @Override
    public void mark(List<GUIArticle> selected) {
        if (selected.get(0) != null) {
            if (dataHandler.getMarkedArticle().get() != null) {
                // remove old mark
                dataHandler.getMarkedArticle().get().getMarked().set(false);
            };

            // mark new
            dataHandler.getMarkedArticle().set(selected.get(0));
            selected.get(0).getMarked().set(true);
        }
    }

    @Override
    public void showVersions(GUIArticle selected) {
        new ListVersionsController(framework, dataHandler.getVersions(selected), "Versionen anzeigen", new IListVersionsListener<GUIArticle>()
        {
            @Override
            public void show(GUIArticle data) {
                new ShowArticleController(data, framework);
            }
        });
    }
}
