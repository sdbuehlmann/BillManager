
package ch.buhls.billmanager.persistance.database.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

/**
 *
 * @author simon
 */
@Entity
public class Bill extends AEntity<Bill>
{
    private String comment;
    
    @Enumerated(EnumType.STRING)
    private BillState billState;
    
    @ManyToOne
    private BillBaseData billBaseData;

    public Bill() {
    }

    public Bill(Bill model){
        model.copyData(this);
    }
    
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BillState getBillState() {
        return billState;
    }

    public void setBillState(BillState billState) {
        this.billState = billState;
    }

    public BillBaseData getBillBaseData() {
        return billBaseData;
    }

    public void setBillBaseData(BillBaseData billBaseData) {
        this.billBaseData = billBaseData;
    }

    @Override
    public void copyData(Bill other) {
        other.comment = comment;
        other.billState = billState;
        other.billBaseData = billBaseData;
        
        super.copyData(other);
    }

    public enum BillState
    {
        SENDET,
        CANCELED,
        PAID
    }
    
}
