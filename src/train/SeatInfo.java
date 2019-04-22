package train;

import java.util.Date;

public class SeatInfo {
    private int car_id;
    private int seat_id;
    private int train_time;
    private Date date;

    public SeatInfo(int train_time, Date date, int car_id, int seat_id){
        this.train_time=train_time;
        this.date=date;
        this.car_id=car_id;
        this.seat_id=seat_id;
    }

}
