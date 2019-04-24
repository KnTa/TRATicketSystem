package SQL;

import org.junit.Before;
import station.Station;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class SQLStationTest {
    SQLStation sqlStation;

    @Before
    public void setUp() {
        sqlStation = new SQLStation();
    }

    @org.junit.Test
    public void getStation() {
        Station station = sqlStation.getStation(100);
        assertEquals(station.getID(), 100);
        assertEquals(0, station.getName_TCN().compareTo("臺北"));
        assertEquals(0, station.getName_EN().compareTo("Taipei"));
    }

    @org.junit.Test
    public void getAllStation() {
    }
}