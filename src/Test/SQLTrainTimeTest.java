package Test;

import SQL.SQLTrainTimeDataControl;
import org.junit.Before;
import org.junit.Test;
import schedual.TrainTime;

import java.util.List;

import static org.junit.Assert.*;

public class SQLTrainTimeTest {
    SQLTrainTimeDataControl sqlTrainTime;

    @Before
    public void setUp() throws Exception {
        sqlTrainTime =new SQLTrainTimeDataControl();
    }

    @Test
    public void getAllTrainTime() {
        List<TrainTime> trainTimeList = sqlTrainTime.getAllTrainTime();
        assertTrue(trainTimeList.size()!=0);
    }
}