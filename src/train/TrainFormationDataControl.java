package train;

import java.util.Date;
import java.util.List;

public interface TrainFormationDataControl {
    public List<Car> getFormation(int train_id, Date date);
}