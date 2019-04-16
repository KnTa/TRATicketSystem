package schedual;

import train.TrainFormation;

import java.util.Date;
import java.util.List;

public class TrainTime {
    private int ID;
    private Date date;
    private int train_class;
    private TrainFormation train_formation;
    private List<TrainStationSchedual> station_schedual_list;
}
