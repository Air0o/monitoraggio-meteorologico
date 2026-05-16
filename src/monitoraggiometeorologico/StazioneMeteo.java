/*
 */
package monitoraggiometeorologico;

import data.RilevazioneDati;
import data.storage.IDataStorage;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author GI.AIROLDI
 */
public class StazioneMeteo {
    private final Long id;
    private final String name;
    private final String posizione;
    private ExecutorService executor;

    public StazioneMeteo(Long id, String name, String posizione) {
        this.id = id;
        this.name = name;
        this.posizione = posizione;
    }
    
    public void getDataAndAddToStorage(IDataStorage storage) throws IllegalStateException{
        if(executor == null){
            throw new IllegalStateException("The ExecutorService is null!");
        }
        if(executor.isShutdown()){
            throw new IllegalStateException("The ExecutorService has been shut down!");
        }
        RilevazioneDati rilevazione = new RilevazioneDati(storage);
        executor.submit(rilevazione);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosizione() {
        return posizione;
    }

    public void setExecutor(ExecutorService executor) {
        this.executor = executor;
    }
    
    
}
