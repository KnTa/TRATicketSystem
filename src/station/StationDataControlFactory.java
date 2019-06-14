package station;

import SQL.SQLStationDataControl;

public class StationDataControlFactory {
    public static StationDataControl getReadStation(){
        return new SQLStationDataControl();
    }
}
