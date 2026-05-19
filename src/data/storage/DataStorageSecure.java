package data.storage;

import data.Data;
import java.util.List;
import security.CriticalDataException;
import security.ISecurityService;
import security.InvalidDataException;

public class DataStorageSecure implements IDataStorage{

    DataStorage dataStorage = new DataStorage();
    ISecurityService securityService;

    public DataStorageSecure(ISecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void add(Data d) {
        try {
            securityService.checkData(d);
        } catch (InvalidDataException | CriticalDataException e) {
            System.err.println(e.getMessage());
            if(e instanceof InvalidDataException){
                return;
            }
        }
        dataStorage.add(d);
    }

    @Override
    public List<Data> listAll() {
        return dataStorage.listAll();
    }

    @Override
    public void purge() {
        dataStorage.purge();
    }
    
}
