package train;

import java.util.Date;

public class Seat {
    private SeatSectionRecord section;
    private SeatInfo seatInfo;

    public Seat(int train_time, Date date, int car_id, int seat_id){
        seatInfo=new SeatInfo(train_time, date, car_id, seat_id);
    }
}
