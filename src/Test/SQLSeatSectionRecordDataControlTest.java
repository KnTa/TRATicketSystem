package Test;

import SQL.SQLSeatSectionRecordDataControl;
import SQL.SQLStationDataControl;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import static org.junit.Assert.*;

public class SQLSeatSectionRecordDataControlTest {

    SQLSeatSectionRecordDataControl sqlSeatSectionRecordDataControl;
    int train_time, car_id, seat_id, departure, arrive, ticket;
    Date date;

    @Before
    public void setUp() {
        sqlSeatSectionRecordDataControl = new SQLSeatSectionRecordDataControl();
        train_time = 0;
        car_id = 0;
        seat_id = 0;
        departure = 0;
        arrive = 0;
        ticket = 0;
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        date = Date.from(zdt.toInstant());
    }

    @Test
    public void SectionRecordTest() {
        sqlSeatSectionRecordDataControl.setSectionRecord(train_time, date, car_id, seat_id, departure, arrive, ticket);

        sqlSeatSectionRecordDataControl.updateSectionRecord(ticket, 5, 5);
        sqlSeatSectionRecordDataControl.updateSectionRecord(ticket, car_id, seat_id);

        assertTrue(sqlSeatSectionRecordDataControl.getSectionRecord(train_time, date, car_id, seat_id).size()>0);
        sqlSeatSectionRecordDataControl.deleteSectionRecord(ticket);
    }
}