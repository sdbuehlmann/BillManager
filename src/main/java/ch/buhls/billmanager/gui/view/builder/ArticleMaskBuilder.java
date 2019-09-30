
package ch.buhls.billmanager.gui.view.builder;

import ch.buhls.billmanager.framework.jfxRenderer.FormRenderer;
import ch.buhls.billmanager.framework.propertyDescription.PropertiesView;
import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.view.container.form.ArticleFormContainer;
import ch.buhls.billmanager.gui.view.elements.LabledSwitchableControlContainer;
import javafx.scene.layout.VBox;
import ch.buhls.billmanager.gui.view.builder.listener.IDefaultMaskListener;
import ch.buhls.billmanager.persistance.database.entities.Article;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 *
 * @author simon
 */
public class ArticleMaskBuilder
{
    private static PropertiesView<Article> propView;
    private final GridPane view;
    
    // data
    private final GUIArticle article;
    
    // view
    private final ArticleFormContainer formContainer;
    
    // listener
    private final IDefaultMaskListener listener;
    
    public ArticleMaskBuilder(GUIArticle article, IDefaultMaskListener listener) {
        this.listener = listener;
        this.article = article;
        
        this.formContainer = new ArticleFormContainer();
        
        if(propView == null)
        {
            propView = getPropertiesView();
        }
        view = FormRenderer.render(propView, article.getData());
        
        bindProperties();
        bindListeners();
    }
    
    private void bindProperties()
    {
        LabledSwitchableControlContainer.bindNumberfield(formContainer.getNfIdDBContainer(), article.getDb_id(), true);
        LabledSwitchableControlContainer.bindNumberfield(formContainer.getNfVersionDBContainer(), article.getDb_version(), true);
        
        LabledSwitchableControlContainer.bindNumberfield(formContainer.getNfVersionContainer(), article.getVersionNr(), true);
        LabledSwitchableControlContainer.bindTextfield(formContainer.getTfChangeTxtContainer(), article.getChangeTxt(), true);
        
        LabledSwitchableControlContainer.bindTextfield(formContainer.getTfDescription1Container(), article.getTitle(), false);
        LabledSwitchableControlContainer.bindTextfield(formContainer.getTfDescription2Container(), article.getDescription(), false);
        LabledSwitchableControlContainer.bindTextfield(formContainer.getTfCategorieContainer(), article.getInternalCategorie(), false);
        LabledSwitchableControlContainer.bindNumberfield(formContainer.getNfPrizeContainer(), article.getCosts(), false);
    }
    
    private void bindListeners()
    {
        formContainer.getbSave().setOnAction((event) ->
        {
            this.listener.save(article);
        });
    }
    
    // methods to enable/disable functions from the view
    public void changeFieldsToSwitchableMode(){
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfIdDBContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionDBContainer());
        
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionContainer());
        LabledSwitchableControlContainer.changeToSwitchableState(formContainer.getTfChangeTxtContainer());
        
        LabledSwitchableControlContainer.changeToSwitchableState(formContainer.getTfDescription1Container());
        LabledSwitchableControlContainer.changeToSwitchableState(formContainer.getTfDescription2Container());
        LabledSwitchableControlContainer.changeToSwitchableState(formContainer.getTfCategorieContainer());
        LabledSwitchableControlContainer.changeToSwitchableState(formContainer.getNfPrizeContainer());
    }
    
    public void changeFieldsToReadOnlyMode(){
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfIdDBContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionDBContainer());
        
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getTfChangeTxtContainer());
        
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getTfDescription1Container());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getTfDescription2Container());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getTfCategorieContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfPrizeContainer());
    }
    
    public void changeFieldsToEidtableMode(){
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfIdDBContainer());
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionDBContainer());
        
        LabledSwitchableControlContainer.changeToReadOnlyState(formContainer.getNfVersionContainer());
        LabledSwitchableControlContainer.changeToEditableState(formContainer.getTfChangeTxtContainer());
        
        LabledSwitchableControlContainer.changeToEditableState(formContainer.getTfDescription1Container());
        LabledSwitchableControlContainer.changeToEditableState(formContainer.getTfDescription2Container());
        LabledSwitchableControlContainer.changeToEditableState(formContainer.getTfCategorieContainer());
        LabledSwitchableControlContainer.changeToEditableState(formContainer.getNfPrizeContainer());
    }
    
    private PropertiesView getPropertiesView(){
        PropertiesView<Article> desc = new PropertiesView<>("article", Article.class);
        
        desc.addCategory("textes");
        desc.addProperty("title", String.class, (owner) -> { return owner.getTitle(); }, (owner,value) -> { owner.setTitle(value); });
        desc.addProperty("description", String.class, (owner) -> { return owner.getDescription(); }, (owner,value) -> { owner.setDescription(value); });
        
        desc.addCategory("internal");
        desc.addProperty("category", String.class, (owner) -> { return owner.getInternalCategorie(); }, (owner,value) -> { owner.setInternalCategorie(value); });
        desc.addProperty("changeText", String.class, (owner) -> { return owner.getChangeTxt(); }, (owner,value) -> { owner.setChangeTxt(value); });
        
        return desc;
    }
    
    // getter & setter
    public Node getView() {
        //return view;
        return formContainer.getView();
    }
}
