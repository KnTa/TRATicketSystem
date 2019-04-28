package train;

import user.ReserveCondition;

import java.util.*;

public class Car {
    private int ID;
    private int train_time;
    private List<Seat> seats;
    private int train_class;

    public Car(int train_time, Date date, int id, int seat_number){
        this.ID=id;
        this.train_time=train_time;
        seats=new ArrayList<Seat>();
        for(int count=1;count<=seat_number;count++){
            seats.add(new Seat(train_time, date, id, count));
        }
    }

    public int getID() {
        return ID;
    }

    public int getTrain_class() {
        return train_class;
    }

    public int getTrain_time() {
        return train_time;
    }

    private boolean isAisle(SeatInfo seatInfo){
        return seatInfo.getSeat_id()%4==3 || seatInfo.getSeat_id()%4==0;
    }

    private boolean isWindow(SeatInfo seatInfo){
        return seatInfo.getSeat_id()%4==1 || seatInfo.getSeat_id()%4==2;
    }

    private List<SeatInfo> getSeatReservableBySingleCondition(int departure, int arrive, int condition, int number)throws Exception{
        List<SeatInfo> seatInfoList = new ArrayList<>();
        SeatInfo seatInfo;
        for(Seat seat:seats){
            if((condition == ReserveCondition.SEAT_CONDITION_AISLE && !isAisle(seat.getSeatInfo())
            || (condition == ReserveCondition.SEAT_CONDITION_WINDOW && !isWindow(seat.getSeatInfo())))){
                continue;
            }

            seatInfo = seat.getReservableSeat(departure, arrive);
            if(seatInfo!=null){
                seatInfoList.add(seatInfo);
                number--;
            } else {continue;}
            if(number==0){break;}
        }
        return  seatInfoList;
    }

    public List<SeatInfo> getSeatByCondition(int departure, int arrive, int condition, int number)throws Exception {
        List<SeatInfo> seatInfoList = new ArrayList<>();
        switch (condition){
            case ReserveCondition.SEAT_CONDITION_NONE:
            case ReserveCondition.SEAT_CONDITION_AISLE:
            case ReserveCondition.SEAT_CONDITION_WINDOW:
                seatInfoList.addAll(getSeatReservableBySingleCondition(departure, arrive, condition, number));
                break;
        }

        return seatInfoList;
    }

    public void setTrainClass(int train_class) {
        this.train_class = train_class;
        for(Seat seat:seats){
            seat.setTrainClass(train_class);
        }
    }

    public void updateSeatRecord(int seat_id) {
        getSeat(seat_id).updateSeatRecord();
    }
    public void updateSeatRecord(List<SeatInfo> seatInfoList) {
       for(SeatInfo seatInfo:seatInfoList){
           getSeat(seatInfo.getSeat_id()).updateSeatRecord();
       }
    }

    private Seat getSeat(int seat_id) {
        for (Seat seat:seats){
            if(seat.getSeatInfo().getSeat_id()==seat_id){
                return seat;
            }
        }
        return null;
    }

    public Map<Integer, Boolean> getAllSeatCurrentStatus(int departure, int arrive)throws Exception {
        Map<Integer, Boolean> seat_result=new TreeMap<>();
        for(Seat seat:seats){
            Integer integer=new Integer(seat.getSeatInfo().getSeat_id());
            seat_result.put(integer,seat.checkSeatStatus(departure,arrive));
        }
        return seat_result;
    }

    public SeatInfo selectSeat(int ticketID, int departure, int arrive, int seat_id)throws Exception {
        Seat seat = getSeat(seat_id);
        SeatInfo seatInfo = seat.selectSeat(ticketID, departure, arrive);
        return seatInfo;
    }
}


