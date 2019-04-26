package train;

import SQL.SQLTrainFormationDataControl;

public class TrainFormationDataControlAdapter {
    public static TrainFormationDataControl getReadTrainFormation(){
        return new SQLTrainFormationDataControl();
    }
}
