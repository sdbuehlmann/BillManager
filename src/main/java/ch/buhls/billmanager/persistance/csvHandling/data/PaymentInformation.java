package ch.buhls.billmanager.persistance.csvHandling.data;

/**
 *
 * @author sdb
 */
public class PaymentInformation
{
    private String[] receiver_lines;
    private String account;

    public PaymentInformation(String[] receiver_lines, String account)
    {
        this.receiver_lines = receiver_lines;
        this.account = account;
    }
    
    public String[] getReceiver_lines()
    {
        return receiver_lines;
    }

    public void setReceiver_lines(String[] receiver_lines)
    {
        this.receiver_lines = receiver_lines;
    }
    
    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }
}
