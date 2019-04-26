package station;

import SQL.SQLStationDataControl;

public class StationDataControlAdapter {
    public static StationDataControl getReadStation(){
        return new SQLStationDataControl();
    }
}
