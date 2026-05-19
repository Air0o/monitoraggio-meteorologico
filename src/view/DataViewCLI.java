package view;

import data.Data;
import java.util.List;

public class DataViewCLI implements IDataView{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";

    @Override
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public void showMessage(String msg, MessageType type) {
        switch (type) {
            case MessageType.INFO -> {
                showMessage(msg);
            }
            case MessageType.WARNING -> {
                System.out.println(ANSI_YELLOW + msg + ANSI_RESET);
            }
            case MessageType.ERROR -> {
                System.out.println(ANSI_RED + msg + ANSI_RESET);
            }
            default -> throw new AssertionError();
        }
    }

    @Override
    public void printData(Data d) {
        System.out.println(d);
    }

    @Override
    public void printData(List<Data> data) {
        for(Data d : data){
            printData(d);
        }
    }

}
