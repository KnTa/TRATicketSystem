package schedual;

import train.TrainFormation;

import java.util.Date;
import java.util.List;

public class TrainTime {
    private int ID;
    private Date date;
    private int train_class;
    private TrainFormation train_formation;
    private List<TrainStationSchedule> station_schedual_list;

    public TrainTime(int id, Date date, int train_class){
        this.date = date;
        this.ID = id;
        this.train_class =train_class;
    }
}
