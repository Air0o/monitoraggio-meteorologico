package data.storage;

import data.Data;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

public class DataStorageFile implements IDataStorage{

    private String fileName;

    public DataStorageFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void add(Data d) {
        try(ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(fileName, true))){
            oout.writeObject(d);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Data> listAll() {
        List<Data> data = new LinkedList<>();
        try(var oin = new ObjectInputStream(new FileInputStream(fileName))){
            Data d = (Data)oin.readObject();
            while(d != null){
                data.add(d);
            }
        } catch(IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public void purge() {
        File f = new File(fileName);
        f.delete();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void add(List<Data> d) {
        try(ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(fileName, true))){
            oout.writeObject(d);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
