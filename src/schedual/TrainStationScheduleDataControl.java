package schedual;

import java.util.Date;
import java.util.List;

public interface TrainStationScheduleDataControl {
    List<TrainStationSchedule> getTrainStationSchedule(int train_time, Date date);
}
