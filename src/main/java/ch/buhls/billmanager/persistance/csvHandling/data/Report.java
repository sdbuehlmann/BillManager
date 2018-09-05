
package ch.buhls.billmanager.persistance.csvHandling.data;

/**
 *
 * @author sdb
 */
public class Report
{
    private Bill bill;
    private String comment;

    public Report(Bill bill, String comment)
    {
        this.bill = bill;
        this.comment = comment;
    }

    public Bill getBill()
    {
        return bill;
    }

    public String getComment()
    {
        return comment;
    }

}
