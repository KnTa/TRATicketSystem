package SQL;

import station.ReadStation;
import station.Station;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

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
}
