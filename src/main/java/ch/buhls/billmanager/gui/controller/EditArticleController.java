
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.framework.IGUIFramework.Area;
import ch.buhls.billmanager.gui.framework.ITabHandle;
import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.view.builder.ArticleMaskBuilder;
import ch.buhls.billmanager.persistance.PersistanceException;
import ch.buhls.billmanager.gui.view.builder.listener.IDefaultMaskListener;


/**
 *
 * @author simon
 */
public class EditArticleController implements IDefaultMaskListener<GUIArticle>
{
    private final GUIArticle article;
    
    private final ITabHandle tabHandle;
    private final ArticleMaskBuilder builder;
    
    private final DataHandler dataHandler;
    
    private final IGUIFramework framework;
    
    public EditArticleController(GUIArticle origArticle, IGUIFramework framework, DataHandler dataHandler)
    {
        this.framework = framework;
        this.dataHandler = dataHandler;

        // duplicate all persons
        this.article = dataHandler.editArticle(origArticle);
        
        this.builder = new ArticleMaskBuilder(this.article, this);
        this.builder.changeFieldsToSwitchableMode();
        
        this.tabHandle = framework.displayMask(builder.getView(), "Bearbeite Mitglieder", Area.RIGHT);
    }
    
    // private methods
    @Override
    public void save(GUIArticle data)
    {
        try {
            if(framework.confirmToStore()){
                dataHandler.storeArticle(data);
                tabHandle.close();
            }
        }
        catch (PersistanceException ex) {
            framework.showExceptionDialoque(ex);
        }
    }
}
