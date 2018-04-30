
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.framework.IGUIFramework.Area;
import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.view.builder.ArticleMaskBuilder;
import ch.buhls.billmanager.gui.view.listener.IDefaultMaskListener;


/**
 *
 * @author simon
 */
public class ShowArticleController implements IDefaultMaskListener<GUIArticle>
{
    private final GUIArticle origArticle;
    private final ArticleMaskBuilder builder;
    
    public ShowArticleController(GUIArticle origArticle, IGUIFramework framework)
    {
        this.origArticle = origArticle;
        
        this.builder = new ArticleMaskBuilder(this.origArticle, this);
        this.builder.changeFieldsToReadOnlyMode();
        
        framework.displayMask(builder.getView(), "Artikel anzeigen", Area.RIGHT);
    }
    
    // private methods
    @Override
    public void save(GUIArticle data)
    {
        throw new UnsupportedOperationException();
    }
}
