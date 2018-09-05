package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.framework.ITabHandle;
import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.framework.IHintHandle;
import ch.buhls.billmanager.gui.view.builder.ListArticlesBuilder;
import ch.buhls.billmanager.gui.view.container.HintContainer;
import ch.buhls.billmanager.gui.view.listener.IListArticlesListener;
import ch.buhls.billmanager.gui.view.listener.IListVersionsListener;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

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
    private IHintHandle artMarkedHintHandle;

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
            // remove old mark
            if (dataHandler.getMarkedArticleProperty().get() != null) {
                dataHandler.getMarkedArticleProperty().get().getMarked().set(false);
            }

            // remove old hint
            if (artMarkedHintHandle != null) {
                artMarkedHintHandle.close();
                artMarkedHintHandle = null;
            }

            // mark new
            dataHandler.getMarkedArticleProperty().set(selected.get(0));
            selected.get(0).getMarked().set(true);

            artMarkedHintHandle = framework.displayHint(new HintContainer(new Label(GUIStringCollection.getHintTxt_artMarked(selected.get(0))), () -> {
                // close hint
                dataHandler.getMarkedArticleProperty().get().getMarked().set(false);
                dataHandler.getMarkedArticleProperty().set(null);
                artMarkedHintHandle.close();
                artMarkedHintHandle = null;
            }));
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
