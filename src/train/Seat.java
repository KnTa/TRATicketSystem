package train;

import java.util.Date;

public class Seat {
    private SeatSectionRecord section;
    private SeatInfo seatInfo;

    public Seat(int train_time, Date date, int car_id, int seat_id){
        seatInfo=new SeatInfo(train_time, date, car_id, seat_id);
        section=new SeatSectionRecord(train_time, date, car_id, seat_id);
    }

    public SeatInfo getSeatInfo(){
        return seatInfo;
    }

    public void setTrainClass(int train_class) {
        seatInfo.setTrainClass(train_class);
    }

    public SeatInfo getReservableSeat(int departure, int arrive) throws Exception{
        if(section.checkSection(departure, arrive)){
            return seatInfo;
        }
        return null;
    }

    public boolean checkSeatStatus(int departure, int arrive)throws Exception{
        return section.checkSection(departure,arrive);
    }

    public void updateSeatRecord() {
        section.updateSeatSectionRecord();
    }

    public SeatInfo selectSeat(int ticketID, int departure, int arrive)throws Exception {
        updateSeatRecord();
        if(checkSeatStatus(departure, arrive)){
            return seatInfo;
        }
        return null;
    }
}
