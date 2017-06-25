package net.hstamenov.virtualbookguide;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.gms.maps.model.LatLng;

import net.hstamenov.virtualbookguide.models.Place;

public class PlacesDatabase extends SQLiteOpenHelper {

    // All Static variables
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "virtualbookguide";

    private static final String TABLE_PLACES = "places";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_OPEN_HOURS = "openHours";
    private static final String KEY_INFORMATION = "information";
    private static final String KEY_POSITION = "position";
    private static final String KEY_VISITED = "visited";



    public PlacesDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PLACES_TABLE = "CREATE TABLE " + TABLE_PLACES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_OPEN_HOURS + " TEXT,"
                + KEY_INFORMATION + " TEXT,"
                + KEY_POSITION + " TEXT,"
                + KEY_VISITED + " INTEGER,"
                + KEY_ADDRESS + " TEXT" + ")";
        db.execSQL(CREATE_PLACES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACES);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    void addPlace(Place place) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, place.name);
        values.put(KEY_OPEN_HOURS, place.openHours);
        values.put(KEY_INFORMATION, place.information);
        values.put(KEY_POSITION, place.position.latitude + "," + place.position.longitude);
        values.put(KEY_VISITED, place.visited ? 1 : 0);
        values.put(KEY_ADDRESS, place.address);

        db.insert(TABLE_PLACES, null, values);
        db.close();
    }

    Place getPlace(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLACES, new String[] { KEY_ID,
                        KEY_NAME, KEY_OPEN_HOURS, KEY_INFORMATION, KEY_POSITION, KEY_VISITED, KEY_ADDRESS }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Place place = new Place();

        place.id = Integer.parseInt(cursor.getString(0));
        place.name = cursor.getString(1);
        place.openHours = cursor.getString(2);
        place.information = cursor.getString(3);
        String position = cursor.getString(4);
        place.position = new LatLng(Float.parseFloat(position.split(",")[0]), Float.parseFloat(position.split(",")[1]));
        place.visited = Integer.parseInt(cursor.getString(5)) == 0 ? false : true;
        place.address = cursor.getString(6);

        cursor.close();

        return place;
    }

    public List<Place> getAllPlaces() {
        List<Place> placesList = new ArrayList<Place>();
        String selectQuery = "SELECT  * FROM " + TABLE_PLACES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Place place = new Place();
                place.id = cursor.getInt(0);
                place.name = cursor.getString(1);
                place.openHours = cursor.getString(2);
                place.information = cursor.getString(3);
                place.position = new LatLng(Float.parseFloat(cursor.getString(4).split(",")[0]), Float.parseFloat(cursor.getString(4).split(",")[1]));
                place.visited = cursor.getInt(5) == 0 ? false : true;
                place.address = cursor.getString(6);

                placesList.add(place);
            } while (cursor.moveToNext());
        }

        return placesList;
    }

    public int updatePlace(Place place) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, place.name);
        values.put(KEY_OPEN_HOURS, place.openHours);
        values.put(KEY_INFORMATION, place.information);
        values.put(KEY_POSITION, place.position.latitude + "," + place.position.longitude);
        values.put(KEY_VISITED, place.visited ? 1 : 0);
        values.put(KEY_ADDRESS, place.address);

        return db.update(TABLE_PLACES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(place.id) });
    }

    public void deletePlace(Place contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLACES, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.id) });
        db.close();
    }

    public int getPlacesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PLACES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

}