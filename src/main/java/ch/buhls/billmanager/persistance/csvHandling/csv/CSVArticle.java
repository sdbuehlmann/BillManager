
package ch.buhls.billmanager.persistance.csvHandling.csv;

/**
 *
 * @author sdb
 */
public class CSVArticle
{
    // ID;line1;line2;line3;Kosten
    public static final int ID = 0;
    public static final int LINE1 = 1;
    public static final int LINE2 = 2;
    public static final int PRIZE = 3;
    public static final int INTERNAL_CATEGORIE = 4;
    
    private int id, prize;
    private String line1, line2, internalCategorie;

    public CSVArticle(int id, String line1, String line2, int prize, String internalCategorie)
    {
        this.id = id;
        this.prize = prize;
        this.line1 = line1;
        this.line2 = line2;
        this.internalCategorie = internalCategorie;
    }

    public int getId()
    {
        return id;
    }

    public int getPrize()
    {
        return prize;
    }

    public String getLine1()
    {
        return line1;
    }

    public String getLine2()
    {
        return line2;
    }

    public String getInternalCategorie()
    {
        return internalCategorie;
    }

    
}
