
package ch.buhls.billmanager.persistance.csvHandling.reader;


/**
 *
 * @author sdb
 */
public class LineParser
{
    public static final String CSV_SEPERATOR = ";";
    public static final String COMMENT = "//";
    
    public static final String SPECIAL_CHAR_UE = "%ue";
    
    public Line parse(String data, int lineNumber)
    {
        data = this.replaceSpecialCharacters(data);
        Line line = this.seperateCommentAndText(data, lineNumber);
        this.removeNonEssentialSpaces(line);
        
        return line;
    }
    
    public Line seperateCommentAndText(String data, int lineNumber)
    {
        Line line = new Line(lineNumber);
        
        String[] lineCommentSplited = data.split(COMMENT);
        
        if(lineCommentSplited.length > 1)
        {
            // comment found
            String comment = "";
            
            // everything behind the comment sign is a comment
            for(int cntCommentElements = 1; cntCommentElements < lineCommentSplited.length; cntCommentElements++)
            {
                comment = comment + lineCommentSplited[cntCommentElements];
            }
            
            line.setComment(comment);
        }

        parseData(lineCommentSplited[0], CSV_SEPERATOR, line);

        return line;
    }

    private void parseData(String data, String seperator, Line container){
        if(data.isEmpty()){
            // nothing to parse
            return;
        }

        String[] lineSeperatorSplited = data.split(seperator);
        for(String dataElement : lineSeperatorSplited)
        {
            container.getElements().add(dataElement);
        }
    }
    
    public String replaceSpecialCharacters(String text)
    {
        //text = text.replace(SPECIAL_CHAR_UE, "ü");
        char[] chars = text.toCharArray();
        for(int cnt = 0; cnt < chars.length; cnt ++)
        {
            int test  = (int)chars[cnt];
            switch((int)chars[cnt])
            {
                case 50108:
                    chars[cnt] = 'ü';
                    break;
            }
        }
        
        
        return text;
    }
    
    public void removeNonEssentialSpaces(Line line)
    {
        for(int cnt = 0; cnt < line.getElements().size(); cnt++)
        {
            String element = line.getElements().get(cnt);
            
            int nrLeadingSpaces = countLeadingSpaces(element);
            int nrSpasesAtTheEnd = countSpacesAtTheEnd(element);
            
            CharSequence subSequence = element.subSequence(nrLeadingSpaces, element.length()-nrSpasesAtTheEnd);
            
            line.getElements().set(cnt, subSequence.toString());
        }
    }
    
    public int countLeadingSpaces(String data){
        int counter = 0;
        for(char dataChar : data.toCharArray())
        {
            if(dataChar == ' ')
            {
                counter++;
            }
            else
            {
                break;
            }
        }
        
        return counter;
    }
    
    public int countSpacesAtTheEnd(String data)
    {
        int counter = 0;
        char[] dataAsArray = data.toCharArray();
        
        for(int posInArray = dataAsArray.length-1; posInArray >= 0; posInArray--)
        {
            if(dataAsArray[posInArray] == ' ')
            {
                counter++;
            }
            else
            {
                break;
            }
        }
        
        return counter;
    }
}
