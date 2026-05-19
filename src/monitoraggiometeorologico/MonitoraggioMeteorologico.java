
package monitoraggiometeorologico;

import data.storage.DataStorageSecure;
import data.storage.IDataStorage;
import security.Alarm;
import security.ISecurityService;

public class MonitoraggioMeteorologico {

    public static void main(String[] args) throws InterruptedException {
        ISecurityService securityService = new Alarm();
        
        IDataStorage dataStorage = new DataStorageSecure(securityService);
        CentroElaborazione centro = new CentroElaborazione(dataStorage);
        
        centro.StartDataCollection();
    }
    
}
