package data;

import data.storage.IDataStorage;
import java.util.Random;

public class RilevazioneDati implements Runnable{

    private final IDataStorage storage;
    private final Long stationId;

    public RilevazioneDati(Long stationId, IDataStorage storage) {
        this.storage = storage;
        this.stationId = stationId;
    }

    @Override
    public void run() {
        Data data = getData();

        storage.add(data);
    }
    
    private Data getData(){
        Random rng = new Random();
        try {
            Thread.sleep(rng.nextInt(1, 3));
        } catch (InterruptedException ex) {
            System.getLogger(RilevazioneDati.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return new Data(
                stationId,
                getTemp(),
                getHum(),
                getPres()
        );
    }
    
    
    private Integer getTemp(){
        Random rng = new Random();
        return rng.nextInt(-5, 35);
    }
    
    private Integer getHum(){
        Random rng = new Random();
        return rng.nextInt(0, 100);
    }
    
    private Integer getPres(){
        Random rng = new Random();
        return rng.nextInt(980, 1050);
    }
}
