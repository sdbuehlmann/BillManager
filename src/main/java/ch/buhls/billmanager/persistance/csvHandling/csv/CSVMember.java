
package ch.buhls.billmanager.persistance.csvHandling.csv;

/**
 *
 * @author simon
 */
public class CSVMember
{
    public static final int NUMMER = 0;
    public static final int NAME = NUMMER + 1;
    public static final int VORNAME = NAME + 1;
    public static final int FIRMA = VORNAME + 1;
    
    public static final int GEBURTSTAG_TAG = FIRMA + 1;
    public static final int GEBURTSTAG_MONAT = GEBURTSTAG_TAG + 1;
    public static final int GEBURTSTAG_JAHR = GEBURTSTAG_MONAT + 1;
    
    public static final int STRASSE = GEBURTSTAG_JAHR + 1;
    public static final int PLZ = STRASSE + 1;
    public static final int ORT = PLZ + 1;
    
    public static final int TEL_FESTNETZ = ORT + 1;
    public static final int TEL_MOBILE = TEL_FESTNETZ + 1;
    public static final int MAIL = TEL_MOBILE + 1;
    
    public static final int TEAM = MAIL + 1;
    public static final int ROLLE = TEAM + 1;
    
    private int  nr,plz,birthday_day,birthday_month,birthday_year;
    private String prename, name, company, street, city,phone_landline,phone_mobile,mail,team,role;

    public CSVMember()
    {
    }
    
    public CSVMember(int nr, int plz, int birthday_day, int birthday_month, int birthday_year, String prename, String name, String company, String street, String city, String phone_landline, String phone_mobile, String mail, String team, String role)
    {
        this.nr = nr;
        this.plz = plz;
        this.birthday_day = birthday_day;
        this.birthday_month = birthday_month;
        this.birthday_year = birthday_year;
        this.prename = prename;
        this.name = name;
        this.company = company;
        this.street = street;
        this.city = city;
        this.phone_landline = phone_landline;
        this.phone_mobile = phone_mobile;
        this.mail = mail;
        this.team = team;
        this.role = role;
    }

    public int getNr()
    {
        return nr;
    }

    public void setNr(int nr)
    {
        this.nr = nr;
    }

    public int getPlz()
    {
        return plz;
    }

    public void setPlz(int plz)
    {
        this.plz = plz;
    }

    public int getBirthday_day()
    {
        return birthday_day;
    }

    public void setBirthday_day(int birthday_day)
    {
        this.birthday_day = birthday_day;
    }

    public int getBirthday_month()
    {
        return birthday_month;
    }

    public void setBirthday_month(int birthday_month)
    {
        this.birthday_month = birthday_month;
    }

    public int getBirthday_year()
    {
        return birthday_year;
    }

    public void setBirthday_year(int birthday_year)
    {
        this.birthday_year = birthday_year;
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

    public String getCompany()
    {
        return company;
    }

    public void setCompany(String company)
    {
        this.company = company;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getPhone_landline()
    {
        return phone_landline;
    }

    public void setPhone_landline(String phone_landline)
    {
        this.phone_landline = phone_landline;
    }

    public String getPhone_mobile()
    {
        return phone_mobile;
    }

    public void setPhone_mobile(String phone_mobile)
    {
        this.phone_mobile = phone_mobile;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getTeam()
    {
        return team;
    }

    public void setTeam(String team)
    {
        this.team = team;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }
    
    
}
