
package ch.buhls.billmanager.gui.view.listener;


import ch.buhls.billmanager.gui.data.GUIArticle;
import java.util.List;

/**
 *
 * @author simon
 */
public interface IListArticlesListener extends IContextMenuListener<GUIArticle>
{
    public void create();
    public void edit(List<GUIArticle> selected);
    public void mark(List<GUIArticle> selected);
    public void showVersions(GUIArticle selected);
}
