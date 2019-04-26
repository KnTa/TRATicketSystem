package reservation;

import SQL.SQLTicketRecord;

public class ReadTicketRecordAdapter {
    public static ReadTicketRecord getReadTicketRecord(){
        return new SQLTicketRecord();
    }
}
