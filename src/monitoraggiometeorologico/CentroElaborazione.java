package monitoraggiometeorologico;

import data.Data;
import data.storage.IDataStorage;
import java.util.*;
import java.util.concurrent.*;
import view.IDataView;

public class CentroElaborazione {
    private ExecutorService executor;
    private final Map<Long, WeatherStation> stations = new HashMap<>();
    private final IDataStorage dataStorage;

    private final IDataView view;

    public CentroElaborazione(IDataStorage dataStorage, IDataView view) {
        this.dataStorage = dataStorage;
        this.view = view;
        
        for(int i = 0; i < 10; i++){
            WeatherStation stazione = new WeatherStation(
                    Long.valueOf(i), 
                    "name", 
                    "position"
            );
            
            stations.put(Long.valueOf(i), stazione);
        }
    }
    
    
    public void StartDataCollection() throws InterruptedException{
        StartDataCollection(3);
    }
    
    public void StartDataCollection(int cycles) throws InterruptedException{
        for(int cycle = 0; cycle < cycles; cycle++){
            view.showMessage("Starting data collection cycle (" + cycle + ")");
            
            executor = Executors.newFixedThreadPool(4); 
            setStationsExecutorServiceInstances();
            dataStorage.purge();
            
            for(WeatherStation stazione : stations.values()){
                stazione.getDataAndAddToStorage(dataStorage);
            }

            view.showMessage("Waiting for data collection to complete...");
            executor.shutdown();
            executor.awaitTermination(30, TimeUnit.SECONDS);
            
            view.showMessage("Storage content: ");
            printStorageContent();
            
            Data averageData = getAverageData();
            view.showMessage("Mean values: " + averageData.toString());

            WeatherStation recordStation = getStationWithHighTemp();
            view.showMessage("Station that measured the highest temperature: " + recordStation.getId());
            
            recordStation = getStationWithLowTemp();
            view.showMessage("Station that measured the lowest temperature: " + recordStation.getId());
            recordStation = getStationWithHighHum();
            view.showMessage("Station that measured the highest humidity: " + recordStation.getId());
            recordStation = getStationWithLowHum();
            view.showMessage("Station that measured the lowest humidity: " + recordStation.getId());
            recordStation = getStationWithHighPres();
            view.showMessage("Station that measured the highest pressure: " + recordStation.getId());
            recordStation = getStationWithLowPres();
            view.showMessage("Station that measured the lowest pressure: " + recordStation.getId());
        }
    }

    private void setStationsExecutorServiceInstances(){
        for(WeatherStation station : stations.values()){
            station.setExecutor(executor);
        }
    }
    
    private void printStorageContent(){
        List<Data> content = dataStorage.listAll();
        
        view.printData(content);
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
            -1l,
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
    
    private WeatherStation getStationWithHighTemp(){
        List<Data> data = dataStorage.listAll();
        data.sort((a, b) -> {
            return b.getTemperature().compareTo(a.getTemperature());
        });
        
        Long stationId = data.get(0).getStationId();
        return stations.get(stationId);
    }
    private WeatherStation getStationWithHighHum(){
        List<Data> data = dataStorage.listAll();
        data.sort((a, b) -> {
            return b.getHumidity().compareTo(a.getHumidity());
        });
        
        Long stationId = data.get(0).getStationId();
        return stations.get(stationId);
    }
    private WeatherStation getStationWithHighPres(){
        List<Data> data = dataStorage.listAll();
        data.sort((a, b) -> {
            return b.getPressure().compareTo(a.getPressure());
        });
        
        Long stationId = data.get(0).getStationId();
        return stations.get(stationId);
    }
    
    private WeatherStation getStationWithLowTemp(){
        List<Data> data = dataStorage.listAll();
        data.sort((a, b) -> {
            return a.getTemperature().compareTo(b.getTemperature());
        });
        
        Long stationId = data.get(0).getStationId();
        return stations.get(stationId);
    }
    private WeatherStation getStationWithLowHum(){
        List<Data> data = dataStorage.listAll();
        data.sort((a, b) -> {
            return a.getHumidity().compareTo(b.getHumidity());
        });
        
        Long stationId = data.get(0).getStationId();
        return stations.get(stationId);
    }
    private WeatherStation getStationWithLowPres(){
        List<Data> data = dataStorage.listAll();
        data.sort((a, b) -> {
            return a.getPressure().compareTo(b.getPressure());
        });
        
        Long stationId = data.get(0).getStationId();
        return stations.get(stationId);
    }
}
