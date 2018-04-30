
package ch.buhls.billmanager.persistance.database.services;

import ch.buhls.billmanager.persistance.database.ContainerFactory;
import ch.buhls.billmanager.persistance.database.entities.Article;



/**
 *
 * @author simon
 */
public class ArticleService extends AService<Article>
{

    public ArticleService(ContainerFactory factory)
    {
        super(factory, factory.getArticleContainer());
    }

    @Override
    public void remove(Article entity) throws ServiceException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void updateManagedEntity(Article managedEntity, Article entity)
    {
        managedEntity.setTitle(entity.getTitle());
        managedEntity.setDescription(entity.getDescription());
        managedEntity.setCosts(entity.getCosts());
        managedEntity.setInternalCategorie(entity.getInternalCategorie());
    }
}
