package schedual;

import reservation.TrainSeatInfo;
import station.Station;
import station.StationManager;
import train.SeatInfo;
import train.TrainFormation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TrainTime {
    private int ID;
    private Date date;
    private int train_class;
    private Station start_station, end_station;
    private TrainFormation train_formation;
    private List<TrainStationSchedule> station_schedule_list;

    public TrainTime(int id, int start_station, int end_station, Date date, int train_class){
        this.date = date;
        this.ID = id;
        this.train_class =train_class;
        this.start_station = StationManager.getStation(start_station);
        this.end_station = StationManager.getStation(end_station);

        train_formation = new TrainFormation(this.ID, this.date, this.train_class);
        station_schedule_list = TrainStationScheduleDataControlFactory.getReadTrainStationSchedule().getTrainStationSchedule(id,date);
    }

    public boolean checkTrainTime(int departure, int arrive, Date startTime, Date endTime, int timeCondition) {
        boolean exist_departure=false, exist_arrive=false;
        int now_station=departure;
        //尋找是否有起訖站
        try {
            exist_departure = getTrainStationSchedule(departure).getStation().getID() == departure;
        }catch (Exception e){return false;}
        //確定迄站
        while (now_station!=0 && !exist_arrive){
            now_station = getNextStation(now_station).getID();
            exist_arrive = now_station == arrive;
        }
        if(!(exist_departure && exist_arrive)){return false;}
        //時間條件
        switch (timeCondition){
            case 1:
                Date departureDate = getTrainStationSchedule(departure).getDeparture_time();
                if(departureDate==null){return false;}
                int result_start = startTime.compareTo(departureDate);
                int result_end = endTime.compareTo(departureDate);
                if(result_start<=0 && result_end>=0)
                    {return true;}
                else
                    {return false;}
            default: return false;
        }
    }

    public List<TrainSeatInfo> getSeatByCondition(int departure, int arrive, int condition, int number) throws Exception {
        List<SeatInfo> seatInfoList = train_formation.getSeatByCondition(departure, arrive, condition, number);
        List<TrainSeatInfo> trainSeatInfoList = new ArrayList<>();
        if(seatInfoList==null){return null;}
        for(SeatInfo seatInfo:seatInfoList){
            trainSeatInfoList.add(new TrainSeatInfo(ID, date, seatInfo, departure, arrive));
        }
        return trainSeatInfoList;
    }

    public Date getDate() {
        return date;
    }

    public int getID() {
        return ID;
    }

    public Station getStart_station() {
        return start_station;
    }

    public Station getEnd_station() {
        return end_station;
    }

    public TrainStationSchedule getTrainStationSchedule(int station)throws IllegalArgumentException{
        for(TrainStationSchedule trainStationSchedule:station_schedule_list){
            if(trainStationSchedule.getStation().getID()==station){
                return trainStationSchedule;
            }
        }
        throw new IllegalArgumentException("Station not in schedule."
                + " station:" + station
                + " trainTime" + ID
                + " date" + date.toString());
    }
    public Station getNextStation(int station) throws IllegalArgumentException{
        return getTrainStationSchedule(station).getNext_station();
    }

    public void updateSeatRecord(List<TrainSeatInfo> trainSeatInfoList) {
        train_formation.updateSeatRecord(trainSeatInfoList);
    }
    public void updateSeatRecordBySeatList(List<SeatInfo> seatInfoList) {
        train_formation.updateSeatRecordBySeatList(seatInfoList);
    }

    public Map<Integer, Map<Integer, Boolean>> getAllSeatCurrentStatus(int departure, int arrive)throws Exception {
        return train_formation.getAllSeatCurrentStatus(departure,arrive);
    }

    public SeatInfo selectSeat(int ticketID, int departure, int arrive, int car, int seat)throws Exception {
        return  train_formation.selectSeat(ticketID,departure,arrive,car,seat);
    }

    public Date getStationDepartureTime(int station) {
        return getTrainStationSchedule(station).getDeparture_time();
    }

    public Date getStationArriveTime(int station) {
        return getTrainStationSchedule(station).getArrive_time();
    }
}
