package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.framework.IGUIFramework.Area;
import ch.buhls.billmanager.gui.framework.ITabHandle;
import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.view.builder.ArticleMaskBuilder;
import ch.buhls.billmanager.persistance.PersistanceException;
import ch.buhls.billmanager.gui.view.listener.IDefaultMaskListener;

/**
 *
 * @author simon
 */
public class CreateArticleController implements IDefaultMaskListener<GUIArticle>
{

    private final static String TAB_NAME = "Artikel erfassen";

    private final ITabHandle tabHandle;

    private final ArticleMaskBuilder builder;
    private final DataHandler dataHandler;
    private final IGUIFramework framework;

    public CreateArticleController(IGUIFramework framework, DataHandler dataHandler) {
        this.framework = framework;
        this.dataHandler = dataHandler;

        this.builder = new ArticleMaskBuilder(dataHandler.createArticle(), this);

        this.tabHandle = framework.displayMask(builder.getView(), TAB_NAME, Area.RIGHT);
    }

    @Override
    public void save(GUIArticle data) {
        try {
            if (framework.confirmToStore()) {
                this.dataHandler.storeArticle(data);
                tabHandle.close();
            }
        }
        catch (PersistanceException ex) {
            framework.showExceptionDialoque(ex);
        }

    }

}
