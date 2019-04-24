package SQL;

import station.ReadStation;
import station.Station;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SQLStation implements ReadStation {
    private static Statement statement;
    static {
        statement= SQLManager.getStatement();
    }
    public Station getStation(int id){
        Station station = null;

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM station WHERE ID= " + id + ";");
            while (resultSet.next()){
                String name_en = resultSet.getString("name_en");
                String name_tcn = resultSet.getString("name_tcn");
                station = new Station(id, name_en, name_tcn);
            }
        }catch (Exception e){System.err.println( e.getClass().getName() + ": " + e.getMessage() );}

        return station;
    }

    public List<Station> getAllStation(){
        List<Station> stationList = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM station ;");
            while (resultSet.next()){
                int id = resultSet.getInt("ID");
                String name_en = resultSet.getString("name_en");
                String name_tcn = resultSet.getString("name_tcn");
                stationList.add(new Station(id, name_en, name_tcn));
            }
        }catch (Exception e){System.err.println( e.getClass().getName() + ": " + e.getMessage() );}

        return stationList;
    }
}
