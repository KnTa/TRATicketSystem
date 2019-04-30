package reservation;

import train.SeatInfo;
import train.SeatSectionRecordDataControl;
import train.SeatSectionRecordDataControlAdapter;

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
        updateTicket(selectResult);
        ticket_info.setSeatInfo(selectResult);
    }

    protected void updateTicket(SeatInfo seatInfo){
        readTicketRecord.updateTicketRecord(status.getID(), seatInfo.getCar_id(), seatInfo.getSeat_id());
        seatSectionRecordDataControl.updateSectionRecord(seatInfo.getCar_id(), seatInfo.getSeat_id(),status.getID());
    }

    protected SeatInfo confirm() {
        status.confirm();
        return ticket_info.getTrain_seat_info().getSeat_info();
    }
}
