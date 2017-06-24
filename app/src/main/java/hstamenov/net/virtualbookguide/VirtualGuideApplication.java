package hstamenov.net.virtualbookguide;

import android.app.Application;

/**
 * Created by hstamenov on 24.6.2017 г..
 */

public class VirtualGuideApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Handler.initPlaces();
    }

}
