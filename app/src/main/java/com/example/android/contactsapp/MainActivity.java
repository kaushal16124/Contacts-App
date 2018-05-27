
package com.example.android.contactsapp;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.contactsapp.data.Contact;
import com.example.android.contactsapp.data.ContactCursorAdapter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int CONTACT_LOADER = 0;

    ContactCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        ListView contactListView = (ListView)findViewById(R.id.list);
        mCursorAdapter = new ContactCursorAdapter(this,null);
        contactListView.setAdapter(mCursorAdapter);
        getLoaderManager().initLoader(CONTACT_LOADER, null,this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {Contact.ContactEntry._ID, Contact.ContactEntry.COLUMN_CONTACT_NAME, Contact.ContactEntry.COLUMN_CONTACT_MOBILE};
        return new CursorLoader(this, Contact.ContactEntry.CONTENT_URI,projection,null,null,null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mCursorAdapter.swapCursor(null);
    }
}
