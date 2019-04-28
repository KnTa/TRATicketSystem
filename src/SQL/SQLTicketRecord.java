package SQL;

import reservation.ReadTicketRecord;
import reservation.TicketInfo;
import reservation.TrainSeatInfo;
import train.SeatInfo;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SQLTicketRecord implements ReadTicketRecord {
    private static Statement statement;
    private static SimpleDateFormat simpleDateFormat, simpleDateFormat_time;
    private static DateTimeFormatter dateTimeFormatter;
    static {
        statement= SQLManager.getStatement();
        simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd");
        simpleDateFormat_time = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }
    public TicketInfo getTicketRecord(int ticket_id){
        TicketInfo ticketInfo = null;
        Date date = null;
        int train_time = 0;
        int departure=0,arrive=0;
        int car_id=0,seat_id=0;
        try {
            statement.executeQuery("SELECT * FROM ticket_record WHERE id="+ticket_id+" ;");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ticket_record WHERE "
                    + "id = " + ticket_id + " ;");
            while (resultSet.next()){
                train_time =resultSet.getInt("train_time");
                departure = resultSet.getInt("departure_station");
                arrive = resultSet.getInt("arrive_station");
                car_id = resultSet.getInt("car");
                seat_id = resultSet.getInt("seat");
                date = simpleDateFormat_time.parse(resultSet.getString("reservation_date"));
            }
        }catch (Exception e){System.err.println( e.getClass().getName() + ": " + e.getMessage() );return null;}
        SeatInfo seatInfo = new SeatInfo(train_time, date, car_id, seat_id);
        TrainSeatInfo trainSeatInfo=new TrainSeatInfo(train_time, date,seatInfo,departure,arrive);
        ticketInfo = new TicketInfo(trainSeatInfo);
        return ticketInfo;
    }

    public void setTicketRecord(int ticket_id, int train_id, Date date, int car_id, int seat_id, int departure, int arrive, int status){
        try {
            LocalDateTime localDateTime = LocalDateTime.now();

            statement.executeUpdate("INSERT INTO ticket_record VALUES ("
                    + ticket_id + ", "
                    + train_id + ", "
                    + "'" + simpleDateFormat.format(date) + "', "
                    + "'" + dateTimeFormatter.format(localDateTime) + "', "
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

    public int getTicketStatus(int ticket_id){
        int status=0;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ticket_record WHERE "
                    + "id = " + ticket_id + " ;");
            while (resultSet.next()){
                status = resultSet.getInt("status");
            }
        }catch (Exception e){System.err.println( e.getClass().getName() + ": " + e.getMessage() );}
        return status;
    }

    public int getMaxId(){
        int max_id = -1;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM ticket_record;");
            while (resultSet.next()){
                max_id = resultSet.getInt(1);
            }
        }catch (Exception e){System.err.println( e.getClass().getName() + ": " + e.getMessage() );}
        return max_id;
    }
}
