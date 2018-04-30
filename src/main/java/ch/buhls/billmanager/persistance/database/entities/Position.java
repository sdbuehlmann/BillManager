package ch.buhls.billmanager.persistance.database.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author sdb
 */
@Entity
public class Position extends AEntity<Position>
{

    // attributes
    private int number;
    private int position;

    // references
    @ManyToOne()
    private Article article;
    
    public Position() {
    }
    
    public Position(Position model) {
        model.copyData(this);
    }
    
    public int getNumber() {
        return number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public int getPosition() {
        return position;
    }
    
    public void setPosition(int position) {
        this.position = position;
    }
    
    public Article getArticle() {
        return article;
    }
    
    public void setArticle(Article article) {
        this.article = article;
    }
    
    @Override
    public void copyData(Position other) {
        other.article = article;
        other.number = number;
        other.position = position;
        
        super.copyData(other);        
    }
    
}
