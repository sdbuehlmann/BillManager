
package ch.buhls.billmanager.gui.viewModel.dataLoader;

import ch.buhls.billmanager.model.data.filter.IFilter;
import ch.buhls.billmanager.persistance.database.entities.AEntity;
import java.util.List;

/**
 *
 * @author simon
 * @param <T>
 */
public interface IDataLoader<T extends AEntity<T>>
{
    public List<T> loadData(IFilter<T> criteria);
    
    public List<T> loadData();
}
