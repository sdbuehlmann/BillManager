package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.framework.IHintHandle;
import ch.buhls.billmanager.gui.view.builder.ListArticlesBuilder;
import ch.buhls.billmanager.gui.view.listener.IListArticlesListener;
import ch.buhls.billmanager.gui.view.listener.IListVersionsListener;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

/**
 *
 * @author simon
 */
public class ListArticlesController extends AController implements IListArticlesListener
{
    private final ListArticlesBuilder builder;

    private IHintHandle artMarkedHintHandle;

    public ListArticlesController(IGUIFramework framework, DataHandler dataHandler) {
        super(framework, dataHandler, GUIStringCollection.getTabTitle_listArticles());

        builder = new ListArticlesBuilder(this, dataHandler.getArticlesBuffer());
        builder.enableDBInfos(dataHandler.getShowDBInfosProperty().get());
        dataHandler.getShowDBInfosProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            builder.enableDBInfos(newValue);
        });
        
        display(builder.getView(), IGUIFramework.Area.LEFT);
    }

    @Override
    public void create() {
        new CreateArticleController(framework, dataHandler);
    }

    @Override
    public void contextMenuOpened(ObservableList<GUIArticle> selected) {
        // ignore
    }

    @Override
    public void edit(List<GUIArticle> selected) {
        new EditArticleController(selected.get(0), framework, dataHandler);
    }

    @Override
    public void mark(List<GUIArticle> selected) {
        if (selected.get(0) != null) {
            // remove old hint
            if (artMarkedHintHandle != null) {
                artMarkedHintHandle.close();
                artMarkedHintHandle = null;
            }

            // mark new
            dataHandler.getMarkedArticleProperty().set(selected.get(0));

            artMarkedHintHandle = framework.displayMarkedHint(GUIStringCollection.getHintTxt_artMarked(selected.get(0)), () -> {
                // close hint
                dataHandler.getMarkedRole().set(null);
                artMarkedHintHandle.close();
                artMarkedHintHandle = null;
            });
        }
    }

    @Override
    public void showVersions(GUIArticle selected) {
        new ListVersionsController(
                framework, 
                dataHandler, 
                "Versionen anzeigen",
                dataHandler.getVersions(selected),
                new IListVersionsListener<GUIArticle>()
        {
            @Override
            public void show(GUIArticle data) {
                new ShowArticleController(data, framework);
            }
        });
    }
}
