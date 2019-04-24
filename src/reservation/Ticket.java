package reservation;

public class Ticket {
    private TicketStatus status;
    private TicketInfo ticket_info;

    public Ticket(TrainSeatInfo trainSeatInfo){}
    public Ticket(TicketInfo ticket_info, TicketStatus ticketStatus){
        status=ticketStatus;
        this.ticket_info=ticket_info;
    }

    public TicketInfo getTicketInfo()
    {
        return  ticket_info;
    }
}
