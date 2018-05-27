package com.example.android.contactsapp.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.contactsapp.R;

import static com.example.android.contactsapp.data.Contact.ContactEntry;

/**
 * Created by LENOVO on 27-05-2018.
 */

public class ContactCursorAdapter extends CursorAdapter {

    public ContactCursorAdapter(Context context, Cursor c){
        super(context,c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView nameTextView = (TextView)view.findViewById(R.id.name1);

        TextView numberTextView = (TextView)view.findViewById(R.id.number1);

        int nameColumnIndex = cursor.getColumnIndex(ContactEntry.COLUMN_CONTACT_NAME);

        int numberColumnIndex = cursor.getColumnIndex(ContactEntry.COLUMN_CONTACT_MOBILE);

        String cName = cursor.getString(nameColumnIndex);

        String cNumber = cursor.getString(numberColumnIndex);

        nameTextView.setText(cName);
        numberTextView.setText(cNumber);



    }
}
