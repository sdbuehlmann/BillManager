
package ch.buhls.billmanager.persistance.database.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author simon
 */
@Entity
public class FinancialYear extends ATrackedEntity<FinancialYear>
{
    @Temporal(TemporalType.DATE)
    private Date firstDay;
    
    @Temporal(TemporalType.DATE)
    private Date lastDay;
    
    private String billIdPrefix;

    public FinancialYear() {
    }

    public FinancialYear(FinancialYear model) {
        model.copyData(this);
    }

    
    
    public Date getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(Date firstDay) {
        this.firstDay = firstDay;
    }

    public Date getLastDay() {
        return lastDay;
    }

    public void setLastDay(Date lastDay) {
        this.lastDay = lastDay;
    }

    public String getBillIdPrefix() {
        return billIdPrefix;
    }

    public void setBillIdPrefix(String billIdPrefix) {
        this.billIdPrefix = billIdPrefix;
    }

    @Override
    public void copyData(FinancialYear other) {
        other.billIdPrefix = billIdPrefix;
        other.firstDay = new Date(firstDay.getTime());
        other.lastDay = new Date(lastDay.getTime());
        
        super.copyData(other);
    }
    
    
}
