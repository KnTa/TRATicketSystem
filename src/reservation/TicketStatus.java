package reservation;

public class TicketStatus {
    private int ID;
    private int status;
    //private TicketInfo section;

    public final int STATUS_CONFIRM = 1;
    public final int STATUS_RESERVING = 0;

    public TicketStatus(int id){
        this.ID=id;
        status=STATUS_RESERVING;
    }

    public TicketStatus(int id, int status){
        this.ID=id;
        this.status=status;
    }

    public int getID() {
        return ID;
    }

    public int getStatus(){
        return status;
    }

    void confirm(){

    }
}
