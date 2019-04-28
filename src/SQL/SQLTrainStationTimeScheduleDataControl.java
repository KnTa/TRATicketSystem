package SQL;
import schedual.TrainStationScheduleDataControl;
import schedual.TrainStationSchedule;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLTrainStationTimeScheduleDataControl implements TrainStationScheduleDataControl {
    private static Statement statement;
    private static DateTimeFormatter dateFormat;
    private static DateTimeFormatter  dateFormat_schedule;
    static {
        statement= SQLManager.getStatement();
        dateFormat = DateTimeFormatter.ofPattern ("yyyy-MM-dd");
        dateFormat_schedule = DateTimeFormatter.ofPattern ("yyyy-MM-dd HH:mm");
    }
    public List<TrainStationSchedule> getTrainStationSchedule(int train_time, Date date){
        List<TrainStationSchedule> trainStationScheduleList = new ArrayList<>();

        Instant instant = date.toInstant();
        LocalDateTime ldt = instant.atZone(ZoneId.of("UTC+8")).toLocalDateTime();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM station_time_schedule WHERE "
                    + " train_time = " + train_time
                    + " AND train_date LIKE " + "'" + ldt.format(dateFormat) + "'" + ";");
            while (resultSet.next()){
               int station = resultSet.getInt("station");
               String arrive = resultSet.getString("arrive_time");
               String departure = resultSet.getString("departure_time");
               int next_station = resultSet.getInt("next_station");

               Date arriveData = null;
               if(arrive!=null){
                   LocalDateTime ldt_arrive = LocalDateTime.parse(arrive, dateFormat_schedule);
                   arriveData= Date.from(ldt_arrive.atZone(ZoneId.systemDefault()).toInstant());
               }
               Date departureDate = null;
               if(departure!=null){
                   LocalDateTime ldt_departure = LocalDateTime.parse(departure, dateFormat_schedule);
                   departureDate=Date.from(ldt_departure.atZone(ZoneId.systemDefault()).toInstant());
               }

               trainStationScheduleList.add(new TrainStationSchedule(station, train_time, arriveData, departureDate, next_station));
            }
        }catch (Exception e){System.err.println( e.getClass().getName() + ": " + e.getMessage() );}

        return trainStationScheduleList;
    }
}
