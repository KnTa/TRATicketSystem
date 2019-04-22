package schedual;

import station.ReadStation;
import station.Station;
import train.ReadStationAdpter;

import java.util.Date;

public class TrainStationSchedule {
    private Station next_station;
    private Date arrive_time;
    private Date departure_time;
    private  int train_time;
    private Station station;

    public TrainStationSchedule(int station, int train_time, Date arrive_time, Date departure_time, int next_station){
        ReadStation readStation = ReadStationAdpter.getReadStaion();

        this.station = readStation.getStation(station);
        this.arrive_time = arrive_time;
        this.departure_time = departure_time;
        this.next_station = readStation.getStation(next_station);
        this.train_time = train_time;
    }
}
