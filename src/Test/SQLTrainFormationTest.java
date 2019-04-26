package Test;

import SQL.SQLTrainFormationDataControl;
import org.junit.Before;
import org.junit.Test;
import train.Car;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class SQLTrainFormationTest {
    SQLTrainFormationDataControl sqlTrainFormation;

    @Before
    public void setUp() throws Exception {
        sqlTrainFormation = new SQLTrainFormationDataControl();
    }

    @Test
    public void getFormation() {
        Date date = new GregorianCalendar(2019, 06, 01).getTime();
        List<Car> carList = sqlTrainFormation.getFormation(221, date);
        assertTrue(carList.size()!=0);
    }
}