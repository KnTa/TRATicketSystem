package train;

import java.util.Date;
import java.util.List;

public interface ReadSeatSectionRecord {
        List<SectionRecord> getSectionRecord(int train_time, Date date, int car_id, int seat_id);
        void setSectionRecord(int train_time, Date date, int car_id, int seat_id, int departure, int arrive, int ticket);
        void updateSectionRecord(int car_id, int seat_id, int ticket);
        void deleteSectionRecord(int ticket);
}
