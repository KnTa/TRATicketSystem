package train;

import java.util.Date;
import java.util.List;

public class TrainFormation {
    private int train_id;
    private Date date;
    private List<Car> cars;

    public TrainFormation(int train_id, Date date){
        this.train_id = train_id;
        this.date = date;
        ReadTrainFormation readTrainFormation = ReadTrainFormationAdapter.getReadTrainFormation();
        cars = readTrainFormation.getFormation(this.train_id, this.date);
    }
}

