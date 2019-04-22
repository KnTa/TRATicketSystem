package train;

import SQL.SQLTrainFormation;

public class ReadTrainFormationAdapter {
    public static ReadTrainFormation getReadTrainFormation(){
        return new SQLTrainFormation();
    }
}
