
package ch.buhls.billmanager.persistance.csvHandling;

/**
 *
 * @author sdb
 */
public class Element
{
    private Type type;
    private String data;

    public Element(Type type, String data)
    {
        this.type = type;
        this.data = data;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public enum Type
    {
        COMMENT,
        ELEMENT
    }
}
