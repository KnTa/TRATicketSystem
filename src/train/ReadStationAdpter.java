package train;

import SQL.SQLStation;
import station.ReadStation;

public class ReadStationAdpter {
    public static ReadStation getReadStaion(){
        return new SQLStation();
    }
}
