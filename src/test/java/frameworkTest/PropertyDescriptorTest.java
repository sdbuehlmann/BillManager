package frameworkTest;

import ch.buhls.billmanager.framework.propertyDescription.PropertiesView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author buhls
 */
public class PropertyDescriptorTest
{
    
    public PropertyDescriptorTest()
    {
    }
    
    @BeforeAll
    public static void setUpClass()
    {
    }
    
    @AfterAll
    public static void tearDownClass()
    {
    }
    
    @BeforeEach
    public void setUp()
    {
    }
    
    @AfterEach
    public void tearDown()
    {
    }

     @Test
     public void hello() {
        PropertiesView<Person> desc = new PropertiesView<>("person", Person.class);
        
        desc.addCategory("editable");
        desc.addProperty("name", String.class, (owner) -> { return owner.getName(); }, (owner,value) -> { owner.setName(value); });
        desc.addProperty("prename", String.class, (owner) -> { return owner.getPrename(); }, (owner,value) -> { owner.setPrename(value); });
        
        desc.addCategory("readonly");
        desc.addProperty("age", Integer.class, (owner) -> { return owner.getName(); });
        desc.addProperty("sex", ESex.class, (owner) -> { return owner.getSex(); });
     }
     
     private class Person{
        private String name;
        private String prename;
        
        private int age;
        private ESex sex;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }

        public String getPrename()
        {
            return prename;
        }

        public void setPrename(String prename)
        {
            this.prename = prename;
        }

        public int getAge()
        {
            return age;
        }

        public ESex getSex()
        {
            return sex;
        }
     }
     
     private enum ESex{
            FEMALE,
            MALE
        }
     
     
}
