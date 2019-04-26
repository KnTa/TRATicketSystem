package schedual;

import SQL.SQLTrainStationTimeScheduleDataControl;

public class TrainStationScheduleDataControlAdapter {
    public static TrainStationScheduleDataControl getReadTrainStationSchedule(){
        return new SQLTrainStationTimeScheduleDataControl();
    }
}
