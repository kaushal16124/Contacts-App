package com.example.android.contactsapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.contactsapp.data.Contact.ContactEntry;

/**
 * Created by LENOVO on 27-05-2018.
 */

public class ContactDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "contactdata.db";

    public static final int DATABASE_VERSION = 1;

    public ContactDbHelper(Context context) {

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + ContactEntry.TABLE_NAME + " (" +
                ContactEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ContactEntry.COLUMN_CONTACT_NAME +
                " TEXT NOT NULL, " + ContactEntry.COLUMN_CONTACT_NICKNAME + " TEXT NOT NULL, " +
                ContactEntry.COLUMN_CONTACT_EMAIL + " TEXT NOT NULL, " + ContactEntry.COLUMN_CONTACT_MOBILE +
                " TEXT NOT NULL, " + ContactEntry.COLUMN_CONTACT_PHONE + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
