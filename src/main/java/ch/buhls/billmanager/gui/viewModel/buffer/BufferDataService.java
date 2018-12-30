
package ch.buhls.billmanager.gui.viewModel.buffer;

import ch.buhls.billmanager.gui.viewModel.dataLoader.IDataLoader;
import ch.buhls.billmanager.gui.viewModel.filter.IFilterService;
import ch.buhls.billmanager.gui.viewModel.wrappers.IDataWrapper;
import ch.buhls.billmanager.gui.data.AGUIData;
import ch.buhls.billmanager.gui.data.CopiedDataObjectContainer;
import ch.buhls.billmanager.gui.viewModel.criteria.ICriteriaContainer;
import ch.buhls.billmanager.persistance.database.entities.AEntity;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ch.buhls.billmanager.gui.viewModel.criteria.ICriteria;

/**
 *
 * @author simon
 * @param <G>
 * @param <T>
 */
public class BufferDataService<T extends AEntity<T>, G extends AGUIData<T>> implements ICriteriaContainer<T>
{
    private final ObservableList<G> buffer;
    private final List<ICriteria<T>> criterias;
    
    private final IFilterService filterService;
    private final IDataLoader<T> loadDataService;
    private final IDataWrapper<T,G> wrapper;

    public BufferDataService(IFilterService<T> filterService, IDataLoader<T> loadDataService, IDataWrapper<T,G> wrapper) {
        this.buffer = FXCollections.observableArrayList();
        this.criterias = new ArrayList<>();
        
        this.filterService = filterService;
        this.loadDataService = loadDataService;
        this.wrapper = wrapper;
    }
    
    public void add(G guiData){
        this.buffer.add(guiData);
        this.filterService.filter(criterias, this.wrapper.unwrapListElements(buffer));
    }
    
    public void add(T data){
        this.buffer.add(this.wrapper.wrapEntity(data));
        this.filterService.filter(criterias, this.wrapper.unwrapListElements(buffer));
    }
    
    public void update(CopiedDataObjectContainer<G> copiedDataContainer){
        copiedDataContainer.getOriginalDataObject().setData(copiedDataContainer.getCopiedDataObject().getData());
        this.filterService.filter(criterias, this.wrapper.unwrapListElements(buffer));
    }
    
    public void addCriteria(ICriteria criteria){
        this.criterias.add(criteria);
        
        if(criterias.size() == 1){
            // was first criteria
            this.reloadData();
        }else{
            // not first criteria, data is allready loaded -> only re-filter it
            this.filterService.filter(criterias, this.wrapper.unwrapListElements(buffer));
        }
    }
    
    @Override
    public void deleteCriteria(ICriteria<T> criteria){
        this.criterias.remove(criteria);
        this.reloadData();
    }
    
    public void reloadData(){
        // load data
        List<T> loadedData;
        if(criterias.isEmpty()){
            loadedData = this.loadDataService.loadData();
        }else{
            loadedData = this.loadDataService.loadData(criterias.get(0));
        }
        
        // filter data
        this.filterService.filter(criterias, loadedData.iterator());
       
        // refresh buffer
        this.buffer.clear();
        for(T data : loadedData){
            this.buffer.add(this.wrapper.wrapEntity(data));
        }
    }

    public ObservableList<G> getBuffer() {
        return buffer;
    }
    
    
}
