package ch.buhls.billmanager.persistance.csvHandling.csv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sdb
 */
public class CSVManager
{

    private CSVFileManager csvManager;

    public CSVManager()
    {
        csvManager = new CSVFileManager();
    }

    public List<CSVBill> readBills(File locationBills) throws IOException, MatchException
    {
        List<CSVBill> bills = new ArrayList<>();
        List<Line> lines = csvManager.readCSVFile(locationBills);

        for (Line line : lines)
        {
            if (line.getElements().size() > 1)
            {
                bills.add(matchBill(line));
            }
        }

        return bills;
    }

    public List<CSVArticle> readArticles(File location) throws IOException, MatchException
    {
        List<CSVArticle> articles = new ArrayList<>();
        List<Line> lines = csvManager.readCSVFile(location);

        for (Line line : lines)
        {
            if (line.getElements().size() > 1)
            {
                articles.add(matchArticle(line));
            }
        }

        return articles;
    }

    public CSVBill matchBill(Line line) throws MatchException
    {
        List<String> elements = line.getElements();

        CSVBill csvBill;
        try
        {
            csvBill = new CSVBill(
                    elements.get(CSVBill.NUMMER),
                    elements.get(CSVBill.ANREDE),
                    elements.get(CSVBill.BEGRUESSUNG),
                    elements.get(CSVBill.VORNAME),
                    elements.get(CSVBill.NAME),
                    elements.get(CSVBill.BEGRUESSUNGSNAME),
                    elements.get(CSVBill.STRASSE),
                    elements.get(CSVBill.PLZ),
                    elements.get(CSVBill.ORT),
                    elements.get(CSVBill.TEMPLATE),
                    Boolean.parseBoolean(elements.get(CSVBill.PDF_ERSTELLEN)));
        }
        catch (Exception ex)
        {
            throw new MatchException("Not a valid id, art1, art2, art3 or text. Line nr. " + line.getLineNumber());
        }

        try
        {
            csvBill.setArt1(Integer.parseInt(elements.get(CSVBill.ART1)));
            csvBill.setMenge_art1(Integer.parseInt(elements.get(CSVBill.MENGE_ART1)));
        }
        catch (Exception ex)
        {
            throw new MatchException("Bill with no articles. Line nr. " + line.getLineNumber());
        }

        try
        {
            csvBill.setArt2(Integer.parseInt(elements.get(CSVBill.ART2)));
            csvBill.setMenge_art2(Integer.parseInt(elements.get(CSVBill.MENGE_ART2)));
        }
        catch (Exception ex)
        {
        }

        try
        {
            csvBill.setArt3(Integer.parseInt(elements.get(CSVBill.ART3)));
            csvBill.setMenge_art3(Integer.parseInt(elements.get(CSVBill.MENGE_ART3)));
        }
        catch (Exception ex)
        {
        }

        try
        {
            csvBill.setArt4(Integer.parseInt(elements.get(CSVBill.ART4)));
            csvBill.setMenge_art4(Integer.parseInt(elements.get(CSVBill.MENGE_ART4)));
        }
        catch (Exception ex)
        {
        }

        return csvBill;
    }

    public CSVArticle matchArticle(Line line) throws MatchException
    {
        // ID;line1;line2;Kosten;
        if (line.getElements().size() == 5)
        {
            try
            {
                int id = Integer.parseInt(line.getElements().get(CSVArticle.ID));
                int price = Integer.parseInt(line.getElements().get(CSVArticle.PRIZE));

                CSVArticle article = new CSVArticle(
                        id,
                        line.getElements().get(CSVArticle.LINE1),
                        line.getElements().get(CSVArticle.LINE2),
                        price,
                        line.getElements().get(CSVArticle.INTERNAL_CATEGORIE));

                return article;
            }
            catch (Exception ex)
            {
                throw new MatchException("Not a valid id or price. Line nr. " + line.getLineNumber());
            }
        }
        else
        {
            throw new MatchException("Not a valid article. Line nr. " + line.getLineNumber());
        }
    }

