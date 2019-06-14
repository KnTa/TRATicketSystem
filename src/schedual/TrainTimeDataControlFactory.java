package schedual;

import SQL.SQLTrainTimeDataControl;

public class TrainTimeDataControlFactory {
    public static TrainTimeDataControl getReadTrainTime(){
        return new SQLTrainTimeDataControl();
    }
}
