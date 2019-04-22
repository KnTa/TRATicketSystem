package train;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Car {
    private int ID;
    private List<Seat> seats;

    public Car(int train_time, Date date, int id, int seat_number){
        seats=new ArrayList<Seat>();
        for(int count=1;count<=seat_number;count++){
            seats.add(new Seat(train_time, date, id, count));
        }
    }}
