
package com.example.android.contactsapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.net.wifi.aware.PublishConfig;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.example.android.contactsapp.data.Contact.ContactEntry;

/**
 * Created by LENOVO on 27-05-2018.
 */

public class ContactProvider extends ContentProvider {

    public static final int CONTACTS = 100;
    public static final int CONTACTS_ID = 101;

    private ContactDbHelper mDbHelper;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        sUriMatcher.addURI(Contact.CONTENT_AUTHORITY,Contact.PATH_CONTACTS,CONTACTS);
        sUriMatcher.addURI(Contact.CONTENT_AUTHORITY,Contact.PATH_CONTACTS + "/#", CONTACTS_ID);
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new ContactDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);

        switch (match)
        {
            case CONTACTS :
                cursor = database.query(ContactEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CONTACTS_ID :
                selection = ContactEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(ContactEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI : " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }


    @Override
    public int update(Uri uri,ContentValues contentValues,String s,String[] strings) {
        return 0;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        int match = sUriMatcher.match(uri);

        switch (match)
        {
            case CONTACTS :
                return insertContact(uri,contentValues);
            default:
                throw new IllegalArgumentException("Insetion not Supported for : " + uri);
        }
    }

    private Uri insertContact(Uri uri, ContentValues contentValues){
        String name = contentValues.getAsString(ContactEntry.COLUMN_CONTACT_NAME);
        if (name == null) {
            throw new IllegalArgumentException("Person requires a Name !!");
        }
        String nickname = contentValues.getAsString(ContactEntry.COLUMN_CONTACT_NICKNAME);
        String email = contentValues.getAsString(ContactEntry.COLUMN_CONTACT_EMAIL);
        String mobile = contentValues.getAsString(ContactEntry.COLUMN_CONTACT_MOBILE);
        String phone = contentValues.getAsString(ContactEntry.COLUMN_CONTACT_PHONE);

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        long id=database.insert(ContactEntry.TABLE_NAME,null ,contentValues);

        getContext().getContentResolver().notifyChange(uri,null);
        return  ContentUris.withAppendedId(uri,id);
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }
}
