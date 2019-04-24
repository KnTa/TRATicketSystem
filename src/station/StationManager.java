package station;

import java.util.List;

public class StationManager {
    private static List<Station> stationList;
    static{
        stationList = ReadStationAdapter.getReadStation().getAllStation();
    }

    public static Station getStation(int id){
        for(Station s:stationList){
            if(s.getID()==id){
                return s;
            }
        }
        return null;
    }
}
