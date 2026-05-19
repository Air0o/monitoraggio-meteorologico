/*
 */

package data.storage;

import data.Data;
import java.util.List;

public interface IDataStorage {
    void add(Data d);
    void add(List<Data> d);
    List<Data> listAll();
    void purge();
}
