/*
 */
package data;

/**
 *
 * @author GI.AIROLDI
 */
public class Data {
    private Integer temperature;
    private Integer humidity;
    private Integer pressure;

    public Data(Integer temperature, Integer humidity, Integer pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("Temperature: ")
            .append(temperature)
            .append(", humidity: ")
            .append(humidity)
            .append(", pressure: ")
            .append(pressure);
        
        return sb.toString();
    }
}
