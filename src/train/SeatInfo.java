package train;

import java.util.Date;

public class SeatInfo {
    private int car_id;
    private int seat_id;
    private int train_time;
    private Date date;
    private int train_class;

    public SeatInfo(int train_time, Date date, int car_id, int seat_id){
        this.train_time=train_time;
        this.date=date;
        this.car_id=car_id;
        this.seat_id=seat_id;
    }

    protected void setTrainClass(int train_class) {
        this.train_class = train_class;
    }

    public Date getDate() {
        return date;
    }

    public int getCar_id() {
        return car_id;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public int getTrain_class() {
        return train_class;
    }

    public int getTrain_time() {
        return train_time;
    }
}
