
package ch.buhls.billmanager.gui.viewModel.criteria;

import java.util.List;

/**
 *
 * @author simon
 * @param <T>
 */
public class CriteriaHandle<T> implements IFilterHandle
{
    private final ICriteria<T> filter;
    private final ICriteriaContainer<T> container;
    private final List<ICriteria<T>> filters;

    /**
     * @deprecated 
     * @param personFilter
     * @param filters 
     */
    public CriteriaHandle(ICriteria<T> personFilter, List<ICriteria<T>> filters) {
        this.filter = personFilter;
        this.filters = filters;
        this.container = null;
    }

    public CriteriaHandle(ICriteria<T> personFilter, ICriteriaContainer<T> container) {
        this.filter = personFilter;
        this.filters = null;
        this.container = container;
    }

    @Override
    public void delete() {
        if(this.filters != null){
            this.filters.remove(filter);
        }
        if(this.container != null){
            this.container.deleteCriteria(filter);
        }
    }
    
}
