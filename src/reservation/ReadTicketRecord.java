package reservation;

import java.util.Date;
import java.util.List;

public interface ReadTicketRecord {
    TicketInfo getTicketRecord(int ticket_id);
    void setTicketRecord(int ticket_id, int train_id, Date date, int car_id, int seat_id, int departure, int arrive, int status);
    void updateTicketRecord(int ticket_id, int car_id, int seat_id);
    void updateTicketStatus(int ticket_id, int status);
    void deleteTicketRecord(int ticket_id);
    Date getTicketReserveDate(int ticket_id);
    int getTicketStatus(int ticket_id);
    int getMaxId();
}

