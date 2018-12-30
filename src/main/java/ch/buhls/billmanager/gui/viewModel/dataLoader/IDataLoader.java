
package ch.buhls.billmanager.gui.viewModel.dataLoader;

import ch.buhls.billmanager.persistance.database.entities.AEntity;
import java.util.List;
import ch.buhls.billmanager.gui.viewModel.criteria.ICriteria;

/**
 *
 * @author simon
 * @param <T>
 */
public interface IDataLoader<T extends AEntity<T>>
{
    public List<T> loadData(ICriteria<T> criteria);
    
    public List<T> loadData();
}
