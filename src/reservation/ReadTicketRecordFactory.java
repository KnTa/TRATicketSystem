package reservation;

import SQL.SQLTicketRecord;

public class ReadTicketRecordFactory {
    public static ReadTicketRecord getReadTicketRecord(){
        return new SQLTicketRecord();
    }
}
