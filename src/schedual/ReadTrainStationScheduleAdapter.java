package schedual;

import SQL.SQLTrainStationTimeSchedule;

public class ReadTrainStationScheduleAdapter {
    public static ReadTrainStationSchedule getReadTrainStationSchedule(){
        return new SQLTrainStationTimeSchedule();
    }
}
