package train;

import SQL.SQLSeatSectionRecordDataControl;

public class SeatSectionRecordDataControlFactory {
    public static SeatSectionRecordDataControl getReadSeatSectionRecord(){
        return new SQLSeatSectionRecordDataControl();
    }
}
