package SQL;

import train.Car;
import train.TrainFormationDataControl;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLTrainFormationDataControl implements TrainFormationDataControl {
    private static Statement statement;
    private static SimpleDateFormat simpleDateFormat;
    static {
        statement= SQLManager.getStatement();
        simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd");
    }
    public List<Car> getFormation(int train_time, Date date){
        List<Car> train_formation = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM train_formation WHERE "
                    + "train_time = " + train_time
                    + " AND date LIKE '" + simpleDateFormat.format(date) + "'" + ";");
            while (resultSet.next()){
                int car = resultSet.getInt("car");
                int seat_number = resultSet.getInt("seat_number");
                train_formation.add(new Car(train_time, date, car, seat_number));
            }
        }catch (Exception e){System.err.println( e.getClass().getName() + ": " + e.getMessage() );}
        return train_formation;
    }
}
