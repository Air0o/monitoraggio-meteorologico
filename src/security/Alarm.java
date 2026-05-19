package security;

import data.Data;

public class Alarm implements ISecurityService{

    @Override
    public void checkData(Data data) throws InvalidDataException, CriticalDataException{
        if(data == null){
            throw new InvalidDataException("Data is null");
        }
        if(data.getTemperature() == null){
            throw new InvalidDataException("Temperature is null");
        }
        if(data.getHumidity() == null){
            throw new InvalidDataException("Humidity is null");
        }
        if(data.getPressure() == null){
            throw new InvalidDataException("Pressure is null");
        }
        if(data.getTemperature() < -2 || data.getTemperature() > 30){
            throw new CriticalDataException("Temperature is out of range (-2°, 30°)");
        }
    }
    
}
