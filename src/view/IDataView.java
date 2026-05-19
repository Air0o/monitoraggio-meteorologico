package view;

import java.util.List;

import data.Data;

public interface IDataView {
    void showMessage(String msg);
    void showMessage(String msg, MessageType type);

    void printData(Data d);
    void printData(List<Data> d);
}
