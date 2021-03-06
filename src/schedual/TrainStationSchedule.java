package schedual;

import station.Station;
import station.StationManager;

import java.util.Date;

public class TrainStationSchedule {
    private Station next_station;
    private Date arrive_time;
    private Date departure_time;
    private  int train_time;
    private Station station;

    public TrainStationSchedule(int station_id, int train_time, Date arrive_time, Date departure_time, int next_station_id){
        this.station = StationManager.getStation(station_id);
        this.arrive_time = arrive_time;
        this.departure_time = departure_time;
        this.next_station = StationManager.getStation(next_station_id);
        this.train_time = train_time;
    }

    public Station getStation() {
        return station;
    }

    public Station getNext_station() {
        return next_station;
    }

    public Date getArrive_time() {
        return arrive_time;
    }

    public Date getDeparture_time() {
        return departure_time;
    }
}
