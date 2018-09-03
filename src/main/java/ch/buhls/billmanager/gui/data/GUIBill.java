package ch.buhls.billmanager.gui.data;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.properties.IPropertyData;
import ch.buhls.billmanager.gui.data.properties.IntegerAdapterProperty;
import ch.buhls.billmanager.gui.data.properties.ObjectAdapterProperty;
import ch.buhls.billmanager.gui.data.properties.StringAdapterProperty;
import ch.buhls.billmanager.persistance.database.entities.Bill;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author simon
 */
public class GUIBill extends AGUIData<Bill>
{
    public enum GUIBillStatus{
        SENDET(GUIStringCollection.BILL_STATUS_SENDET),
        PAID(GUIStringCollection.BILL_STATUS_PAID),
        STORNO(GUIStringCollection.BILL_STATUS_STORNO);
        
        private String label;

        GUIBillStatus(String label) {
            this.label = label;
        }

        public String toString() {
            return label;
        }
    }

    private final ObjectProperty<LocalDate> sendetDate;
    private final ObjectProperty<LocalDate> closedDate;

    private final StringProperty location;
    private final StringProperty comment;
    private final ObjectProperty<GUIBillStatus> state;

    private final IntegerProperty paymentPeriodInDays;
    private final IntegerProperty sumInRp;
    private final IntegerProperty nrPositions;

    private final GUITemplate template;
    private final GUIFinancialYear year;
    private final GUIPersonBaseData person;

    public GUIBill(Bill t, GUITemplate template, GUIFinancialYear year, GUIPersonBaseData person) {
        super(t);

        this.template = template;
        this.year = year;
        this.person = person;

        sendetDate = new ObjectAdapterProperty<>(new IPropertyData<LocalDate>()
        {
            @Override
            public LocalDate get() {
                return data.getDateSendet().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            }

            @Override
            public void set(LocalDate set) {
                data.setDateSendet(Date.from(set.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return GUIStringCollection.BILL_DATE_SENDET;
            }
        });

        closedDate = new ObjectAdapterProperty<>(new IPropertyData<LocalDate>()
        {
            @Override
            public LocalDate get() {
                if(data.getDateClosed() != null){
                    return data.getDateClosed().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                }else{
                    return null;
                }
            }

            @Override
            public void set(LocalDate set) {
                data.setDateClosed(Date.from(set.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return GUIStringCollection.BILL_DATE_CLOSED;
            }
        });

        location = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return data.getLocation();
            }

            @Override
            public void set(String set) {
                data.setLocation(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return GUIStringCollection.BILL_LOCATION;
            }
        });
        
        comment = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return data.getComment();
            }

            @Override
            public void set(String set) {
                data.setComment(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return GUIStringCollection.BILL_COMMENT;
            }
        });

        state = new ObjectAdapterProperty(new IPropertyData<GUIBillStatus>()
        {
            @Override
            public GUIBillStatus get() {
                switch (data.getBillState()) {
                    case SENDET:
                        return GUIBillStatus.SENDET;
                    case CANCELED:
                        return GUIBillStatus.STORNO;
                    case PAID:
                        return GUIBillStatus.PAID;
                    default:
                        throw new UnsupportedOperationException("illegal bill state");
                }
            }

            @Override
            public void set(GUIBillStatus set) {
                switch (set) {
                    case SENDET:
                        data.setBillState(Bill.BillState.SENDET);
                        break;
                    case STORNO:
                        data.setBillState(Bill.BillState.CANCELED);
                        break;
                    case PAID:
                        data.setBillState(Bill.BillState.PAID);
                        break;
                    default:
                        throw new UnsupportedOperationException("illegal bill state");
                }
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return GUIStringCollection.BILL_STATUS;
            }
        });
        
        paymentPeriodInDays = new IntegerAdapterProperty(new IPropertyData<Integer>()
        {
            @Override
            public Integer get() {
                return data.getPaymentPeriodInDays();
            }

            @Override
            public void set(Integer set) {
                data.setPaymentPeriodInDays(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return GUIStringCollection.BILL_PAYMENT_DEADLINE;
            }
        });
        
        sumInRp = new IntegerAdapterProperty(new IPropertyData<Integer>()
        {
            @Override
            public Integer get() {
                return Bill.getSumInRp(data);
            }

            @Override
            public void set(Integer set) {
                throw new UnsupportedOperationException("illegal operation: can not et the sum of a bill");
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return GUIStringCollection.BILL_SUM_RP;
            }
        });
        
        nrPositions = new IntegerAdapterProperty(new IPropertyData<Integer>()
        {
            @Override
            public Integer get() {
                return data.getPositions().size();
            }

            @Override
            public void set(Integer set) {
                throw new UnsupportedOperationException("illegal operation: can not set the nr of positions from a bill");
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return GUIStringCollection.BILL_NR_POSITIONS;
            }
        });
    }

    public ObjectProperty<LocalDate> getSendetDate() {
        return sendetDate;
    }

    public ObjectProperty<LocalDate> getClosedDate() {
        return closedDate;
    }

    public StringProperty getLocation() {
        return location;
    }

    public ObjectProperty<GUIBillStatus> getState() {
        return state;
    }

    public IntegerProperty getPaymentPeriodInDays() {
        return paymentPeriodInDays;
    }

    public IntegerProperty getSumInRp() {
        return sumInRp;
    }

    public IntegerProperty getNrPositions() {
        return nrPositions;
    }

    public GUITemplate getTemplate() {
        return template;
    }

    public GUIFinancialYear getYear() {
        return year;
    }

    public GUIPersonBaseData getPerson() {
        return person;
    }

    public StringProperty getComment() {
        return comment;
    }

}
