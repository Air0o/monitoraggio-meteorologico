package view;

import data.Data;
import java.util.List;

public interface IDataView {
    void showMessage(String msg);
    void showMessage(String msg, MessageType type);

    void showData(Data d);
    void showData(List<Data> d);
}
