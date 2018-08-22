
package ch.buhls.billmanager.model.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sdb
 */
public class TemplateData
{
    private String salutation, salutationName, title, prename, name, street, plz, city, number, location, template, date;
    private List<Position> positions;
    private int total;

    public TemplateData()
    {
    }
    
    public TemplateData(String salutation, String salutationName, String title, String prename, String name, String street, String plz, String city, String number, String location, String template, String date, List<Position> positions, int total)
    {
        this.salutation = salutation;
        this.salutationName = salutationName;
        this.title = title;
        this.prename = prename;
        this.name = name;
        this.street = street;
        this.plz = plz;
        this.city = city;
        this.number = number;
        this.location = location;
        this.template = template;
        this.date = date;
        this.positions = positions;
        this.total = total;
    }


    public String getSalutation()
    {
        return salutation;
    }

    public void setSalutation(String salutation)
    {
        this.salutation = salutation;
    }

    public String getSalutationName()
    {
        return salutationName;
    }

    public void setSalutationName(String salutationName)
    {
        this.salutationName = salutationName;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getPrename()
    {
        return prename;
    }

    public void setPrename(String prename)
    {
        this.prename = prename;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getPlz()
    {
        return plz;
    }

    public void setPlz(String plz)
    {
        this.plz = plz;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public List<Position> getPositions()
    {
        if(this.positions == null)
        {
            this.positions = new ArrayList<>();
        }
        
        return positions;
    }
    
    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTemplate()
    {
        return template;
    }

    public void setTemplate(String template)
    {
        this.template = template;
    }
    
}
