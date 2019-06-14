package station;

public class Station {
    private String name_TCN;
    private String name_EN;
    private int ID;

    private static StationDataControl stationDataControl;

    static {
        stationDataControl = StationDataControlFactory.getReadStation();
    }

    public Station(int id, String name_EN, String name_TCN){
        this.ID = id;
        this.name_EN = name_EN;
        this.name_TCN = name_TCN;
    }

    public int getID() {
        return ID;
    }

    public String getName_EN() {
        return name_EN;
    }

    public String getName_TCN() {
        return name_TCN;
    }

    public boolean compare(Station station){
        return this.ID == station.getID();
    }

    public String toString(){
        return name_TCN;
    }
}
