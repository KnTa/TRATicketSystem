package Test;

import org.junit.Before;
import org.junit.Test;
import reservation.*;
import train.SeatInfo;
import train.SeatSectionRecordDataControlFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ReservationTest {

    private Reservation reservation;
    SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Before
    public void setUp() throws Exception {
        reservation = new Reservation();
    }

    @Test
    public void ticketReservation() {
        TrainSeatInfo trainSeatInfo;
        Map<Integer, Ticket> ticketMap;
        List<TrainSeatInfo> list = new ArrayList<>();
        try {
            Date date = simpleDateFormat.parse("2017-07-01 00:00");
            trainSeatInfo = new TrainSeatInfo(0, date, new SeatInfo(0, date, 0, 0), 0, 0);
            list.add(trainSeatInfo);
        }catch (Exception e){}
        ticketMap = reservation.ticketReservation(list);
        assertTrue(reservation.confirm().size()>0);
        ticketMap.forEach((id, ticket)-> ReadTicketRecordFactory.getReadTicketRecord().deleteTicketRecord(id));
        ticketMap.forEach((id, ticket)-> SeatSectionRecordDataControlFactory.getReadSeatSectionRecord().deleteSectionRecord(id));
    }
}