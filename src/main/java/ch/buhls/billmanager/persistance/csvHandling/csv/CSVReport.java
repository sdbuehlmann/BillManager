
package ch.buhls.billmanager.persistance.csvHandling.csv;

/**
 *
 * @author sdb
 */
public class CSVReport
{
    private String id, prename, name, street, plz, city, total, comment;

    public CSVReport(String id, String prename, String name, String street, String plz, String city, String total, String comment)
    {
        this.id = id;
        this.prename = prename;
        this.name = name;
        this.street = street;
        this.plz = plz;
        this.city = city;
        this.total = total;
        this.comment = comment;
    }

    public String getId()
    {
        return id;
    }

    public String getPrename()
    {
        return prename;
    }

    public String getName()
    {
        return name;
    }

    public String getStreet()
    {
        return street;
    }

    public String getPlz()
    {
        return plz;
    }

    public String getCity()
    {
        return city;
    }

    public String getTotal()
    {
        return total;
    }

    public String getComment()
    {
        return comment;
    }
    
    
}
