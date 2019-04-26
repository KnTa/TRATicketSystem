package train;

import SQL.SQLSeatSectionRecordDataControl;

public class SeatSectionRecordDataControlAdapter {
    public static SeatSectionRecordDataControl getReadSeatSectionRecord(){
        return new SQLSeatSectionRecordDataControl();
    }
}
