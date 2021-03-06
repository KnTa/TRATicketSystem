package reservation;

public class TicketStatus {
    private int ID;
    private int status;

    private ReadTicketRecord readTicketRecord = ReadTicketRecordFactory.getReadTicketRecord();

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

    public String getStatusString(){
        switch (status){
            case STATUS_CONFIRM:
                return "確定";
            case STATUS_RESERVING:
                return "未確定";
        }
        return "";
    }

    void confirm(){
        readTicketRecord.updateTicketStatus(getID(), this.STATUS_CONFIRM);
        status = STATUS_CONFIRM;
    }
}
