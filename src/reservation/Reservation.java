package reservation;

import train.SeatInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Reservation {
    private Map<Integer, Ticket> ticket_map;
    private ReadTicketRecord readTicketRecorde;

    public Reservation(){
        ticket_map = new TreeMap<>();
        readTicketRecorde = ReadTicketRecordFactory.getReadTicketRecord();
    }

    public Map<Integer, Ticket> ticketReservation(List<TrainSeatInfo> trainSeatInfoList) {
        for(TrainSeatInfo trainSeatInfo: trainSeatInfoList){
            Ticket ticket = new Ticket(trainSeatInfo);
            ticket_map.put(new Integer(ticket.getStatus().getID()), ticket);
        }
        return ticket_map;
    }

    public Ticket getTicket(int ticketID) {
        return ticket_map.get(new Integer(ticketID));
    }

    public void selectSeat(int ticketID, SeatInfo selectResult) {
        Ticket ticket = ticket_map.get(ticketID);
        ticket.selectSeat(selectResult);
    }

    public List<SeatInfo> confirm() {
        List<SeatInfo> seatInfoList = new ArrayList<>();
        ticket_map.forEach((id, ticket)->seatInfoList.add(ticket.confirm()));
        return seatInfoList;
    }
}
