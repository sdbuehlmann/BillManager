
package ch.buhls.billmanager.model.data.filter;

/**
 *
 * @author simon
 */
public interface ICriteriaContainer<T>
{
    public void deleteCriteria(IFilter<T> criteria);
}
