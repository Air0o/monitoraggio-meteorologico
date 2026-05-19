package data.storage;

import data.Data;
import java.util.List;
import security.CriticalDataException;
import security.ISecurityService;
import security.InvalidDataException;
import view.IDataView;
import view.MessageType;

public class DataStorageSecure implements IDataStorage{

    private final DataStorageTemp dataStorage = new DataStorageTemp();
    private final ISecurityService securityService;
    private final IDataView view;

    public DataStorageSecure(ISecurityService securityService, IDataView view) {
        this.securityService = securityService;
        this.view = view;
    }

    @Override
    public void add(Data d) {
        try {
            securityService.checkData(d);
        } catch (InvalidDataException | CriticalDataException e) {
            if(e instanceof CriticalDataException){
                view.showMessage(e.getMessage(), MessageType.WARNING);
            } else if(e instanceof InvalidDataException){
                view.showMessage(e.getMessage(), MessageType.ERROR);
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

    @Override
    public void add(List<Data> d) {
        dataStorage.add(d);
    }
    
}
