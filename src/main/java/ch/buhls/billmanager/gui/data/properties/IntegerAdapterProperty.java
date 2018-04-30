
package ch.buhls.billmanager.gui.data.properties;

import com.sun.javafx.binding.ExpressionHelper;
import java.lang.ref.WeakReference;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableNumberValue;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author simon
 */
public class IntegerAdapterProperty extends IntegerProperty {

    private IPropertyData<Integer> data;
    
    private ObservableIntegerValue observable = null;
    private InvalidationListener listener = null;
    private boolean valid = true;
    private ExpressionHelper<Number> helper = null;
    
    public IntegerAdapterProperty(IPropertyData<Integer> data) {
        this.data = data;
    }

    @Override
    public void addListener(InvalidationListener listener) {
        helper = ExpressionHelper.addListener(helper, this, listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        helper = ExpressionHelper.removeListener(helper, listener);
    }

    @Override
    public void addListener(ChangeListener<? super Number> listener) {
        helper = ExpressionHelper.addListener(helper, this, listener);
    }

    @Override
    public void removeListener(ChangeListener<? super Number> listener) {
        helper = ExpressionHelper.removeListener(helper, listener);
    }

    /**
     * Sends notifications to all attached
     * {@link javafx.beans.InvalidationListener InvalidationListeners} and
     * {@link javafx.beans.value.ChangeListener ChangeListeners}.
     *
     * This method is called when the value is changed, either manually by
     * calling {@link #set(int)} or in case of a bound property, if the
     * binding becomes invalid.
     */
    protected void fireValueChangedEvent() {
        ExpressionHelper.fireValueChangedEvent(helper);
    }

    private void markInvalid() {
        if (valid) {
            valid = false;
            invalidated();
            fireValueChangedEvent();
        }
    }

    /**
     * The method {@code invalidated()} can be overridden to receive
     * invalidation notifications. This is the preferred option in
     * {@code Objects} defining the property, because it requires less memory.
     *
     * The default implementation is empty.
     */
    protected void invalidated() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int get() {
        valid = true;
        return observable == null ? data.get() : observable.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(int newValue) {
        if (isBound()) {
            throw new java.lang.RuntimeException((getBean() != null && getName() != null ?
                    getBean().getClass().getSimpleName() + "." + getName() + " : ": "") + "A bound value cannot be set.");
        }
        if (data.get() != newValue) {
            data.set(newValue);
            markInvalid();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBound() {
        return observable != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind(final ObservableValue<? extends Number> rawObservable) {
        if (rawObservable == null) {
            throw new NullPointerException("Cannot bind to null");
        }

        ObservableIntegerValue newObservable;
        if (rawObservable instanceof ObservableIntegerValue) {
            newObservable = (ObservableIntegerValue)rawObservable;
        } else if (rawObservable instanceof ObservableNumberValue) {
            final ObservableNumberValue numberValue = (ObservableNumberValue)rawObservable;
            newObservable = new IntegerBinding() {
                {
                    super.bind(rawObservable);
                }

                @Override
                protected int computeValue() {
                    return numberValue.intValue();
                }
            };
        } else {
            newObservable = new IntegerBinding() {
                {
                    super.bind(rawObservable);
                }

                @Override
                protected int computeValue() {
                    final Number value = rawObservable.getValue();
                    return (value == null)? 0 : value.intValue();
                }
            };
        }

        if (!newObservable.equals(observable)) {
            unbind();
            observable = newObservable;
            if (listener == null) {
                listener = new Listener(this);
            }
            observable.addListener(listener);
            markInvalid();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unbind() {
        if (observable != null) {
            data.set(observable.get());
            observable.removeListener(listener);
            observable = null;
        }
    }

    /**
     * Returns a string representation of this {@code IntegerPropertyBase} object.
     * @return a string representation of this {@code IntegerPropertyBase} object.
     */
    @Override
    public String toString() {
        final Object bean = getBean();
        final String name = getName();
        final StringBuilder result = new StringBuilder("IntegerProperty [");
        if (bean != null) {
            result.append("bean: ").append(bean).append(", ");
        }
        if ((name != null) && (!name.equals(""))) {
            result.append("name: ").append(name).append(", ");
        }
        if (isBound()) {
            result.append("bound, ");
            if (valid) {
                result.append("value: ").append(get());
            } else {
                result.append("invalid");
            }
        } else {
            result.append("value: ").append(get());
        }
        result.append("]");
        return result.toString();
    }

    @Override
    public Object getBean() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static class Listener implements InvalidationListener {

        private final WeakReference<IntegerAdapterProperty> wref;

        public Listener(IntegerAdapterProperty ref) {
            this.wref = new WeakReference<>(ref);
        }

        @Override
        public void invalidated(Observable observable) {
            IntegerAdapterProperty ref = wref.get();
            if (ref == null) {
                observable.removeListener(this);
            } else {
                ref.markInvalid();
            }
        }
    }

    public IPropertyData<Integer> getData() {
        return data;
    }

    public void setData(IPropertyData<Integer> data) {
        this.data = data;
        fireValueChangedEvent();
    }
    
    
}
