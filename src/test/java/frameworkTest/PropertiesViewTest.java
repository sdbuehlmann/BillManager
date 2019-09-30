package frameworkTest;

import ch.buhls.billmanager.framework.propertyDescription.CategoryDescriptor;
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
public class PropertiesViewTest
{
    
    public PropertiesViewTest()
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
     public void DescriptionTest() {
        PropertiesView<Person> desc = new PropertiesView<>("person", Person.class);
        
        desc.addCategory("editable");
        desc.addProperty("name", String.class, (owner) -> { return owner.getName(); }, (owner,value) -> { owner.setName(value); });
        desc.addProperty("prename", String.class, (owner) -> { return owner.getPrename(); }, (owner,value) -> { owner.setPrename(value); });
        
        desc.addCategory("readonly");
        desc.addProperty("age", Integer.class, (owner) -> { return owner.getName(); });
        
        // test
        assertEquals(2, desc.getCategories().size());
        
        CategoryDescriptor category = desc.getCategories().get(0);
        assertEquals("editable", category.getKey());
        assertEquals(2, category.getProperties().size());
        assertEquals("name", category.getProperties().get(0).getKey());
        assertEquals("prename", category.getProperties().get(1).getKey());
        
        category = desc.getCategories().get(1);
        assertEquals("readonly", category.getKey());
        assertEquals(1, category.getProperties().size());
        assertEquals("age", category.getProperties().get(0).getKey());
     }
     
     private class Person{
        private String name;
        private String prename;
        
        private int age;

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
     }
}
