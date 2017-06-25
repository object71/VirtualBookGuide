package net.hstamenov.virtualbookguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.hstamenov.virtualbookguide.models.Place;

public class InfoActivity extends AppCompatActivity {
    Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        place = Handler.database.getPlace(getIntent().getExtras().getInt("Place"));

        TextView name = (TextView)this.findViewById(R.id.name);
        TextView openHours = (TextView)this.findViewById(R.id.openHours);
        TextView content = (TextView)this.findViewById(R.id.content);

        Button mark = (Button)this.findViewById(R.id.mark);
        if (!place.visited) {
            mark.setVisibility(View.VISIBLE);
        } else {
            mark.setVisibility(View.INVISIBLE);
        }

        name.setText(place.name);
        openHours.setText(place.openHours);
        content.setText(place.information);
    }

    public void markAsVisited(View view) {
        Button mark = (Button)this.findViewById(R.id.mark);
        mark.setVisibility(View.INVISIBLE);

        place.visited = true;
        Handler.database.updatePlace(place);
    }
}
