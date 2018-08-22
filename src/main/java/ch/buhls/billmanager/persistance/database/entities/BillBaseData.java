
package ch.buhls.billmanager.persistance.database.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author simon
 */
@Entity
public class BillBaseData extends AEntity<BillBaseData>
{
    private int billNr;
    
    private int paymentPeriodInDays;
    
    @Temporal(TemporalType.DATE)
    private Date date;
    
    private String location;
    
    @ManyToOne
    private BillTemplate template;
    
    @ManyToOne
    private FinancialYear financialYear;
    
    @OneToMany
    private List<Position> positions;
    
    @ManyToOne
    private PersonBaseData personBaseData;

    public BillBaseData() {
    }

    public BillBaseData(BillBaseData model){
        model.copyData(this);
    }
    
    public int getBillNr() {
        return billNr;
    }

    public void setBillNr(int billNr) {
        this.billNr = billNr;
    }

    public int getPaymentPeriodInDays() {
        return paymentPeriodInDays;
    }

    public void setPaymentPeriodInDays(int paymentPeriodInDays) {
        this.paymentPeriodInDays = paymentPeriodInDays;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BillTemplate getTemplate() {
        return template;
    }

    public void setTemplate(BillTemplate template) {
        this.template = template;
    }

    public FinancialYear getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(FinancialYear financialYear) {
        this.financialYear = financialYear;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public PersonBaseData getPersonBaseData() {
        return personBaseData;
    }

    public void setPersonBaseData(PersonBaseData personBaseData) {
        this.personBaseData = personBaseData;
    }

    @Override
    public void copyData(BillBaseData other) {
        other.billNr = billNr;
        other.date = new Date(date.getTime());
        other.location = location;
        other.template = template;
        other.financialYear = financialYear;
        other.personBaseData = personBaseData;
        other.positions = new ArrayList<>(positions);
        other.paymentPeriodInDays = paymentPeriodInDays;
        
        super.copyData(other);
    }
    
    
}
