package schedual;

import SQL.SQLTrainStationTimeScheduleDataControl;

public class TrainStationScheduleDataControlFactory {
    public static TrainStationScheduleDataControl getReadTrainStationSchedule(){
        return new SQLTrainStationTimeScheduleDataControl();
    }
}
