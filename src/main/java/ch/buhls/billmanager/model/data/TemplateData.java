package ch.buhls.billmanager.model.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sdb
 */
public class TemplateData
{

    private String salutation, title, prename, name, street, plz, city, number, location, template, date;
    private List<TemplatePosition> positions;
    private int total;

    public TemplateData() {
    }

    public TemplateData(String salutation, String title, String prename, String name, String street, String plz, String city, String number, String location, String template, String date, List<TemplatePosition> positions, int total) {
        this.salutation = salutation;
        this.title = title;
        this.prename = prename;
        this.name = name;
        this.street = street;
        this.plz = plz;
        this.city = city;
        this.number = number;
        this.location = location;
        this.template = template;
        this.date = date;
        this.positions = positions;
        this.total = total;
    }

    public String getSalutation() {
        return salutation;
    }

    public TemplateData setSalutation(String salutation) {
        this.salutation = salutation;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TemplateData setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPrename() {
        return prename;
    }

    public TemplateData setPrename(String prename) {
        this.prename = prename;
        return this;
    }

    public String getName() {
        return name;
    }

    public TemplateData setName(String name) {
        this.name = name;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public TemplateData setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getPlz() {
        return plz;
    }

    public TemplateData setPlz(String plz) {
        this.plz = plz;
        return this;
    }

    public String getCity() {
        return city;
    }

    public TemplateData setCity(String city) {
        this.city = city;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public TemplateData setNumber(String number) {
        this.number = number;
        return this;
    }

    public List<TemplatePosition> getPositions() {
        if (this.positions == null) {
            this.positions = new ArrayList<>();
        }

        return positions;
    }

    public int getTotal() {
        return total;
    }

    public TemplateData setTotal(int total) {
        this.total = total;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public TemplateData setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getDate() {
        return date;
    }

    public TemplateData setDate(String date) {
        this.date = date;
        return this;
    }

    public String getTemplate() {
        return template;
    }

    public TemplateData setTemplate(String template) {
        this.template = template;
        return this;
    }

}
