package train;

import SQL.SQLTrainFormationDataControl;

public class TrainFormationDataControlFactory {
    public static TrainFormationDataControl getReadTrainFormation(){
        return new SQLTrainFormationDataControl();
    }
}
