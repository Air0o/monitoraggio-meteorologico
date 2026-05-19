
package monitoraggiometeorologico;

import data.storage.DataStorageSecure;
import data.storage.IDataStorage;
import security.Alarm;
import security.ISecurityService;
import view.*;

public class MonitoraggioMeteorologico {

    public static void main(String[] args) throws InterruptedException {
        ISecurityService securityService = new Alarm();

        IDataView view = new DataViewCLI();
        
        IDataStorage dataStorage = new DataStorageSecure(securityService, view);
        CentroElaborazione centro = new CentroElaborazione(dataStorage, view);
        
        centro.StartDataCollection();
    }
    
}
