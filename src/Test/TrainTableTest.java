package Test;

import org.junit.Test;
import reservation.TrainSeatInfo;
import schedual.TrainTable;
import train.SeatInfo;
import user.ReserveCondition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

public class TrainTableTest {
    SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Test
    public void searchTrainSeat() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,13);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        cal.set(2019, 06, 1);

        Date departureDate = cal.getTime();

        cal.set(Calendar.HOUR_OF_DAY,14);
        Date arriveDate = cal.getTime();

        assertTrue(TrainTable.searchTrainSeat(73, 102, departureDate, arriveDate, 1, ReserveCondition.SEAT_CONDITION_NONE, 1).size()>0);
        assertTrue(TrainTable.searchTrainSeat(73, 102, departureDate, arriveDate, 1, ReserveCondition.SEAT_CONDITION_WINDOW, 1).size()>0);
        assertTrue(TrainTable.searchTrainSeat(73, 102, departureDate, arriveDate, 1, ReserveCondition.SEAT_CONDITION_AISLE, 1).size()>0);
    }

    @Test
    public void getTrainTime() {
    }

    @Test
    public void updateSeatRecordTest(){
        TrainSeatInfo trainSeatInfo = null;
        List<TrainSeatInfo> list = new ArrayList<>();
        try {
            Date date =  simpleDateFormat.parse("2019-07-01 00:00");
            trainSeatInfo = new TrainSeatInfo(221,date,new SeatInfo(221,date, 2, 2),0,0);
            list.add(trainSeatInfo);
        }catch (Exception e){}

        TrainTable.updateSeatRecord(list);
    }

    @Test
    public void getAllSeatCurrentStatusTest()throws Exception{
        Date date=null;
        try {
             date = simpleDateFormat.parse("2019-07-01 00:00");
        }catch (Exception e){}
        Map<Integer,Map<Integer,Boolean>> result = TrainTable.getAllSeatCurrentStatus(221,date,51,100);
        assertTrue(result.size()==2);
    }
}