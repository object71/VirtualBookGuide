package hstamenov.net.virtualbookguide;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import hstamenov.net.virtualbookguide.models.Place;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMarkerClickListener(this);

        for(Place place: Handler.places) {
            Marker marker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.map)).draggable(false).position(place.position).title(place.name).anchor(0.5f, 0.5f));

        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(42.2418426,25.033178)));

        try {
            mMap.setMyLocationEnabled(true);
        } catch (SecurityException exception) {

        }

        mMap.moveCamera(CameraUpdateFactory.zoomTo(6f));
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        Place place = Handler.getPlaceByName(marker.getTitle());
        int index = Handler.places.indexOf(place);

        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("Place", index);
        startActivity(intent);

        return true;
    }
}