    public List<CSVMember> readMembers(File location) throws IOException, MatchException
    {
        List<CSVMember> members = new ArrayList<>();
        List<Line> lines = csvManager.readCSVFile(location);

        for (Line line : lines)
        {
            if (line.getElements().size() > 1)
            {
                members.add(matchMember(line));
            }
        }

        return members;
    }

    public CSVMember matchMember(Line line) throws MatchException
    {
        try
        {
            CSVMember member = new CSVMember();
            member.setNr(parseInt(line.getElements().get(CSVMember.NUMMER)));
            member.setName(line.getElements().get(CSVMember.NAME));
            member.setPrename(line.getElements().get(CSVMember.VORNAME));//    public static final int VORNAME = NAME + 1;
            member.setCompany(line.getElements().get(CSVMember.FIRMA));//    public static final int FIRMA = VORNAME + 1;

            member.setBirthday_day(parseInt(line.getElements().get(CSVMember.GEBURTSTAG_TAG)));//    public static final int GEBURTSTAG_TAG = FIRMA + 1;
            member.setBirthday_month(parseInt(line.getElements().get(CSVMember.GEBURTSTAG_MONAT)));//    public static final int GEBURTSTAG_MONAT = GEBURTSTAG_TAG + 1;
            member.setBirthday_year(parseInt(line.getElements().get(CSVMember.GEBURTSTAG_JAHR)));//    public static final int GEBURTSTAG_JAHR = GEBURTSTAG_MONAT + 1;

            member.setStreet(line.getElements().get(CSVMember.STRASSE));//    public static final int STRASSE = GEBURTSTAG_JAHR + 1;
            member.setPlz(parseInt(line.getElements().get(CSVMember.PLZ)));//    public static final int PLZ = STRASSE + 1;
            member.setCity(line.getElements().get(CSVMember.ORT));//    public static final int ORT = PLZ + 1;
  
            member.setPhone_landline(line.getElements().get(CSVMember.TEL_FESTNETZ));//    public static final int TEL_FESTNETZ = ORT + 1;
            member.setPhone_mobile(line.getElements().get(CSVMember.TEL_MOBILE));//    public static final int TEL_MOBILE = TEL_FESTNETZ + 1;
            member.setMail(line.getElements().get(CSVMember.MAIL));//    public static final int MAIL = TEL_MOBILE + 1;
  
            member.setTeam(line.getElements().get(CSVMember.TEAM));//    public static final int TEAM = MAIL + 1;
            member.setRole(line.getElements().get(CSVMember.ROLLE));//    public static final int ROLLE = TEAM + 1;
            
            return member;
        }
        catch (Exception ex)
        {
            throw new MatchException("can not match member. Line nr. " + line.getLineNumber());
        }
    }

    public void writeReports(List<CSVReport> reports, File location) throws IOException
    {
        int cnt = 1;
        List<Line> lines = new ArrayList<>();
        for (CSVReport report : reports)
        {
            Line line = new Line(cnt);
            cnt++;

            line.getElements().add(report.getId());
            line.getElements().add(report.getPrename());
            line.getElements().add(report.getName());
            line.getElements().add(report.getStreet());
            line.getElements().add(report.getPlz());
            line.getElements().add(report.getCity());
            line.getElements().add(report.getTotal());
            line.getElements().add(report.getComment());

            lines.add(line);
        }

        csvManager.writeCSVFile(location, lines);
    }
    
    public void writeArticles(List<CSVArticle> articles, File location) throws IOException
    {
        int cnt = 1;
        List<Line> lines = new ArrayList<>();
        for (CSVArticle article : articles)
        {
            Line line = new Line(cnt);
            cnt++;

            // ID;line1;line2;Kosten;
            line.getElements().add(article.getId()+"");
            line.getElements().add(article.getLine1());
            line.getElements().add(article.getLine2());
            line.getElements().add(article.getPrize()+"");

            lines.add(line);
        }

        csvManager.writeCSVFile(location, lines);
    }
    
    
    private int parseInt(String string)
    {
        int value = 0;
        try
        {
            value = Integer.parseInt(string);
        }
        catch(Exception ex)
        { 
        }
        
        return value;
    }
}
