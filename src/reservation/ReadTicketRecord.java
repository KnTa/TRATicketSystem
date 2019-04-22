package reservation;

import java.util.Date;
import java.util.List;

public interface ReadTicketRecord {
    //List<Ticket> getTicketRecord(int train_time, Date date, int car_id, int seat_id);
    void setTicketRecord(int ticket_id, Date reservation_date, int train_id, Date date, int car_id, int seat_id, int departure, int arrive, int status);
    void updateTicketRecord(int ticket_id, int car_id, int seat_id, int status);
    void deleteTicketRecord(int ticket_id);
    Date getTicketReserveDate(int ticket_id);
}

