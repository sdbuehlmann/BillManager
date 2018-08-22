
package ch.buhls.billmanager.model.data;

/**
 *
 * @author sdb
 */
public class Position
{
    private int position, number, price, sum;
    private String firstLine, secondLine;

    public Position(int position, int number, int price, int sum, String firstLine, String secondLine)
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

    public void setPosition(int position)
    {
        this.position = position;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getSum()
    {
        return sum;
    }

    public void setSum(int sum)
    {
        this.sum = sum;
    }

    public String getFirstLine()
    {
        return firstLine;
    }

    public void setFirstLine(String firstLine)
    {
        this.firstLine = firstLine;
    }

    public String getSecondLine()
    {
        return secondLine;
    }

    public void setSecondLine(String secondLine)
    {
        this.secondLine = secondLine;
    }

    
}
