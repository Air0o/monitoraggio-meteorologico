/*
 */
package monitoraggiometeorologico;

import data.Data;
import data.storage.IDataStorage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * @author GI.AIROLDI
 */
public class CentroElaborazione {
    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    private final List<StazioneMeteo> stazioni = new ArrayList();
    private final IDataStorage dataStorage;

    public CentroElaborazione(IDataStorage dataStorage) {
        this.dataStorage = dataStorage;
        
        for(int i = 0; i < 10; i++){
            StazioneMeteo stazione = new StazioneMeteo(
                    Long.valueOf(i), 
                    "nome", 
                    "posizione", 
                    executor
            );
            
            stazioni.add(stazione);
        }
    }
    
    
    public void StartDataCollection() throws InterruptedException{
        StartDataCollection(3);
    }
    
    public void StartDataCollection(int cycles) throws InterruptedException{
        for(int cycle = 0; cycle < cycles; cycle++){
            System.out.println("Inizio ciclo " + cycle);
            dataStorage.purge();
            
            for(StazioneMeteo stazione : stazioni){
                stazione.getDataAndAddToStorage(dataStorage);
            }
            
            executor.awaitTermination(100, TimeUnit.SECONDS);
            
            System.out.println("Storage content: ");
            printStorageContent();
            
            //Data averageData = getAverageData();
            //System.out.println("Dati medi: " + averageData.toString());
        }
    }
    
    private void printStorageContent(){
        List<Data> content = dataStorage.listAll();
        
        for(Data data : content){
            System.out.println(data.toString());
        }
    }
    
    private Data getAverageData(){
        return null;
    }
    
    private StazioneMeteo getStazioneWithHighTemp(){return null;}
    private StazioneMeteo getStazioneWithHighHum(){return null;}
    private StazioneMeteo getStazioneWithHighPres(){return null;}
    
    private StazioneMeteo getStazioneWithLowTemp(){return null;}
    private StazioneMeteo getStazioneWithLowHum(){return null;}
    private StazioneMeteo getStazioneWithLowPres(){return null;}
}
