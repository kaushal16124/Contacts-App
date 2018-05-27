package com.example.android.contactsapp.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by LENOVO on 27-05-2018.
 */

public final class Contact {

    private Contact(){

    }
    public static final String CONTENT_AUTHORITY = "com.example.android.contactsapp";

    public static final String PATH_CONTACTS = "contacts";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class ContactEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_CONTACTS);


        public final static String TABLE_NAME = "contacts";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_CONTACT_NAME = "name";

        public final static String COLUMN_CONTACT_NICKNAME = "nickname";

        public final static String COLUMN_CONTACT_EMAIL = "email";

        public final static String COLUMN_CONTACT_MOBILE = "mobile";

        public final static String COLUMN_CONTACT_PHONE = "phone";


    }
}
