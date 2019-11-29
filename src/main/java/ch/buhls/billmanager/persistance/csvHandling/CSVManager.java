package ch.buhls.billmanager.persistance.csvHandling;

import ch.buhls.billmanager.utils.IPropertiesSet;
import ch.buhls.billmanager.utils.IPropertyDescriptor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author sdb
 */
public class CSVManager<TDataContainer>
{

    private CSVFileManager csvManager;

    public CSVManager() {
        csvManager = new CSVFileManager();
    }

    public List<CSVPerson> readMembers(File location) throws IOException, MatchException {
        List<CSVPerson> members = new ArrayList<>();
        List<Line> lines = csvManager.readCSVFile(location);

        for (Line line : lines) {
            if (line.getElements().size() > 1) {
                members.add(matchMember(line));
            }
        }

        return members;
    }

    private CSVPerson matchMember(Line line) throws MatchException {
        try {
            CSVPerson member = new CSVPerson();
            member.setName(line.getElements().get(CSVPerson.NAME));
            member.setPrename(line.getElements().get(CSVPerson.VORNAME));
            member.setCompany(line.getElements().get(CSVPerson.FIRMA));

            member.setBirthday_day(parseInt(line.getElements().get(CSVPerson.GEBURTSTAG_TAG)));
            member.setBirthday_month(parseInt(line.getElements().get(CSVPerson.GEBURTSTAG_MONAT)));
            member.setBirthday_year(parseInt(line.getElements().get(CSVPerson.GEBURTSTAG_JAHR)));

            member.setStreet(line.getElements().get(CSVPerson.STRASSE));
            member.setPlz(parseInt(line.getElements().get(CSVPerson.PLZ)));
            member.setCity(line.getElements().get(CSVPerson.ORT));

            member.setPhone_landline(line.getElements().get(CSVPerson.TEL_FESTNETZ));
            member.setPhone_mobile(line.getElements().get(CSVPerson.TEL_MOBILE));
            member.setMail(line.getElements().get(CSVPerson.MAIL));

            member.setSalutation(line.getElements().get(CSVPerson.BEGRUESSUNG));
            member.setTitle(line.getElements().get(CSVPerson.TITEL));

            return member;
        }
        catch (Exception ex) {
            throw new MatchException("can not match member. Line nr. " + line.getLineNumber());
        }
    }

    private int parseInt(String string) {
        int value = 0;
        try {
            value = Integer.parseInt(string);
        }
        catch (Exception ex) {
        }

        return value;
    }

    public TDataContainer read(File location, IPropertiesSet propertiesSet, TDataContainer container) throws IOException {
        List<Line> lines = csvManager.readCSVFile(location);

        return null;
    }

    public void write(File location, IPropertiesSet propertiesSet, Collection<TDataContainer> containers) throws IOException {
        List<Line> lines = containers.stream()
                .map(dataContainer -> containerToLine(propertiesSet, dataContainer))
                .collect(Collectors.toList());

        csvManager.writeCSVFile(location, lines);
    }

    private Line containerToLine(IPropertiesSet propertiesSet, TDataContainer container){
        Line line = new Line();

        propertiesSet.getProperties().stream()
                .forEach(propertyDescriptor -> line.getElements().add(propertyToString(propertyDescriptor, container)));

        return line;
    }

    private String propertyToString(IPropertyDescriptor propertyDescriptor, TDataContainer container){
        if(propertyDescriptor.getType().equals(Integer.class)){
            return Integer.toString((int)propertyDescriptor.getGetter().Get(container));
        }else if(propertyDescriptor.getType().equals(String.class)){
            return (String)propertyDescriptor.getGetter().Get(container);
        }

        return "unsupported";
    }
}
