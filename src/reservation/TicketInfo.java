package reservation;

import station.Station;
import station.StationManager;
import train.SeatInfo;

public class TicketInfo {
    private TrainSeatInfo train_seat_info;
    private Station departure, arrive;

    public TicketInfo(TrainSeatInfo trainSeatInfo){
        train_seat_info=trainSeatInfo;
        departure = StationManager.getStation(trainSeatInfo.getDeparture());
        arrive = StationManager.getStation(trainSeatInfo.getArrive());
    }

    public TrainSeatInfo getTrain_seat_info() {
        return train_seat_info;
    }

    public Station getArrive() {
        return arrive;
    }

    public Station getDeparture() {
        return departure;
    }

    void setSeatInfo(SeatInfo seatInfo){
        train_seat_info.setSeatInfo(seatInfo);
    }
}
