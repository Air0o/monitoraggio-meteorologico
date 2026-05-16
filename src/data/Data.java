package data;

public class Data {
    private final Long stationId;
    private Integer temperature;
    private Integer humidity;
    private Integer pressure;

    public Data(Long stationId, Integer temperature, Integer humidity, Integer pressure) {
        this.stationId = stationId;
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
        if(stationId != -1){
            sb.append("[id: ")
                .append(stationId)
                .append("] ");

        }
        sb.append("Temperature: ")
            .append(temperature)
            .append(", \thumidity: ")
            .append(humidity)
            .append(", \tpressure: ")
            .append(pressure);
        
        return sb.toString();
    }

    public Long getStationId() {
        return stationId;
    }
}
