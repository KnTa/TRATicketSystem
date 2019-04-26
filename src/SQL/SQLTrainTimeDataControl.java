package SQL;

import schedual.TrainTimeDataControl;
import schedual.TrainTime;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLTrainTimeDataControl implements TrainTimeDataControl {
    private static Statement statement;
    private static SimpleDateFormat simpleDateFormat;
    static {
        statement= SQLManager.getStatement();
        simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd");
    }
    public List<TrainTime> getAllTrainTime(){
        List<TrainTime> trainTimeList = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM train_time;");
            while (resultSet.next()){
                int train_id = resultSet.getInt("train_time");
                int start_station = resultSet.getInt("start_station");
                int end_station = resultSet.getInt("end_station");
                int train_class = resultSet.getInt("class");
                Date date = simpleDateFormat.parse(resultSet.getString("date"));

                trainTimeList.add(new TrainTime(train_id, start_station, end_station, date, train_class));
            }
        }catch (Exception e){System.err.println( e.getClass().getName() + ": " + e.getMessage() );}
        return trainTimeList;
    }
}
