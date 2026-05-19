package data.storage;

import data.Data;
import java.util.ArrayList;
import java.util.List;

public class DataStorage implements IDataStorage{

    private List<Data> data = new ArrayList<>();
    
    @Override
    public synchronized void add(Data d) {
        data.add(d);
    }

    @Override
    public synchronized List<Data> listAll() {
        return data;
    }

    @Override
    public synchronized void purge() {
        data = new ArrayList<>();
    }
    
}
