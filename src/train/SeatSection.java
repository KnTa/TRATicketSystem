package train;

import station.Station;
import ticket.TicketStatus;

import java.util.List;
import java.util.Map;

class Section{
  Station departure_station;
  Station arrive_station;
  Integer ticket_ID;
}

public class SeatSection {
    private List<Section> sections;
    private  Map<Integer, TicketStatus> ticket_status;
}
