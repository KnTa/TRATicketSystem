package train;

import reservation.ReadTicketRecord;
import reservation.ReadTicketRecordAdapter;
import schedual.TrainStationSchedule;
import schedual.TrainTable;
import schedual.TrainTime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class SeatSectionRecord {
  private List<SectionRecord> sectionRecordList;
  private SeatSectionRecordDataControl seatSectionRecordDataControl = SeatSectionRecordDataControlAdapter.getReadSeatSectionRecord();
  private int car_id;
  private int seat_id;
  private int train_time;
  private Date date;

  SeatSectionRecord(int train_time, Date date, int car_id, int seat_id){
    this.car_id = car_id;
    this.seat_id = seat_id;
    this.date=date;
    this.train_time=train_time;
    sectionRecordList = seatSectionRecordDataControl.getSectionRecord(train_time, date, car_id, seat_id);
  }

  void updateSeatSectionRecord(){
    checkReserveOverTime();
    sectionRecordList = seatSectionRecordDataControl.getSectionRecord(train_time, date, car_id, seat_id);
  }
  void checkReserveOverTime(){
    ReadTicketRecord readTicketRecord = ReadTicketRecordAdapter.getReadTicketRecord();
    for(SectionRecord sectionRecord:sectionRecordList){
      Date reserveDate = readTicketRecord.getTicketReserveDate(sectionRecord.ticket_id);
      LocalDateTime localDateTime = LocalDateTime.now();
      Date now = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
      int status = readTicketRecord.getTicketStatus(sectionRecord.ticket_id);
      if(now.getTime()-reserveDate.getTime()>60000 && status==0){
        readTicketRecord.deleteTicketRecord(sectionRecord.ticket_id);
        seatSectionRecordDataControl.deleteSectionRecord(sectionRecord.ticket_id);
      }
    }
  }

  public boolean checkSection(int departure, int arrive) throws Exception {
    updateSeatSectionRecord();
    TrainTime trainTime = TrainTable.getTrainTime(train_time, date);

    final int NONE_ENTER_SECTION = 0;
    final int SEATING_SECTION=1;
    final int LEAVE_SECTION=2;

    //確定已定位之區間是否有重疊
    for(SectionRecord sectionRecord:sectionRecordList){
      int section_flag_record = NONE_ENTER_SECTION;
      int section_flag_new = NONE_ENTER_SECTION;
      int now_station = trainTime.getStart_station().getID();
      while(true){
        //設置成離座區間
        if(arrive == now_station){section_flag_new=LEAVE_SECTION;}
        if(sectionRecord.getArrive_station() == now_station){section_flag_record=LEAVE_SECTION;}

        if((section_flag_new==LEAVE_SECTION && section_flag_record==LEAVE_SECTION)
                ||(section_flag_new==LEAVE_SECTION && section_flag_record==NONE_ENTER_SECTION)
                ||(section_flag_new==NONE_ENTER_SECTION && section_flag_record==LEAVE_SECTION)){
          break;
        }

        //設置乘坐區間
        if(departure == now_station){section_flag_new=SEATING_SECTION;}
        if(sectionRecord.getDeparture_station() == now_station){section_flag_record=SEATING_SECTION;}
        if(section_flag_new==SEATING_SECTION && section_flag_record==SEATING_SECTION){return false;}

        now_station = trainTime.getNextStation(now_station).getID(); //下一站
        if(now_station==0){throw new Exception("SeatSectionRecord checkSection() error"
                + " departure:" + departure
                + " arrive:" + arrive
                + " train time:" + train_time
                + " date:"+ date.toString());}
      }
    }
    return true;
  }
}


