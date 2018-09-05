
package ch.buhls.billmanager.persistance.csvHandling.data;

/**
 *
 * @author sdb
 */
public class Position
{
    private int position, number;
    private Article item;

    public Position(int position, int number, Article item)
    {
        this.position = position;
        this.number = number;
        this.item = item;
    }

    public int getSubtotal()
    {
        return number*item.getPrice();
    }
    
    public int getPosition()
    {
        return position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public Article getItem()
    {
        return item;
    }

    public void setItem(Article item)
    {
        this.item = item;
    }
    
    
}
