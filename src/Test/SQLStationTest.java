package Test;

import SQL.SQLStationDataControl;
import org.junit.Before;
import station.Station;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class SQLStationTest {
    SQLStationDataControl sqlStation;

    @Before
    public void setUp() {
        sqlStation = new SQLStationDataControl();
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
        List<Station> stationList = new ArrayList<>();
        stationList = sqlStation.getAllStation();
        assertTrue(stationList.size()!=0);
    }
}