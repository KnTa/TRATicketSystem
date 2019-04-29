package schedual;

import reservation.TrainSeatInfo;
import train.SeatInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class TrainTable {
    private static List<TrainTime> time_list;
    static {
        time_list = TrainTimeDataControlAdapter.getReadTrainTime().getAllTrainTime();
    }

    public static List<TrainSeatInfo> searchTrainSeat(int departure, int arrive, Date startTime, Date endTime, int timeCondition, int condition, int number)throws Exception {
        if(startTime.compareTo(endTime)!=-1){throw new Exception("時間錯誤");}
        List<TrainSeatInfo> trainSeatInfoList=null;
        for(TrainTime trainTime:time_list){
            if(trainTime.checkTrainTime(departure, arrive, startTime, endTime, timeCondition)){
                trainSeatInfoList = trainTime.getSeatByCondition(departure, arrive, condition, number);
                if (trainSeatInfoList!=null && trainSeatInfoList.size()==number){
                    break;
                }else if(trainSeatInfoList!=null && trainSeatInfoList.size()!=number){
                    trainSeatInfoList.clear();
                    continue;
                }else{
                    continue;
                }
            }
        }

        return trainSeatInfoList;
    }

    public static TrainTime getTrainTime(int train_time, Date date){
        for (TrainTime trainTime:time_list){
            if(trainTime.getID()==train_time && trainTime.getDate().compareTo(date)==0){
                return trainTime;
            }
        }
        return null;
    }

    public static void updateSeatRecord(List<TrainSeatInfo> trainSeatInfoList) {
        TrainTime trainTime = getTrainTime(trainSeatInfoList.get(0).getTrain_id(), trainSeatInfoList.get(0).getDate());
        trainTime.updateSeatRecord(trainSeatInfoList);
    }

    public static void updateSeatRecord(List<SeatInfo> seatInfoList, int train_id, Date date) {
        TrainTime trainTime = getTrainTime(train_id, date);
        trainTime.updateSeatRecordBySeatList(seatInfoList);
    }

    public static Map<Integer, Map<Integer, Boolean>> getAllSeatCurrentStatus(int train_id, Date date, int departure, int arrive)throws  Exception {
        TrainTime trainTime = getTrainTime(train_id, date);
        return trainTime.getAllSeatCurrentStatus(departure,arrive);
    }

    public static SeatInfo selectSeat(int ticketID, int train_time, Date date, int departure, int arrive, int car, int seat)throws Exception {
        TrainTime trainTime = getTrainTime(train_time,date);
        return trainTime.selectSeat(ticketID, departure,arrive,car,seat);
    }

    public static Date getStationDepartureTime(int train_time, Date date, int station) {
        Date date_result = getTrainTime(train_time, date).getStationDepartureTime(station);
        return date_result;
    }

    public static Date getStationArriveTime(int train_time, Date date, int station) {
        Date date_result = getTrainTime(train_time, date).getStationArriveTime(station);
        return date_result;
    }
}
