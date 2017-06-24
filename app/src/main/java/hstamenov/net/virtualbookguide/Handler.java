package hstamenov.net.virtualbookguide;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import hstamenov.net.virtualbookguide.models.Place;

/**
 * Created by hstamenov on 24.6.2017 г..
 */

public class Handler {
    public static ArrayList<Place> places = new ArrayList<>();

    public static Place getPlaceByName(String name) {
        for(int i = 0; i < places.size(); i++){
            if(places.get(i).name.equalsIgnoreCase(name)) {
                return places.get(i);
            }
        }
        return null;
    }

    public static void initPlaces() {

        Place place = new Place("Sofia Center", "Center", "9:00 - 17:00", new LatLng(42.6976236,23.3215469));
        place.information = "Well the center of sofia";

        places.add(place);

    }
}
