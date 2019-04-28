package reservation;

import train.SeatInfo;

import java.util.Date;

public class TrainSeatInfo {
    private SeatInfo seat_info;
    private int train_id;
    private Date date;
    private int departure, arrive;

    public TrainSeatInfo(int train_id, Date date, SeatInfo seat_info, int departure, int arrive){
        this.train_id=train_id;
        this.date=date;
        this.seat_info=seat_info;
        this.departure=departure;
        this.arrive=arrive;
    }

    public Date getDate() {
        return date;
    }

    public int getTrain_id() {
        return train_id;
    }

    public SeatInfo getSeat_info() {
        return seat_info;
    }

    public int getArrive() {
        return arrive;
    }

    public int getDeparture() {
        return departure;
    }

    public void setSeatInfo(SeatInfo seatInfo) {
        seat_info = seatInfo;
    }
}
