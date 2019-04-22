package SQL;
import schedual.ReadTrainStationSchedule;
import schedual.TrainStationSchedule;
import station.Station;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLTrainStationTimeSchedule implements ReadTrainStationSchedule {
    private static Statement statement;
    private static SimpleDateFormat simpleDateFormat;
    private static SimpleDateFormat simpleDateFormat_shedule;
    static {
        statement= SQLManager.getStatement();
        simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd");
        simpleDateFormat_shedule = new SimpleDateFormat ("yyyy-MM-dd HH:MM");
    }
    public List<TrainStationSchedule> getTrainStationSchedule(int train_time, Date date){
        List<TrainStationSchedule> trainStationScheduleList = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM station_time_schedule WHERE "
                    + " train_time = " + train_time
                    + " train_date = " + "'" + simpleDateFormat.format(date) + "'" + ";");
            while (resultSet.next()){
               int station = resultSet.getInt("station");
               String arrive = resultSet.getString("arrive_time");
               String departure = resultSet.getString("departure_time");
               int next_station = resultSet.getInt("station");

               trainStationScheduleList.add(new TrainStationSchedule(station, train_time, simpleDateFormat_shedule.parse(arrive), simpleDateFormat_shedule.parse(departure), next_station));
            }
        }catch (Exception e){System.err.println( e.getClass().getName() + ": " + e.getMessage() );}

        return trainStationScheduleList;
    }
}
