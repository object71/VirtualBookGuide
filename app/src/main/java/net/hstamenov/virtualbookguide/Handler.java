package net.hstamenov.virtualbookguide;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.hstamenov.virtualbookguide.models.Place;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * Created by hstamenov on 24.6.2017 г..
 */

public class Handler {

    public static PlacesDatabase database = null;
    //public static ArrayList<Place> places = new ArrayList<>();

    public static Place getPlaceByName(String name) {
        List<Place> places = database.getAllPlaces();
        for(int i = 0; i < places.size(); i++){
            if(places.get(i).name.equalsIgnoreCase(name)) {
                return places.get(i);
            }
        }
        return null;
    }

    public static void parse(InputStream is) {

        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;

        Place place = null;
        String text = null;

        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("place")) {
                            // create a new instance of employee
                            place = new Place();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("place")) {
                            database.addPlace(place);
                        } else if (tagname.equalsIgnoreCase("name")) {
                            place.name = text;
                        } else if (tagname.equalsIgnoreCase("openHours")) {
                            place.openHours = text;
                        } else if (tagname.equalsIgnoreCase("address")) {
                            place.address = text;
                        } else if (tagname.equalsIgnoreCase("information")) {
                            place.information = text;
                        } else if (tagname.equalsIgnoreCase("position")) {
                            place.position = new LatLng(Float.parseFloat(text.split(",")[0]), Float.parseFloat(text.split(",")[1]));
                        }


                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
