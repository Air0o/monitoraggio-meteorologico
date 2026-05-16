/*
 */
package data.storage;

import data.Data;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GI.AIROLDI
 */
public class DataStorage implements IDataStorage{

    List<Data> data = new ArrayList();
    
    @Override
    public synchronized void add(Data d) {
        data.add(d);
    }

    @Override
    public List<Data> listAll() {
        return data;
    }

    @Override
    public void purge() {
        data = new ArrayList();
    }
    
}
