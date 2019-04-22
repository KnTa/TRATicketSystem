package SQL;

import train.ReadSeatSectionRecord;
import train.SectionRecord;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

public class SQLSeatSectionRecord implements ReadSeatSectionRecord {
    private static Statement statement;
    private static SimpleDateFormat simpleDateFormat;
    static {
        statement= SQLManager.getStatement();
        simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd");
    }
    public List<SectionRecord> getSectionRecord(int train_time, Date date, int car_id, int seat_id){
        List<SectionRecord> recordList=new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM seat_section_record WHERE "
                    + "train_time = " + train_time
                    + " AND date LIKE '" + simpleDateFormat.format(date) + "'"
                    + " AND car = " + car_id
                    + " AND seat = " + seat_id + " ;");
            while (resultSet.next()){
                int ticket_id = resultSet.getInt("ticket_id");
                int departure_station = resultSet.getInt("departure_station");
                int arrive_station = resultSet.getInt("arrive_station");

                recordList.add(new SectionRecord(departure_station, arrive_station, ticket_id));
            }
        }catch (Exception e){System.err.println( e.getClass().getName() + ": " + e.getMessage() );}
        return recordList;
    }
    public void setSectionRecord(int train_time, Date date, int car_id, int seat_id, int departure, int arrive, int ticket){
        try {
            statement.executeUpdate("INSERT INTO seat_section_record VALUES ("
                    + train_time + ", "
                    + "'" + simpleDateFormat.format(date)  + "'" + ", "
                    + car_id + ", "
                    + seat_id + ", "
                    + departure + ", "
                    + arrive + ", "
                    + ticket
                    + ");");
            SQLManager.commite();
        }catch (Exception e){System.err.println( e.getClass().getName() + ": " + e.getMessage() );}
    }
    public void updateSectionRecord(int car_id, int seat_id, int ticket){
        try {
            statement.executeUpdate("UPDATE seat_section_record set "
                    + "car = " + car_id +", "
                    + "seat = " + seat_id
                    + " WHERE ticket_id = " + ticket + ";");
            SQLManager.commite();
        }catch (Exception e){System.err.println( e.getClass().getName() + ": " + e.getMessage() );}
    }

    public void deleteSectionRecord(int ticket){
        try {
            statement.executeUpdate("DELETE from seat_section_record WHERE "
                    + " ticket_id = " + ticket + ";");
            SQLManager.commite();
        }catch (Exception e){System.err.println( e.getClass().getName() + ": " + e.getMessage() );}
    }
}
