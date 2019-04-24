package train;

import SQL.SQLSeatSectionRecord;

public class ReadSeatSectionRecordAdapter {
    public static ReadSeatSectionRecord getReadSeatSectionRecord(){
        return new SQLSeatSectionRecord();
    }
}
