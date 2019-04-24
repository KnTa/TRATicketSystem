package station;

import SQL.SQLStation;

public class ReadStationAdapter {
    public static ReadStation getReadStation(){
        return new SQLStation();
    }
}
