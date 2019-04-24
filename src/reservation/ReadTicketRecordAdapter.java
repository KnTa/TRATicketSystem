package reservation;

import SQL.SQLReadTicketRecord;

public class ReadTicketRecordAdapter {
    public static ReadTicketRecord getReadTicketRecord(){
        return new SQLReadTicketRecord();
    }
}
