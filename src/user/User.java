package user;

import reservation.Reservation;
import reservation.Ticket;
import reservation.TrainSeatInfo;
import schedual.TrainTable;
import station.Station;
import station.StationManager;
import train.SeatInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class User {
    public Reservation reservation;
    public static List<Station> getAllStation(){
        return StationManager.getAllStation();
    }

    public User(){
        reservation = new Reservation();
    }

    public Map<Integer,Ticket> reserveTicket(int departure, int arrive, Date startTime, Date endTime, int timeCondition, int condition, int number)throws Exception{
        List<TrainSeatInfo> trainSeatInfoList;
        trainSeatInfoList = TrainTable.searchTrainSeat(departure, arrive, startTime, endTime, timeCondition, condition, number);
        Map<Integer, Ticket> ticketMap;
        ticketMap = reservation.ticketReservation(trainSeatInfoList);
        TrainTable.updateSeatRecord(trainSeatInfoList);
        return ticketMap;
    }

    public Map<Integer,Map<Integer,Boolean>> getAllSeatCurrentStatus(int train_id, Date date, int departure, int arrive)throws Exception{
        return TrainTable.getAllSeatCurrentStatus(train_id,date,departure,arrive);
    }

    public boolean selectSeat(int ticketID, int car, int seat)throws Exception{
        SeatInfo selectResult;
        List<SeatInfo> seatInfoList = new ArrayList<>();
        Ticket ticket = reservation.getTicket(ticketID);
        selectResult = TrainTable.selectSeat(ticketID
                , ticket.getTicketInfo().getTrain_seat_info().getSeat_info().getTrain_time()
                , ticket.getTicketInfo().getTrain_seat_info().getSeat_info().getDate()
                , ticket.getTicketInfo().getTrain_seat_info().getDeparture()
                , ticket.getTicketInfo().getTrain_seat_info().getArrive()
                , car, seat);
        if(selectResult==null){
           return false;
        }

        seatInfoList.add(ticket.getTicketInfo().getTrain_seat_info().getSeat_info());
        try{
            reservation.selectSeat(ticketID, selectResult);
        }catch (Exception e){return false;}
        seatInfoList.add(selectResult);
        TrainTable.updateSeatRecord(seatInfoList, ticket.getTicketInfo().getTrain_seat_info().getTrain_id(), ticket.getTicketInfo().getTrain_seat_info().getDate());
        return true;
    }
    public boolean confirm(){
        List<SeatInfo> seatInfoList;
        seatInfoList = reservation.confirm();
        if(seatInfoList.size()==0){return false;}
        TrainTable.updateSeatRecord(seatInfoList, seatInfoList.get(0).getTrain_time(), seatInfoList.get(0).getDate());
        return true;
    }

    public Date getStationDepartureTime(int train_time, Date date, int station){
        Date date_result = TrainTable.getStationDepartureTime(train_time, date, station);
        return date_result;
    }

    public Date getStationArriveTime(int train_time, Date date, int station) {
        Date date_result = TrainTable.getStationArriveTime(train_time, date, station);
        return date_result;
    }
}
