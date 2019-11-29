package ch.buhls.billmanager.persistance.csvHandling.mapping;

import ch.buhls.billmanager.persistance.database.entities.Person;
import ch.buhls.billmanager.utils.IGetter;
import ch.buhls.billmanager.utils.ISetter;
import ch.buhls.billmanager.utils.PropertiesSetBuilder;

import java.util.function.Supplier;

public class MappingScheme<TOwner> {

    public void addField(Supplier<String> supplier){

    }

    public <TProperty> void addProperty(String key, Class typeProperty, IGetter<TOwner,TProperty> getter, ISetter<TOwner,TProperty> setter){

    }


    public static void main(String[] args) {
        MappingScheme<Person> scheme = new MappingScheme<>();

        PropertiesSetBuilder<Person> builder = new PropertiesSetBuilder<>("person", Person.class);

        builder
                .addProperty(
                    "key",
                    String.class,
                    person -> person.getPersonBaseData().getName(),
                    (person, value) -> person.getPersonBaseData().setName(value))
                .addProperty(
                        "key",
                        String.class,
                        person -> person.getPersonBaseData().getName(),
                        (person, value) -> person.getPersonBaseData().setName(value))
                .addProperty(
                        "key",
                        String.class,
                        person -> person.getPersonBaseData().getName(),
                        (person, value) -> person.getPersonBaseData().setName(value));
    }
}
