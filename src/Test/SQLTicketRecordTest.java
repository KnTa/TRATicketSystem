package Test;

import SQL.SQLTicketRecord;
import org.junit.Before;
import org.junit.Test;
import reservation.TicketInfo;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class SQLTicketRecordTest {
    SQLTicketRecord sqlTicketRecord;
    int ticket_id = 1;
    int train_id = 0;
    Date date = new GregorianCalendar(2019, 06, 01).getTime();;
    int car_id = 0;
    int seat_id = 0;
    int departure = 0; int arrive = 0;
    int status = 0;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    LocalDateTime localDateTime = LocalDateTime.now();

    @Before
    public void setUp() throws Exception {
        sqlTicketRecord = new SQLTicketRecord();
    }

    @Test
    public void TicketRecordTest() {
        sqlTicketRecord.setTicketRecord(ticket_id, train_id, date, car_id, seat_id, departure, arrive, status);
        sqlTicketRecord.updateTicketRecord(ticket_id, 1, 1);
        sqlTicketRecord.updateTicketStatus(ticket_id, 1);

        TicketInfo ticketInfo = sqlTicketRecord.getTicketRecord(ticket_id);
        assertTrue(ticketInfo!=null);

        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        Date date = Date.from(zdt.toInstant());
        Date reservationDate = sqlTicketRecord.getTicketReserveDate(ticket_id);
        String reservation = simpleDateFormat.format(reservationDate);
        assertTrue(simpleDateFormat.format(date).compareTo(reservation)==0);
        sqlTicketRecord.deleteTicketRecord(ticket_id);
    }

    @Test
    public void  GetMaxId(){
        assertTrue(sqlTicketRecord.getMaxId()!=-1);
    }
}