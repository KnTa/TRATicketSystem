package user;

import station.Station;
import station.StationManager;
import train.SeatInfo;

import java.util.List;

public class User {
    public static List<Station> getAllStation(){
        return StationManager.getAllStation();
    }
    public static List<SeatInfo> reserveTicket(int condition){
        List<SeatInfo> seatInfoList=null;

        return seatInfoList;
    }
}
