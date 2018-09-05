package ch.buhls.billmanager.persistance.csvHandling;

import ch.buhls.billmanager.persistance.csvHandling.csv.CSVArticle;
import ch.buhls.billmanager.persistance.csvHandling.csv.CSVBill;
import ch.buhls.billmanager.persistance.csvHandling.csv.CSVReport;
import ch.buhls.billmanager.persistance.csvHandling.csv.CSVManager;
import ch.buhls.billmanager.persistance.csvHandling.csv.LinkerException;
import ch.buhls.billmanager.persistance.csvHandling.csv.MatchException;
import ch.buhls.billmanager.persistance.csvHandling.data.Address;
import ch.buhls.billmanager.persistance.csvHandling.data.Article;
import ch.buhls.billmanager.persistance.csvHandling.data.Bill;
import ch.buhls.billmanager.persistance.csvHandling.data.Position;
import ch.buhls.billmanager.persistance.csvHandling.data.Report;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sdb
 */
public class CSVDataLoader
{
    private CSVManager ioManager;

    public CSVDataLoader()
    {
        ioManager = new CSVManager();
    }

    public List<Article> loadArticles(File fileArticles) throws IOException, MatchException
    {
        List<CSVArticle> csvArts = ioManager.readArticles(fileArticles);
        List<Article> arts = new ArrayList<>();

        for (CSVArticle csvArticle : csvArts)
        {
            arts.add(new Article(
                    csvArticle.getId(),
                    csvArticle.getLine1(),
                    csvArticle.getLine2(),
                    csvArticle.getPrize()));
        }

        return arts;
    }
    
    public void writeArticles(File location, List<Report> reports) throws IOException
    {
        
    }

    public List<Bill> loadBills(List<Article> articles, File pathBills) throws IOException, MatchException, LinkerException
    {
        List<Bill> bills = new ArrayList<>();

        for (CSVBill csvBill : ioManager.readBills(pathBills))
        {
            Address address = new Address(
                    csvBill.getAnrede(),
                    csvBill.getPrename(),
                    csvBill.getName(),
                    csvBill.getStreet(),
                    csvBill.getPlz(),
                    csvBill.getCity());

            Bill bill = new Bill(
                    csvBill.getBegruessung(),
                    csvBill.getSalutationname(),
                    address,
                    csvBill.getId(),
                    csvBill.getTemplate(),
                    csvBill.isPdf_erstellen());

            bill.getPositions().add(
                    new Position(10,
                            csvBill.getMenge_art1(),
                            findArticle(articles, csvBill.getArt1())));

            bill.getPositions().add(
                    new Position(20,
                            csvBill.getMenge_art2(),
                            findArticle(articles, csvBill.getArt2())));
            
            bill.getPositions().add(
                    new Position(30,
                            csvBill.getMenge_art3(),
                            findArticle(articles, csvBill.getArt3())));
            
            bill.getPositions().add(
                    new Position(40,
                            csvBill.getMenge_art4(),
                            findArticle(articles, csvBill.getArt4())));

            bills.add(bill);
        }

        return bills;
    }

    public void writeReports(File location, List<Report> reports) throws IOException
    {
        List<CSVReport> csvReports = new ArrayList<>();

        for (Report report : reports)
        {
            csvReports.add(
                    new CSVReport(
                            report.getBill().getNumber(),
                            report.getBill().getAddress().getPrename(),
                            report.getBill().getAddress().getName(),
                            report.getBill().getAddress().getStreet(),
                            report.getBill().getAddress().getPlz(),
                            report.getBill().getAddress().getCity(),
                            report.getBill().getTotal() + "",
                            report.getComment()));
        }

        ioManager.writeReports(csvReports, location);
    }

    private Article findArticle(List<Article> articles, int id) throws LinkerException
    {
        for (Article article : articles)
        {
            if (article.getId() == id)
            {
                return article;
            }
        }

        throw new LinkerException("Article with id " + id + "not found");
    }
}
