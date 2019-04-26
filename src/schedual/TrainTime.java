package schedual;

import station.Station;
import station.StationManager;
import train.TrainFormation;

import java.util.Date;
import java.util.List;

public class TrainTime {
    private int ID;
    private Date date;
    private int train_class;
    private Station start_station, end_station;
    private TrainFormation train_formation;
    private List<TrainStationSchedule> station_schedual_list;

    public TrainTime(int id, int start_station, int end_station, Date date, int train_class){
        this.date = date;
        this.ID = id;
        this.train_class =train_class;
        this.start_station = StationManager.getStation(start_station);
        this.end_station = StationManager.getStation(end_station);

        train_formation = new TrainFormation(this.ID, this.date);
    }
}
