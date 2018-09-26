
package ch.buhls.billmanager.persistance.database.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author simon
 */
@Entity
public class Bill extends AEntity<Bill>
{    
    @Enumerated(EnumType.STRING)
    private BillState billState;
    
    private int paymentPeriodInDays;
    private int sum;
    
    @Temporal(TemporalType.DATE)
    private Date dateSendet;
    
    @Temporal(TemporalType.DATE)
    private Date dateClosed;
    
    private String location;
    private String comment;
    
    @ManyToOne
    private BillTemplate template;
    
    @ManyToOne
    private FinancialYear financialYear;
    
    @ManyToOne
    private PersonBaseData personBaseData;
    
    @OneToMany
    private List<Position> positions;

    public Bill() {
    }

    public Bill(Bill model){
        model.copyData(this);
    }

    @Override
    public void copyData(Bill other) {
        other.billState = billState;
        
        if(dateSendet != null){
            other.dateSendet = new Date(dateSendet.getTime());
        }
        if(dateClosed != null){
            other.dateClosed = new Date(dateClosed.getTime());
        }
        
        other.comment = comment;
        other.location = location;
        
        other.paymentPeriodInDays = paymentPeriodInDays;
        other.sum = sum;
        
        other.template = template;
        other.financialYear = financialYear;
        other.positions = new ArrayList<>(positions);
        
        super.copyData(other);
    }

    public enum BillState
    {
        SENDET,
        CANCELED,
        PAID
    }

    public BillState getBillState() {
        return billState;
    }

    public void setBillState(BillState billState) {
        this.billState = billState;
    }

    public int getPaymentPeriodInDays() {
        return paymentPeriodInDays;
    }

    public void setPaymentPeriodInDays(int paymentPeriodInDays) {
        this.paymentPeriodInDays = paymentPeriodInDays;
    }

    public Date getDateSendet() {
        return dateSendet;
    }

    public void setDateSendet(Date dateSendet) {
        this.dateSendet = dateSendet;
    }

    public Date getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Date dateClosed) {
        this.dateClosed = dateClosed;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        if(positions == null){
            positions = new ArrayList<>();
        }
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
    
    public static int getSumInRp(Bill bill){
        int sum = 0;
        for(Position pos : bill.getPositions()){
            sum += pos.getNumber() * pos.getArticle().getCosts();
        }
        
        return sum;
    }

    public PersonBaseData getPersonBaseData() {
        return personBaseData;
    }

    public void setPersonBaseData(PersonBaseData personBaseData) {
        this.personBaseData = personBaseData;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
