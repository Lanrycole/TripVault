package com.example.tripvault.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tripvault.Util.createTripUtil;

import java.util.ArrayList;
import java.util.List;

public class TripDbHandler extends SQLiteOpenHelper {
    public TripDbHandler(Context context) {
        super(context, createTripUtil.DATABASE_NAME, null, createTripUtil.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TRIP_TABLE = "CREATE TABLE " + createTripUtil.TABLE_NAME + "(" + createTripUtil.TRIP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + " TEXT, "
                + createTripUtil.USERNAME + " TEXT, " + createTripUtil.TRIP_TITLE + " TEXT, " + createTripUtil.TRIP_LOCATION + " TEXT, "
                + createTripUtil.TRIP_ACCOMODATION + " TEXT," + createTripUtil.TRIP_NOTES + " TEXT, " + createTripUtil.TRIP_DATE + " TEXT, " +
                createTripUtil.TRIP_PICTURES + " TEXT ," + createTripUtil.TRIP_FILES + " TEXT " + ")";
        db.execSQL(CREATE_TRIP_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf("DROP TABLE IF EXISTS");
        db.execSQL(DROP_TABLE, new String[]{createTripUtil.DATABASE_NAME});
        onCreate(db);
    }

    // takes in a user of user class
    public void addTript(userTrip usertrip) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(createTripUtil.TRIP_ID, usertrip.getTripId());
        contentValues.put(createTripUtil.USERNAME, usertrip.getUsername());
        contentValues.put(createTripUtil.TRIP_TITLE, usertrip.getTittle());
        contentValues.put(createTripUtil.TRIP_LOCATION, usertrip.getLocation());
        contentValues.put(createTripUtil.TRIP_ACCOMODATION, usertrip.getAccomodation());
        contentValues.put(createTripUtil.TRIP_NOTES, usertrip.getNotes());
        contentValues.put(createTripUtil.TRIP_DATE, usertrip.getDate().toString());

        db.insert(createTripUtil.TABLE_NAME, null, contentValues);
        Log.i("Added", "Add Trip: Trip Added");
        db.close();
    }

    public void deleteTrip(userTrip usertrip) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(createTripUtil.TABLE_NAME, createTripUtil.TRIP_ID + "=?", new String[]{
                String.valueOf(usertrip.getTripId())});

        Log.i("Added", ": Trip Deleted");
        db.close();
    }

    public void upDateContact(userTrip usertrip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        usertrip.setTripId(usertrip.getTripId());
        usertrip.setUsername(usertrip.getUsername());
        usertrip.setTittle(usertrip.getTittle());
        usertrip.setLocation(usertrip.getLocation());
        usertrip.setAccomodation(usertrip.getAccomodation());
        usertrip.setNotes(usertrip.getNotes());
        usertrip.setDate(usertrip.getDate());

        contentValues.put(createTripUtil.TRIP_ID, usertrip.getTripId());
        contentValues.put(createTripUtil.USERNAME, usertrip.getUsername());
        contentValues.put(createTripUtil.TRIP_TITLE, usertrip.getTittle());
        contentValues.put(createTripUtil.TRIP_LOCATION, usertrip.getLocation());
        contentValues.put(createTripUtil.TRIP_NOTES, usertrip.getNotes());
        contentValues.put(createTripUtil.TRIP_ACCOMODATION, usertrip.getAccomodation());
        contentValues.put(createTripUtil.TRIP_DATE, usertrip.getDate().toString());
        db.update(createTripUtil.TABLE_NAME, contentValues, usertrip.tripId + "=?", new String[]{
                String.valueOf(usertrip.getTittle())});
        Log.i("Added", "addContact: Updated Added");
        db.close();
    }
    public List<userTrip> getAllTrip() {
        List<userTrip> tripList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + createTripUtil.TABLE_NAME;
        try (Cursor cursor = db.rawQuery(selectAll, null)) {
            if (cursor.moveToNext()) {
                do {
                    userTrip usertrip = new userTrip();
                    usertrip.setTripId(cursor.getInt(0));
                    usertrip.setUsername(cursor.getString(1));
                    usertrip.setTittle(cursor.getString(2));
                    usertrip.setLocation(cursor.getString(3));
                    usertrip.setAccomodation(cursor.getString(4));
                    usertrip.setNotes(cursor.getString(5));
//                    usertrip.setDate(Integer.valueOf(cursor.getString(6)));
                    tripList.add(usertrip);
                } while (cursor.moveToNext());
            }
        }
        return tripList;
    }
    public int getCount() {
        String select = "SELECT * FROM " + createTripUtil.TABLE_NAME + "";
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.rawQuery(select, null)) {
            return cursor.getCount();
        }
    }
    public void deleteAll() {
        String delete = "DELETE  FROM " + createTripUtil.TABLE_NAME + "";
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(createTripUtil.TABLE_NAME, null, null);
    }
}

