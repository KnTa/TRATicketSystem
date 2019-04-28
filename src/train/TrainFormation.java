package train;

import reservation.TrainSeatInfo;

import java.util.*;

public class TrainFormation {
    private int train_id;
    private Date date;
    private int train_class;
    private List<Car> cars;

    public TrainFormation(int train_id, Date date, int train_class){
        this.train_id = train_id;
        this.date = date;
        this.train_class = train_class;
        TrainFormationDataControl trainFormationDataControl = TrainFormationDataControlAdapter.getReadTrainFormation();
        cars = trainFormationDataControl.getFormation(this.train_id, this.date);
        for(Car car:cars){
            car.setTrainClass(train_class);
        }
    }

    public List<SeatInfo> getSeatByCondition(int departure, int arrive, int condition, int number) throws Exception{
        List<SeatInfo> seatInfoList = new ArrayList<>();
        int l_number = number;
        for(Car car:cars){
            seatInfoList.addAll(car.getSeatByCondition(departure, arrive, condition, l_number));
            if(seatInfoList.size()==number){break;}

            l_number -= seatInfoList.size();
        }
        if(seatInfoList.size()!=number){seatInfoList=null;}
        return seatInfoList;
    }

    public Car getCar(int car_id){
        for (Car car:cars){
            if(car.getID()==car_id){return car;}
        }
        return null;
    }

    public void updateSeatRecord(List<TrainSeatInfo> trainSeatInfoList) {
        for(TrainSeatInfo trainSeatInfo:trainSeatInfoList){
            Car car=getCar(trainSeatInfo.getSeat_info().getCar_id());
            car.updateSeatRecord(trainSeatInfo.getSeat_info().getSeat_id());
        }
    }

    public void updateSeatRecordBySeatList(List<SeatInfo> seatInfoList) {
        for(SeatInfo seatInfo:seatInfoList){
            Car car=getCar(seatInfo.getCar_id());
            car.updateSeatRecord(seatInfo.getSeat_id());
        }
    }

    public Map<Integer, Map<Integer, Boolean>> getAllSeatCurrentStatus(int departure, int arrive)throws Exception {
        Map<Integer, Boolean> car_result = new TreeMap<>();
        Map<Integer, Map<Integer, Boolean>> format_result = new TreeMap<>();
        for (Car car:cars){
            car_result = car.getAllSeatCurrentStatus(departure,arrive);
            format_result.put(car.getID(), car_result);
        }
        return format_result;
    }

    public SeatInfo selectSeat(int ticketID, int departure, int arrive, int car, int seat)throws Exception {
        return getCar(car).selectSeat(ticketID,departure,arrive,seat);
    }

}

