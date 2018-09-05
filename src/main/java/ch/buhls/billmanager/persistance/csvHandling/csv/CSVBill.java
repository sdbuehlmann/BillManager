
package ch.buhls.billmanager.persistance.csvHandling.csv;

import java.util.logging.Logger;

/**
 *
 * @author sdb
 */
public class CSVBill
{     
    public static final int NUMMER = 0;
    public static final int TEMPLATE = NUMMER + 1;
    public static final int PDF_ERSTELLEN = TEMPLATE + 1;
    public static final int ANREDE = PDF_ERSTELLEN + 1;
    public static final int BEGRUESSUNG = ANREDE + 1;
    public static final int BEGRUESSUNGSNAME = BEGRUESSUNG + 1;
    public static final int NAME = BEGRUESSUNGSNAME + 1;
    public static final int VORNAME = NAME + 1;
    public static final int STRASSE = VORNAME + 1;
    public static final int PLZ = STRASSE + 1;
    public static final int ORT = PLZ + 1;
    public static final int MENGE_ART1 = ORT + 1;
    public static final int ART1 = MENGE_ART1 + 1;
    public static final int MENGE_ART2 = ART1 + 1;
    public static final int ART2 = MENGE_ART2 + 1;
    public static final int MENGE_ART3 = ART2 + 1;
    public static final int ART3 = MENGE_ART3 + 1;
    public static final int MENGE_ART4 = ART3 + 1;
    public static final int ART4 = MENGE_ART4 + 1;
    
    private int  art1, art2, art3, art4, menge_art1, menge_art2, menge_art3, menge_art4;
    private String id, anrede, begruessung, prename, name, street, plz, city, template, salutationname;
    private boolean pdf_erstellen;

    public CSVBill(String id, 
            String anrede, 
            String begruessung, 
            String prename, 
            String name, 
            String salutationname, 
            String street, 
            String plz, 
            String city, 
            String template,
            boolean pdf_erstellen)
    {
        this.id = id;
        this.anrede = anrede;
        this.begruessung = begruessung;
        this.prename = prename;
        this.name = name;
        this.salutationname = salutationname;
        this.street = street;
        this.plz = plz;
        this.city = city;
        this.template = template;
        this.pdf_erstellen = pdf_erstellen;
        
        this.art1 = 0;
        this.art2 = 0;
        this.art3 = 0;
        this.art4 = 0;
        this.menge_art1 = 0;
        this.menge_art2 = 0;
        this.menge_art3 = 0;
        this.menge_art4 = 0;
    }
    
    

    public CSVBill(
            int art1, 
            int art2, 
            int art3, 
            int art4, 
            int menge_art1, 
            int menge_art2, 
            int menge_art3, 
            int menge_art4, 
            String id, 
            String anrede, 
            String begruessung, 
            String prename, 
            String name, 
            String salutationname, 
            String street, 
            String plz, 
            String city, 
            String template,
            boolean pdf_erstellen)
    {
        this.art1 = art1;
        this.art2 = art2;
        this.art3 = art3;
        this.art4 = art4;
        this.menge_art1 = menge_art1;
        this.menge_art2 = menge_art2;
        this.menge_art3 = menge_art3;
        this.menge_art4 = menge_art4;
        this.id = id;
        this.anrede = anrede;
        this.begruessung = begruessung;
        this.prename = prename;
        this.name = name;
        this.salutationname = salutationname;
        this.street = street;
        this.plz = plz;
        this.city = city;
        this.template = template;
        this.pdf_erstellen = pdf_erstellen;
    }

    public int getArt1()
    {
        return art1;
    }

    public void setArt1(int art1)
    {
        this.art1 = art1;
    }

    public int getArt2()
    {
        return art2;
    }

    public void setArt2(int art2)
    {
        this.art2 = art2;
    }

    public int getArt3()
    {
        return art3;
    }

    public void setArt3(int art3)
    {
        this.art3 = art3;
    }

    public int getArt4()
    {
        return art4;
    }

    public void setArt4(int art4)
    {
        this.art4 = art4;
    }

    public int getMenge_art1()
    {
        return menge_art1;
    }

    public void setMenge_art1(int menge_art1)
    {
        this.menge_art1 = menge_art1;
    }

    public int getMenge_art2()
    {
        return menge_art2;
    }

    public void setMenge_art2(int menge_art2)
    {
        this.menge_art2 = menge_art2;
    }

    public int getMenge_art3()
    {
        return menge_art3;
    }

    public void setMenge_art3(int menge_art3)
    {
        this.menge_art3 = menge_art3;
    }

    public int getMenge_art4()
    {
        return menge_art4;
    }

    public void setMenge_art4(int menge_art4)
    {
        this.menge_art4 = menge_art4;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getAnrede()
    {
        return anrede;
    }

    public void setAnrede(String anrede)
    {
        this.anrede = anrede;
    }

    public String getBegruessung()
    {
        return begruessung;
    }

    public void setBegruessung(String begruessung)
    {
        this.begruessung = begruessung;
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

    public String getTemplate()
    {
        return template;
    }

    public void setTemplate(String template)
    {
        this.template = template;
    }

    public String getSalutationname()
    {
        return salutationname;
    }

    public void setSalutationname(String salutationname)
    {
        this.salutationname = salutationname;
    }

    public boolean isPdf_erstellen()
    {
        return pdf_erstellen;
    }

    public void setPdf_erstellen(boolean pdf_erstellen)
    {
        this.pdf_erstellen = pdf_erstellen;
    }

    
}
