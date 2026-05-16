package monitoraggiometeorologico;

import data.Data;
import data.storage.IDataStorage;
import java.util.*;
import java.util.concurrent.*;

public class CentroElaborazione {
    private ExecutorService executor;
    private final List<StazioneMeteo> stazioni = new ArrayList<>();
    private final IDataStorage dataStorage;

    public CentroElaborazione(IDataStorage dataStorage) {
        this.dataStorage = dataStorage;
        
        for(int i = 0; i < 10; i++){
            StazioneMeteo stazione = new StazioneMeteo(
                    Long.valueOf(i), 
                    "nome", 
                    "posizione"
            );
            
            stazioni.add(stazione);
        }
    }
    
    
    public void StartDataCollection() throws InterruptedException{
        StartDataCollection(3);
    }
    
    public void StartDataCollection(int cycles) throws InterruptedException{
        for(int cycle = 0; cycle < cycles; cycle++){
            System.out.printf("Starting data collection cycle (%d)\n", cycle);
            
            executor = Executors.newFixedThreadPool(4); 
            setStationsExecutorServiceInstances();
            dataStorage.purge();
            
            for(StazioneMeteo stazione : stazioni){
                stazione.getDataAndAddToStorage(dataStorage);
            }

            System.out.println("Waiting for data collection to complete...");
            executor.shutdown();
            executor.awaitTermination(30, TimeUnit.SECONDS);
            
            System.out.println("Storage content: ");
            printStorageContent();
            
            Data averageData = getAverageData();
            System.out.println("Mean values: " + averageData.toString());
        }
    }

    private void setStationsExecutorServiceInstances(){
        for(StazioneMeteo station : stazioni){
            station.setExecutor(executor);
        }
    }
    
    private void printStorageContent(){
        List<Data> content = dataStorage.listAll();
        
        for(Data data : content){
            System.out.println(data.toString());
        }
    }
    
    private Data getAverageData(){
        List<Data> data = dataStorage.listAll();

        List<Integer> temperatures = new ArrayList<>();
        List<Integer> humidities = new ArrayList<>();
        List<Integer> pressures = new ArrayList<>();

        for(Data d : data){
            temperatures.add(d.getTemperature());
            humidities.add(d.getHumidity());
            pressures.add(d.getPressure());
        }

        Data average = new Data(
            getListAverage(temperatures),
            getListAverage(humidities),
            getListAverage(pressures)
        );

        return average;
    }

    private Integer getListAverage(List<Integer> l){
        Integer x = 0;
        for(Integer i : l){
            x += i;
        }
        return x / l.size();
    }
    
    private StazioneMeteo getStazioneWithHighTemp(){return null;}
    private StazioneMeteo getStazioneWithHighHum(){return null;}
    private StazioneMeteo getStazioneWithHighPres(){return null;}
    
    private StazioneMeteo getStazioneWithLowTemp(){return null;}
    private StazioneMeteo getStazioneWithLowHum(){return null;}
    private StazioneMeteo getStazioneWithLowPres(){return null;}
}
