package reservation;

import train.SeatInfo;
import train.SeatSectionRecordDataControl;
import train.SeatSectionRecordDataControlAdapter;

import java.util.List;

public class Ticket {
    private TicketStatus status;
    private TicketInfo ticket_info;

    private ReadTicketRecord readTicketRecord = ReadTicketRecordAdapter.getReadTicketRecord();
    private SeatSectionRecordDataControl seatSectionRecordDataControl = SeatSectionRecordDataControlAdapter.getReadSeatSectionRecord();

    public Ticket(TrainSeatInfo trainSeatInfo){
        status = new TicketStatus(ReadTicketRecordAdapter.getReadTicketRecord().getMaxId()+1);
        this.ticket_info=new TicketInfo(trainSeatInfo);
        readTicketRecord.setTicketRecord(getStatus().getID()
                , getTicketInfo().getTrain_seat_info().getTrain_id()
                , getTicketInfo().getTrain_seat_info().getDate()
                , getTicketInfo().getTrain_seat_info().getSeat_info().getCar_id()
                , getTicketInfo().getTrain_seat_info().getSeat_info().getSeat_id()
                , getTicketInfo().getDeparture().getID()
                , getTicketInfo().getArrive().getID()
                , getStatus().getStatus());

        seatSectionRecordDataControl.setSectionRecord(
                getTicketInfo().getTrain_seat_info().getTrain_id()
                , getTicketInfo().getTrain_seat_info().getDate()
                , getTicketInfo().getTrain_seat_info().getSeat_info().getCar_id()
                , getTicketInfo().getTrain_seat_info().getSeat_info().getSeat_id()
                , getTicketInfo().getDeparture().getID()
                , getTicketInfo().getArrive().getID()
                , getStatus().getID());
    }
    public Ticket(TicketInfo ticket_info, TicketStatus ticketStatus){
        status=ticketStatus;
        this.ticket_info=ticket_info;
    }

    public TicketInfo getTicketInfo()
    {
        return ticket_info;
    }
    public TicketStatus getStatus() { return status; }

    public void selectSeat(SeatInfo selectResult) {
        readTicketRecord.updateTicketRecord(status.getID(), selectResult.getCar_id(), selectResult.getSeat_id());
        seatSectionRecordDataControl.updateSectionRecord(selectResult.getCar_id(), selectResult.getSeat_id(),status.getID());
        ticket_info.setSeatInfo(selectResult);
    }

    SeatInfo confirm() {
        status.confirm();
        return ticket_info.getTrain_seat_info().getSeat_info();
    }
}
