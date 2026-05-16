
package monitoraggiometeorologico;

import data.storage.DataStorage;
import data.storage.IDataStorage;

public class MonitoraggioMeteorologico {

    public static void main(String[] args) throws InterruptedException {
        IDataStorage dataStorage = new DataStorage();
        CentroElaborazione centro = new CentroElaborazione(dataStorage);
        
        centro.StartDataCollection();
    }
    
}
