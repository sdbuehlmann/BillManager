
package ch.buhls.billmanager.persistance.csvHandling.csv;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sdb
 */
public class Line
{
    private List<String> elements;
    private String comment;
    private int lineNumber;

    public Line(int lineNumber)
    {
        this.lineNumber = lineNumber;
        
        this.elements = new ArrayList<>();
        this.comment = "";
    }

    public List<String> getElements()
    {
        return elements;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public int getLineNumber()
    {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber)
    {
        this.lineNumber = lineNumber;
    }
}
