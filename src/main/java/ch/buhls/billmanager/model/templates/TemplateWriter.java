package ch.buhls.billmanager.model.templates;


import ch.buhls.billmanager.model.data.Position;
import ch.buhls.billmanager.model.data.TemplateData;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdb
 */
public class TemplateWriter
{

    private static final Logger LOG = Logger.getLogger(TemplateWriter.class.getName());

    private String date;

    public TemplateWriter()
    {

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
        Date today = new Date();

        date = dateFormat.format(today);

        LOG.log(Level.INFO, String.format("TemplateWriter generated."));
    }

    public void fillTemplate(Template template, TemplateData bill)
    {
        writeAddress(bill, template);
        writeHeader(bill, template);
        writeSalutation(bill, template);
        writePositions(bill, template);
        writeSum(bill, template);
        writePurpose(bill, template);
        writeDate(bill, template, bill.getLocation(), bill.getDate());
    }

    public void writeAddress(TemplateData bill, Template template)
    {
        template.address.setContent(
                new String[]
                {
                    bill.getTitle(),
                    bill.getPrename() + " " + bill.getName(),
                    bill.getStreet(),
                    bill.getPlz() + " " + bill.getCity(),
                    ""
                });
    }

    public void writeHeader(TemplateData bill, Template template)
    {
        template.header.setContent("Rechnung " + bill.getNumber());
    }

    public void writeSalutation(TemplateData bill, Template template)
    {
        template.salutation.setContent(bill.getSalutation() + " " + bill.getSalutationName());
    }

    public void writePosition(Position position, Template template, int pos)
    {
        if (position != null)
        {
            template.position[pos].position.setContent(position.getPosition() + "");
            template.position[pos].line1.setContent(position.getFirstLine());
            template.position[pos].line2.setContent(position.getSecondLine());
            template.position[pos].number.setContent(position.getNumber() + "");
            template.position[pos].prize.setContent(this.formatCHF(position.getPrice()));
            template.position[pos].subtotal.setContent(this.formatCHF(position.getPrice() * position.getNumber()));
        }
        else
        {
            template.position[pos].position.setContent("");
            template.position[pos].line1.setContent("");
            template.position[pos].line2.setContent("");
            template.position[pos].number.setContent("");
            template.position[pos].prize.setContent("");
            template.position[pos].subtotal.setContent("");
        }
    }

    public void writePositions(TemplateData bill, Template template)
    {
        for (int cnt = 0; cnt < 4; cnt++)
        {
            Position pos = null;
            if (bill.getPositions().size() >= cnt + 1)
            {
                pos = bill.getPositions().get(cnt);
            }

            this.writePosition(pos, template, cnt);

        }

        template.total.setContent(this.formatCHF(bill.getTotal()));
    }

    public void writeSum(TemplateData bill, Template template)
    {
        StringBuilder builder = new StringBuilder(10);
        int total = bill.getTotal();
        String sum = "";
        if (total != 0)
        {
            sum = bill.getTotal() + "";
        }
        else
        {
            sum = "000";
        }

        for (int cnt = 0; cnt < 10; cnt++)
        {
            if (sum.length() > cnt)
            {
                template.sumLeft.sum[cnt].setContent(sum.charAt(sum.length() - 1 - cnt));
                template.sumRight.sum[cnt].setContent(sum.charAt(sum.length() - 1 - cnt));
            }
            else
            {
                template.sumLeft.sum[cnt].setContent(' ');
                template.sumRight.sum[cnt].setContent(' ');
            }

//            if(sum.length() > cnt)
//            {
//                template.sumLeft.sum[cnt].setContent(' ');
//                template.sumRight.sum[cnt].setContent(' ');
//            }
//            else
//            {
//                template.sumLeft.sum[cnt].setContent(sum.charAt(cnt-sum.length()));
//                template.sumRight.sum[cnt].setContent(sum.charAt(cnt-sum.length()));
//            }
        }
    }

    public void writePurpose(TemplateData bill, Template template)
    {
        template.purpose.setContent(
                new String[]
                {
                    bill.getNumber(),
                    "",
                    "",
                    "",
                    "",
                });
    }

    public void writeDate(TemplateData bill, Template template, String place, String date)
    {
        template.date.setContent(place + ", " + date);
    }

    protected String formatCHF(int chf)
    {
        StringBuilder builder = new StringBuilder(chf + "");

        // Check: Costs less than 1Rp.
        if ((Math.abs(chf) / 10) < 1)
        {
            builder.insert(0, "00");
        }
        
        // Check: less than 1Chf.
        if((Math.abs(chf) / 10) < 100){
            // add leading 0
            builder.insert(0,"0");
        }

        builder.insert(builder.length() - 2, ".");
        builder.append(" CHF");

        return builder.toString();
    }

    public String getDate()
    {
        return date;
    }

    public static void main(String[] args)
    {
        TemplateWriter tw = new TemplateWriter();
        System.out.println(tw.formatCHF(-6000));
    }
}
