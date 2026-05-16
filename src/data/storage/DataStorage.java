package data.storage;

import data.Data;
import java.util.ArrayList;
import java.util.List;

public class DataStorage implements IDataStorage{

    private List<Data> data = new ArrayList<>();
    
    @Override
    public synchronized void add(Data d) {
        System.out.printf("Storage add {%s}\n", d.toString());
        data.add(d);
    }

    @Override
    public synchronized List<Data> listAll() {
        return data;
    }

    @Override
    public synchronized void purge() {
        System.out.println("Storage purge");
        data = new ArrayList<>();
    }
    
}
