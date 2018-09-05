
package ch.buhls.billmanager.persistance.csvHandling.data;

/**
 *
 * @author sdb
 */
public class Article
{
    private String firstLine, secondLine;
    private int id, price;

    public Article(int id, String firstLine, String secondLine, int price)
    {
        this.id = id;
        this.firstLine = firstLine;
        this.secondLine = secondLine;
        this.price = price;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstLine()
    {
        return firstLine;
    }

    public void setFirstLine(String firstLine)
    {
        this.firstLine = firstLine;
    }

    public String getSecondLine()
    {
        return secondLine;
    }

    public void setSecondLine(String secondLine)
    {
        this.secondLine = secondLine;
    }
    
    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }
    
    
}
