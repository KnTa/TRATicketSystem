package Test;

import SQL.SQLTrainStationTimeScheduleDataControl;
import org.junit.Before;
import org.junit.Test;
import schedual.TrainStationSchedule;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class SQLTrainStationTimeScheduleTest {
    SQLTrainStationTimeScheduleDataControl sqlTrainStationTimeSchedule;

    @Before
    public void setUp() throws Exception {
        sqlTrainStationTimeSchedule = new SQLTrainStationTimeScheduleDataControl();
    }

    @Test
    public void getTrainStationSchedule() {
        Date date = new GregorianCalendar(2019, 06, 01).getTime();
        List<TrainStationSchedule> trainStationScheduleList = sqlTrainStationTimeSchedule.getTrainStationSchedule(221, date);
        assertTrue(trainStationScheduleList.size()!=0);
    }
}