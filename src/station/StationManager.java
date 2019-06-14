package station;

import java.util.List;

public class  StationManager {
    private static List<Station> stationList;
    static{
        stationList = StationDataControlFactory.getReadStation().getAllStation();
    }

    public static List<Station> getAllStation(){
        return  stationList;
    }

    public static Station getStation(int id){
        for(Station s:stationList){
            if(s.getID()==id){
                return s;
            }
        }
        return getEmptyStation();
    }

    private static Station getEmptyStation(){
        return new Station(0,"","");
    }
}
