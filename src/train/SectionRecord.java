package train;

public class SectionRecord{
    int departure_station;
    int arrive_station;
    int ticket_id;

    public SectionRecord(int departure_station, int arrive_station, int ticket_id){
        this.departure_station=departure_station;
        this.arrive_station=arrive_station;
        this.ticket_id=ticket_id;
    }

    int getDeparture_station(){
        return departure_station;
    }
    int getArrive_station(){
        return arrive_station;
    }
    int getTicket_id(){
        return ticket_id;
    }
}