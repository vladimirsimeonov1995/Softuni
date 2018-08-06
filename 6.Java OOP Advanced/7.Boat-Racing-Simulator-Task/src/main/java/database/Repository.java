package database;

import Utility.Constants;
import contracts.IModelable;
import contracts.IRepository;
import exeptions.DuplicateModelException;
import exeptions.NonExistantModelException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Repository<T extends IModelable> implements IRepository {
    private Map<String, T> itemsByModel;

    public Repository()
    {
        this.setItemsByModel(new LinkedHashMap<String, T>());
    }

    public Map<String, T> getItemsByModel() {
        return this.itemsByModel;
    }

    public void setItemsByModel(HashMap<String, T> itemsByModel) {
        this.itemsByModel = itemsByModel;
    }

    public void Add(IModelable item) throws DuplicateModelException {
        if (this.itemsByModel.containsKey(item.getModel()))
        {
            throw new DuplicateModelException(Constants.DuplicateModelMessage);
        }

        this.itemsByModel.put(item.getModel(), (T) item);

    }

    public T GetItem(String model) throws NonExistantModelException {
        if (!this.itemsByModel.containsKey(model))
        {
            throw new NonExistantModelException(Constants.NonExistantModelMessage);
        }

        return this.itemsByModel.get(model);
    }

}
