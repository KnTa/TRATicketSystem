package Test;

import org.junit.Before;
import org.junit.Test;

import user.User;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class SearchTest {
    private User user;
    SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Before
    public void setUp() throws Exception {
        user = new User();
    }
    @Test
    public void SearchTest(){
        Date departureDate=null;
        Date arriveDate=null;
        try {
            departureDate = simpleDateFormat.parse("2019-07-01 00:00");
            arriveDate = simpleDateFormat.parse("2019-07-01 23:59");
        }catch (Exception e){}
        try {
            assertTrue(user.searchTrain(51,103,departureDate,arriveDate,1,1,1).size()>0);
        }catch (Exception e){assertTrue(false);}

    }
}
