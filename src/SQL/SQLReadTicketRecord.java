package SQL;

import reservation.ReadTicketRecord;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class SQLReadTicketRecord implements ReadTicketRecord {
    private static Statement statement;
    private static SimpleDateFormat simpleDateFormat, simpleDateFormat_time;
    static {
        statement= SQLManager.getStatement();
        simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd");
        simpleDateFormat_time = new SimpleDateFormat ("yyyy-MM-dd HH:MM");
    }
    public void setTicketRecord(int ticket_id, int train_id, Date date, int car_id, int seat_id, int departure, int arrive, int status){
        try {
            statement.executeUpdate("INSERT INTO ticket_record VALUES ("
                    + ticket_id + ", "
                    + train_id + ", "
                    + simpleDateFormat.format(date) + ", "
                    + simpleDateFormat_time.format(LocalDateTime.now()) + ", "
                    + departure + ", "
                    + arrive + ", "
                    + car_id + ", "
                    + seat_id + ", "
                    + status + ");");
            SQLManager.commit();
        }catch (Exception e){System.err.println( e.getClass().getName() + ": " + e.getMessage() );}
    }
    public void updateTicketRecord(int ticket_id, int car_id, int seat_id){
        try {
            statement.executeUpdate("UPDATE ticket_record SET "
                    + "car = " + car_id +", "
                    + "seat = " + seat_id
                    + " WHERE id = " + ticket_id + ";");
            SQLManager.commit();
       }catch (Exception e){System.err.println( e.getClass().getName() + ": " + e.getMessage() );}
    }

    public void updateTicketStatus(int ticket_id, int status){
        try {
            statement.executeUpdate("UPDATE ticket_record SET "
                    + "status = " + status
                    + " WHERE id = " + ticket_id + ";");
            SQLManager.commit();
        }catch (Exception e){System.err.println( e.getClass().getName() + ": " + e.getMessage() );}
    }
    public void deleteTicketRecord(int ticket_id){
        try {
            statement.executeUpdate("DELETE from ticket_record WHERE "
                    + " id = " + ticket_id + ";");
            SQLManager.commit();
        }catch (Exception e){System.err.println( e.getClass().getName() + ": " + e.getMessage() );}
    }
    public Date getTicketReserveDate(int ticket_id){
        Date date = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ticket_record WHERE "
                    + "id = " + ticket_id + " ;");
            while (resultSet.next()){
                date = simpleDateFormat_time.parse(resultSet.getString("reservation_date"));
            }
        }catch (Exception e){System.err.println( e.getClass().getName() + ": " + e.getMessage() );}
        return date;
    }
}
