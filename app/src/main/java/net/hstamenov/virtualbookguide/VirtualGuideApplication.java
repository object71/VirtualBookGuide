package net.hstamenov.virtualbookguide;

import android.app.Application;

import java.io.IOException;

/**
 * Created by hstamenov on 24.6.2017 г..
 */

public class VirtualGuideApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PlacesDatabase db = new PlacesDatabase(this);
        Handler.database = db;

        if(db.getPlacesCount() == 0) {
            try {
                Handler.parse(getAssets().open("national_places.xml"));
            } catch (IOException ex) {

            }
        }
    }

}
