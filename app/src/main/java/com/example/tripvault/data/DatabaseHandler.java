package com.example.tripvault.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tripvault.Util.createUserUtil;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, createUserUtil.DATABASE_NAME, null, createUserUtil.DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + createUserUtil.TABLE_NAME + "(" + createUserUtil.KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                createUserUtil.KEY_USERNAME + " TEXT, " + createUserUtil.KEY_USER_PASSWD + " TEXT," + createUserUtil.KEY_EMAIL + " TEXT, " + createUserUtil.KEY_CITY + " TEXT, " + createUserUtil.KEY_PHONE + " TEXT " + ")";

        db.execSQL(CREATE_CONTACT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf("DROP TABLE IF EXISTS");
        db.execSQL(DROP_TABLE, new String[]{createUserUtil.DATABASE_NAME});
        onCreate(db);

    }


    // takes in a user of user class
    public void addContact(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(createUserUtil.KEY_USERNAME, user.getUserName());
        contentValues.put(createUserUtil.KEY_USER_PASSWD, user.getPassword());
        contentValues.put(createUserUtil.KEY_EMAIL, user.getEmailAddress());
        contentValues.put(createUserUtil.KEY_CITY, user.getCity());
        contentValues.put(createUserUtil.KEY_PHONE, user.getPhone_num());

        db.insert(createUserUtil.TABLE_NAME, null, contentValues);
        Log.i("Added", "addContact: Item Added");
        db.close();
    }

    public void deleteContact(User id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(createUserUtil.TABLE_NAME, createUserUtil.KEY_USER_ID + "=?", new String[]{
                String.valueOf(id.getUserId())});

        Log.i("Added", "addContact: Item Added");
        db.close();
    }

    public void upDateContact(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        user.setUserName(user.getUserName());
        user.setPassword(user.getPassword());
        user.setEmailAddress(user.getEmailAddress());
        user.setCity(user.getCity());

        contentValues.put(createUserUtil.KEY_USERNAME, user.getUserName());
        contentValues.put(createUserUtil.KEY_EMAIL, user.getEmailAddress());
        contentValues.put(createUserUtil.KEY_CITY, user.getCity());
        contentValues.put(createUserUtil.KEY_PHONE, user.getPhone_num());
        db.update(createUserUtil.TABLE_NAME, contentValues, createUserUtil.KEY_USER_ID + "=?", new String[]{
                String.valueOf(user.getUserId())});
        Log.i("Added", "addContact: Updated Added");
        db.close();

    }

    public User getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(createUserUtil.TABLE_NAME, new String[]{createUserUtil.KEY_USER_ID, createUserUtil.KEY_USERNAME,
                        createUserUtil.KEY_USER_PASSWD, createUserUtil.KEY_EMAIL, createUserUtil.KEY_CITY, createUserUtil.KEY_PHONE},
                createUserUtil.KEY_USER_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        User contact = new User();
        if (cursor != null) {
            cursor.moveToFirst();
            contact.setUserId(Integer.parseInt(cursor.getString(0)));
            contact.setUserName(cursor.getString(1));
            contact.setPassword(cursor.getString(2));
            contact.setEmailAddress(cursor.getString(3));
            contact.setCity(cursor.getString(4));
            contact.setPhone_num(cursor.getString(5));
        }
        return contact;
    }


    public User getLogInInfo(String userName) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(createUserUtil.TABLE_NAME, new String[]{createUserUtil.KEY_USER_ID, createUserUtil.KEY_USERNAME, createUserUtil.KEY_USER_PASSWD, createUserUtil.KEY_EMAIL},
                createUserUtil.KEY_USERNAME + "=?", new String[]{String.valueOf(userName)}, null, null, null);
        User contact = new User();

		if (cursor != null) {
			cursor.moveToFirst();
			contact.setUserName(cursor.getString(1));
			contact.setPassword(cursor.getString(2));
			contact.setEmailAddress(cursor.getString(3));
		}
		return contact;

    }


    public List<User> getAllContact() {
        List<User> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + createUserUtil.TABLE_NAME;
        try (Cursor cursor = db.rawQuery(selectAll, null)) {
            if (cursor.moveToNext()) {
                do {
                    User user = new User();
                    user.setUserId(Integer.parseInt(cursor.getString(0)));
                    user.setUserName(cursor.getString(1));
                    user.setPassword(cursor.getString(2));
                    user.setEmailAddress(cursor.getString(3));
                    user.setCity(cursor.getString(4));
                    user.setPhone_num(cursor.getString(5));
                    contactList.add(user);

                } while (cursor.moveToNext());
            }
        }
        return contactList;
    }

    public int getCount() {
        String select = "SELECT * FROM " + createUserUtil.TABLE_NAME + "";
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.rawQuery(select, null)) {
            return cursor.getCount();
        }
    }

    public void deleteAll() {
        String delete = "DELETE  FROM " + createUserUtil.TABLE_NAME + "";
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(createUserUtil.TABLE_NAME, null, null);

    }

}
