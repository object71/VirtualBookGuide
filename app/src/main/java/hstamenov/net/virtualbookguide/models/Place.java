package hstamenov.net.virtualbookguide.models;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by hstamenov on 24.6.2017 г..
 */

public class Place {
    public String name;
    public String address;
    public String openHours;
    public String information;
    public LatLng position;

    public Place(String name, String address, String openHours, LatLng position) {
        this.name = name;
        this.address = address;
        this.openHours = openHours;
        this.position = position;
    }
}
