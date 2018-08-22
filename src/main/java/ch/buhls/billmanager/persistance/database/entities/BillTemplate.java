
package ch.buhls.billmanager.persistance.database.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author simon
 */
@Entity
public class BillTemplate extends AEntity<BillTemplate>
{
    private String name;
    
    private int maxPositions;
    
    @Enumerated(EnumType.STRING)
    private TypePaimentSlip typePaimentSlip;

    public BillTemplate() {
    }

    public BillTemplate(BillTemplate model) {
        model.copyData(this);
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxPositions() {
        return maxPositions;
    }

    public void setMaxPositions(int maxPositions) {
        this.maxPositions = maxPositions;
    }
    
    public TypePaimentSlip getTypePaimentSlip() {
        return typePaimentSlip;
    }

    public void setTypePaimentSlip(TypePaimentSlip typePaimentSlip) {
        this.typePaimentSlip = typePaimentSlip;
    }

    @Override
    public void copyData(BillTemplate other) {
        other.name = name;
        other.maxPositions = maxPositions;
        other.typePaimentSlip = typePaimentSlip;

        super.copyData(other);
    }
    
    
    
    public enum TypePaimentSlip{
        ES,
        ESR
    }
}
