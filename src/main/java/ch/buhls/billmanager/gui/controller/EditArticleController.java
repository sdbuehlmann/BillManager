
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.viewModel.DataHandler;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.view.builder.ArticleMaskBuilder;
import ch.buhls.billmanager.persistance.PersistanceException;
import ch.buhls.billmanager.gui.view.builder.listener.IDefaultMaskListener;


/**
 *
 * @author simon
 */
public class EditArticleController extends AFormController<GUIArticle> implements IDefaultMaskListener<GUIArticle>
{
    private final GUIArticle article;
    
    private final ArticleMaskBuilder builder;
    
    public EditArticleController(GUIArticle origArticle, IGUIFramework framework, DataHandler dataHandler)
    {
        super(framework, dataHandler, framework.getStringCollections().getArticlesStringCollection());

        this.article = dataHandler.editArticle(origArticle);
        
        builder = new ArticleMaskBuilder(this.article, this);
        builder.changeFieldsToSwitchableMode();
        
        displayEditMask(builder.getView(), article);
    }
    
    // private methods
    @Override
    public void save(GUIArticle data)
    {
        try {
            if(displayConfirmToStoreDialoque(data)){
                dataHandler.storeArticle(data);
                tabHandle.close();
                displayEditedInfoHint(data);
            }
        }
        catch (PersistanceException ex) {
            framework.showExceptionDialoque(ex);
        }
    }
}
