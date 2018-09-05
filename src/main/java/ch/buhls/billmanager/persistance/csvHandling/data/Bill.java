
package ch.buhls.billmanager.persistance.csvHandling.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sdb
 */
public class Bill
{
    private String salutation;
    private String salutationName;
    private Address address;
    private List<Position> positions;
    private String number;
    private String template;
    private boolean generatePDF;

    public Bill(String salutation, String salutationName, Address address, String number, String template, boolean generatePDF)
    {
        this.salutation = salutation;
        this.salutationName = salutationName;
        this.address = address;
        this.number = number;
        this.template = template;
        this.generatePDF = generatePDF;
    }

    public int getTotal()
    {
        int total = 0;
        
        for(Position pos : positions)
        {
            int subsum = pos.getNumber() * pos.getItem().getPrice();
            total = total + subsum;
        } 
        return total;        
    }
    
    public String getSalutation()
    {
        return salutation;
    }

    public void setSalutation(String salutation)
    {
        this.salutation = salutation;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public List<Position> getPositions()
    {
        if(positions == null)
        {
            positions = new ArrayList<>();
        }
        return positions;
    }

    public void setPositions(List<Position> positions)
    {
        this.positions = positions;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getTemplate()
    {
        return template;
    }

    public void setTemplate(String template)
    {
        this.template = template;
    }

    public String getSalutationName()
    {
        return salutationName;
    }

    public void setSalutationName(String salutationName)
    {
        this.salutationName = salutationName;
    }

    public boolean isGeneratePDF()
    {
        return generatePDF;
    }

    public void setGeneratePDF(boolean generatePDF)
    {
        this.generatePDF = generatePDF;
    }

}
