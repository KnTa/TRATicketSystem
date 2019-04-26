package schedual;

import SQL.SQLTrainTimeDataControl;

public class TrainTimeDataControlAdapter {
    public static TrainTimeDataControl getReadTrainTime(){
        return new SQLTrainTimeDataControl();
    }
}
