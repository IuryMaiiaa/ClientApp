package HuntApi.Model;

/**
 * Created by Iury on 1/2/2016.
 */
public class CordenadaGeografica {

    private int ID;
    private double lat;
    private double lon;


    public CordenadaGeografica() {

    }
    public CordenadaGeografica(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }


    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }


    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLon() {
        return lon;
    }
    public void setLon(double lon) {
        this.lon = lon;
    }

    public String toString() {
        return lat + " " + lon;
    }

}
