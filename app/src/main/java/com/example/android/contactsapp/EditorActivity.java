
package com.example.android.contactsapp;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.contactsapp.data.Contact;
import com.example.android.contactsapp.data.ContactDbHelper;

import static com.example.android.contactsapp.data.Contact.ContactEntry;

public class EditorActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mNickname;
    private EditText mMobile;
    private EditText mPhone;
    private EditText mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mName = (EditText)findViewById(R.id.edit_name);
        mNickname = (EditText) findViewById(R.id.edit_nickname);
        mMobile = (EditText)findViewById(R.id.edit_mobile);
        mPhone = (EditText) findViewById(R.id.edit_phone);
        mEmail = (EditText)findViewById(R.id.edit_email);

    }

    private void insertContact()
    {
        String nameString = mName.getText().toString().trim();
        String nicknameString = mNickname.getText().toString().trim();
        String emailString = mEmail.getText().toString().trim();
        String mobileString = mMobile.getText().toString().trim();
        String phoneString = mPhone.getText().toString().trim();

        ContentValues values = new ContentValues();

        values.put(ContactEntry.COLUMN_CONTACT_NAME, nameString);
        values.put(ContactEntry.COLUMN_CONTACT_NICKNAME, nicknameString);
        values.put(ContactEntry.COLUMN_CONTACT_EMAIL, emailString);
        values.put(ContactEntry.COLUMN_CONTACT_MOBILE, mobileString);
        values.put(ContactEntry.COLUMN_CONTACT_PHONE, phoneString);

        Uri newUri = getContentResolver().insert(ContactEntry.CONTENT_URI,values);

        if (newUri == null) {
            Toast.makeText(this, "Insert Contact Failed !!",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Contact Added Successfully !!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_save :
                insertContact();
                finish();
                return true;

            case android.R.id.home:

                NavUtils.navigateUpFromSameTask(this);
                return true;


        }
        return super.onOptionsItemSelected(item);
        }
}
