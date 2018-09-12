
package ch.buhls.billmanager.gui.framework;

import ch.buhls.billmanager.gui.data.GUIArticle;

/**
 *
 * @author simon
 */
public class DEArticleStringCollection implements IStringCollection<GUIArticle>
{

    @Override
    public String getTabTitle_Create() {
        return "Artikel erfassen";
    }

    @Override
    public String getTabTitle_Edit(GUIArticle art) {
        return String.format("Artikel \"%s\" bearbeiten", art.getTitle().get());
    }

    @Override
    public String getTabTitle_Show(GUIArticle art) {
        return String.format("Artikel \"%s\" anzeigen", art.getTitle().get());
    }

    @Override
    public String getHintTxt_Created(GUIArticle art) {
         return String.format("Artikel \"%s; %s\" erfolgreich erfasst", art.getTitle().get(), art.getDescription().get());
    }

    @Override
    public String getHintTxt_Edited(GUIArticle art) {
        return String.format("Artikel \"%s; %s\" erfolgreich bearbeitet", art.getTitle().get(), art.getDescription().get());
    }

    @Override
    public String getConfirmTxt_Save(GUIArticle art) {
        return String.format("Artikel \"%s; %s\"", art.getTitle().get(), art.getDescription().get());
    }
    
}
