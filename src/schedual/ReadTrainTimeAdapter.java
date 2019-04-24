package schedual;

import SQL.SQLReadTrainTime;

public class ReadTrainTimeAdapter {
    public static ReadTrainTime getReadTrainTime(){
        return new SQLReadTrainTime();
    }
}
