/*
 */
package monitoraggiometeorologico;

import data.RilevazioneDati;
import data.storage.IDataStorage;
import java.util.concurrent.Executor;

/**
 *
 * @author GI.AIROLDI
 */
public class StazioneMeteo {
    private final Long id;
    private final String name;
    private final String posizione;
    private final Executor executor;

    public StazioneMeteo(Long id, String name, String posizione, Executor executor) {
        this.id = id;
        this.name = name;
        this.posizione = posizione;
        this.executor = executor;
    }
    
    public void getDataAndAddToStorage(IDataStorage storage){
        RilevazioneDati rilevazione = new RilevazioneDati(storage);
        executor.execute(rilevazione);
    }
    
    
}
