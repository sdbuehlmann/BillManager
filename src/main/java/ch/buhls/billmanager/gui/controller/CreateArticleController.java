package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.view.builder.ArticleMaskBuilder;
import ch.buhls.billmanager.persistance.PersistanceException;
import ch.buhls.billmanager.gui.view.builder.listener.IDefaultMaskListener;

/**
 *
 * @author simon
 */
public class CreateArticleController extends AFormController implements IDefaultMaskListener<GUIArticle>
{
    private final ArticleMaskBuilder builder;

    public CreateArticleController(IGUIFramework framework, DataHandler dataHandler) {
        super(framework, dataHandler, framework.getStringCollections().getArticlesStringCollection());
        
        builder = new ArticleMaskBuilder(dataHandler.createArticle(), this);
        displayCreateMask(builder.getView());
    }

    @Override
    public void save(GUIArticle data) {
        try {
            if (displayConfirmToStoreDialoque(data)) {
                dataHandler.storeArticle(data);
                tabHandle.close();
                displayCreatedInfoHint(data);
            }
        }
        catch (PersistanceException ex) {
            framework.showExceptionDialoque(ex);
        }
    }

}
