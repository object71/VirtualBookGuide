package net.hstamenov.virtualbookguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import net.hstamenov.virtualbookguide.models.Place;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    private static PlaceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView)findViewById(R.id.list);

        adapter = new PlaceAdapter(Handler.database.getAllPlaces(), getApplicationContext());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Place place = (Place)parent.getItemAtPosition(position);
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("Place", place.id);
        startActivity(intent);
    }

}
