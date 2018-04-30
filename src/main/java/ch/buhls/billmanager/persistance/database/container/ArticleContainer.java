package ch.buhls.billmanager.persistance.database.container;

import ch.buhls.billmanager.persistance.database.entities.Article;

import javax.persistence.EntityManager;

/**
 *
 * @author simon
 */
public class ArticleContainer extends AContainer<Article>
{

    public ArticleContainer(EntityManager em) {
        super(Article.class, "Article", em);
    }

    /*public List<Article> findAllHighestVersions() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = em.getCriteriaBuilder().createQuery();
        Root<Article> c = criteriaQuery.from(Article.class);
        criteriaQuery.select(criteriaBuilder.max(c.get("versionnr")));

        TypedQuery<Article> query = this.em.createQuery("select x from " + this.tableName + " x", this.typeParameterClass);
        List<Article> temp = query.getResultList();
        return temp;
    }*/

}
