
package ch.buhls.billmanager.gui;

import java.util.List;

/**
 *
 * @author simon
 */
public class FilterHandle
{
    private final IPersonFilter personFilter;
    private final List<IPersonFilter> filters;

    public FilterHandle(IPersonFilter personFilter, List<IPersonFilter> filters) {
        this.personFilter = personFilter;
        this.filters = filters;
    }

    public void delete() {
        filters.remove(personFilter);
    }
    
}
