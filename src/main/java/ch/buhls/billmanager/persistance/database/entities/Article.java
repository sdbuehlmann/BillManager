
package ch.buhls.billmanager.persistance.database.entities;

import javax.persistence.Entity;

/**
 *
 * @author simon
 */
@Entity
public class Article extends ATrackedEntity<Article>
{
    private String title, description, internalCategorie;
    private int costs;

    public Article()
    {
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getCosts()
    {
        return costs;
    }

    public void setCosts(int costs)
    {
        this.costs = costs;
    }

    public String getInternalCategorie()
    {
        return internalCategorie;
    }

    public void setInternalCategorie(String internalCategorie)
    {
        this.internalCategorie = internalCategorie;
    }
    
    public Article duplicate(){
        Article temp = new Article();
        temp.id = id;
        temp.version = version;
        temp.changeTxt = changeTxt;
        temp.previousVersion = previousVersion;
        temp.followingVersion = followingVersion;
        
        temp.title = title;
        temp.description = description;
        temp.internalCategorie = internalCategorie;
        temp.costs = costs;
        
        return temp;
    }
}
