
package ch.buhls.billmanager.model.data;

/**
 *
 * @author sdb
 */
public class TemplatePosition
{
    private int position, number, price, sum;
    private String firstLine, secondLine;

    public TemplatePosition() {
    }
    
    public TemplatePosition(int position, int number, int price, int sum, String firstLine, String secondLine)
    {
        this.position = position;
        this.number = number;
        this.price = price;
        this.sum = sum;
        this.firstLine = firstLine;
        this.secondLine = secondLine;
    }

    public int getPosition()
    {
        return position;
    }

    public TemplatePosition setPosition(int position)
    {
        this.position = position;
        return this;
    }

    public int getNumber()
    {
        return number;
    }

    public TemplatePosition setNumber(int number)
    {
        this.number = number;
        return this;
    }

    public int getPrice()
    {
        return price;
    }

    public TemplatePosition setPrice(int price)
    {
        this.price = price;
        return this;
    }

    public int getSum()
    {
        return sum;
    }

    public TemplatePosition setSum(int sum)
    {
        this.sum = sum;
        return this;
    }

    public String getFirstLine()
    {
        return firstLine;
    }

    public TemplatePosition setFirstLine(String firstLine)
    {
        this.firstLine = firstLine;
        return this;
    }

    public String getSecondLine()
    {
        return secondLine;
    }

    public TemplatePosition setSecondLine(String secondLine)
    {
        this.secondLine = secondLine;
        return this;
    }

    
}
