package SQL;
import schedual.TrainStationScheduleDataControl;
import schedual.TrainStationSchedule;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLTrainStationTimeScheduleDataControl implements TrainStationScheduleDataControl {
    private static Statement statement;
    private static SimpleDateFormat simpleDateFormat;
    private static SimpleDateFormat simpleDateFormat_schedule;
    static {
        statement= SQLManager.getStatement();
        simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd");
        simpleDateFormat_schedule = new SimpleDateFormat ("yyyy-MM-dd HH:MM");
    }
    public List<TrainStationSchedule> getTrainStationSchedule(int train_time, Date date){
        List<TrainStationSchedule> trainStationScheduleList = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM station_time_schedule WHERE "
                    + " train_time = " + train_time
                    + " AND train_date LIKE " + "'" + simpleDateFormat.format(date) + "'" + ";");
            while (resultSet.next()){
               int station = resultSet.getInt("station");
               String arrive = resultSet.getString("arrive_time");
               String departure = resultSet.getString("departure_time");
               int next_station = resultSet.getInt("next_station");

               Date arriveData = null;
               if(arrive!=null){ arriveData=simpleDateFormat_schedule.parse(arrive); }
               Date departureDate = null;
               if(departure!=null){ departureDate=simpleDateFormat_schedule.parse(departure); }

               trainStationScheduleList.add(new TrainStationSchedule(station, train_time, arriveData, departureDate, next_station));
            }
        }catch (Exception e){System.err.println( e.getClass().getName() + ": " + e.getMessage() );}

        return trainStationScheduleList;
    }
}
