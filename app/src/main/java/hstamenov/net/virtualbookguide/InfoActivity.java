package hstamenov.net.virtualbookguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import hstamenov.net.virtualbookguide.models.Place;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Place place = Handler.places.get(getIntent().getExtras().getInt("Place"));

        TextView name = (TextView)this.findViewById(R.id.name);
        TextView content = (TextView)this.findViewById(R.id.content);

        name.setText(place.name);
        content.setText(place.information);
    }
}
