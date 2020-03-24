package com.example.tripvault.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tripvault.Util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
	public DatabaseHandler(Context context) {
		super(context,Util.DATABASE_NAME,null,Util.DATABASE_VERSION);

	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" + Util.KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				Util.KEY_USERNAME  +" TEXT, " + Util.KEY_USER_PASSWD +" TEXT," + Util.KEY_EMAIL + " TEXT, " +Util.KEY_CITY + " TEXT, " + Util.KEY_PHONE + " TEXT " +")";

		db.execSQL(CREATE_CONTACT_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion) {
		String DROP_TABLE = String.valueOf("DROP TABLE IF EXISTS");
		db.execSQL(DROP_TABLE,new String[]{Util.DATABASE_NAME});
		onCreate(db);

	}


		// takes in a contact of Contact class
	public void addContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues contentValues = new ContentValues();
 		contentValues.put(Util.KEY_USERNAME, contact.getUserName());
		contentValues.put(Util.KEY_USER_PASSWD,contact.getPassword());
		contentValues.put(Util.KEY_EMAIL, contact.getEmailAddress());
		contentValues.put(Util.KEY_CITY,contact.getCity());
		contentValues.put(Util.KEY_PHONE,contact.getPhone_num());


		db.insert(Util.TABLE_NAME,null,contentValues);
		Log.i("Added","addContact: Item Added");
		db.close();
	}

	public void deleteContact(Contact id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(Util.TABLE_NAME,Util.KEY_USER_ID + "=?",new String[]{
				String.valueOf(id.getUserId())});

		Log.i("Added","addContact: Item Added");
		db.close();
	}
	public void upDateContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues contentValues = new ContentValues();
		contact.setUserName(contact.getUserName());
		contact.setPassword(contact.getPassword());
		contact.setEmailAddress(contact.getEmailAddress());
		contact.setCity(contact.getCity());

		contentValues.put(Util.KEY_USERNAME,contact.getUserName());
		contentValues.put(Util.KEY_EMAIL,contact.getEmailAddress());
		contentValues.put(Util.KEY_CITY,contact.getCity());
		contentValues.put(Util.KEY_PHONE,contact.getPhone_num());
		db.update(Util.TABLE_NAME, contentValues, Util.KEY_USER_ID + "=?",new String[]{
				String.valueOf(contact.getUserId())} );
		Log.i("Added","addContact: Updated Added");
		db.close();

	}
	public Contact getContact(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(Util.TABLE_NAME,new String[]{Util.KEY_USER_ID,Util.KEY_USERNAME, Util.KEY_USER_PASSWD, Util.KEY_EMAIL, Util.KEY_CITY, Util.KEY_PHONE},
				Util.KEY_USER_ID + "=?",new String[]{String.valueOf(id)},null,null,null);
		if (cursor != null)
			cursor.moveToFirst();
		Contact contact = new Contact();
		assert cursor != null;
		contact.setUserId(Integer.parseInt(cursor.getString(0)));
		contact.setUserName(cursor.getString(1));
		contact.setPassword(cursor.getString(2));
		contact.setCity(cursor.getString(3));
		contact.setEmailAddress(cursor.getString(4));
		contact.setPhone_num(cursor.getString(5));

		return contact;
	}
	public Contact getLogInInfo(String userName) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(Util.TABLE_NAME,new String[]{Util.KEY_USER_ID,Util.KEY_USERNAME, Util.KEY_USER_PASSWD},
				Util.KEY_USERNAME + "=?",new String[]{String.valueOf(userName)},null,null,null);
		Contact contact = new Contact();
		if (cursor != null && cursor.moveToFirst() ){
			cursor.moveToFirst();

			contact.setUserId(Integer.parseInt(cursor.getString(0)));
			contact.setUserName(cursor.getString(1));
			contact.setPassword(cursor.getString(2));
			cursor.close();
		}

		return contact;

	}


	public List<Contact> getAllContact() {
		List<Contact> contactList = new ArrayList<>();
		SQLiteDatabase db = this.getReadableDatabase();
		String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
		try (Cursor cursor = db.rawQuery(selectAll,null)) {
			if (cursor.moveToNext()) {
				do {
					Contact contact = new Contact();
					contact.setUserId(Integer.parseInt(cursor.getString(0)));
					contact.setUserName(cursor.getString(1));
					contact.setPassword(cursor.getString(2));
					contact.setEmailAddress(cursor.getString(3));
					contact.setCity(cursor.getString(4));
					contact.setPhone_num(cursor.getString(5));
					contactList.add(contact);

				} while (cursor.moveToNext());
			}
		}
		return contactList;
	}
	public int getCount(){
		String select = "SELECT * FROM "+ Util.TABLE_NAME +"";
		SQLiteDatabase db = this.getReadableDatabase();
		try (Cursor cursor = db.rawQuery(select,null)) {
			return cursor.getCount();
		}
	}

	public void deleteAll(){
		String delete = "DELETE  FROM "+ Util.TABLE_NAME +"";
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(Util.TABLE_NAME, null, null);

	}

}
