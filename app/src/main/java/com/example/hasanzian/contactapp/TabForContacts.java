package com.example.hasanzian.contactapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by hasanZian on 30-06-2017.
 */

public class TabForContacts extends Fragment{

    ListView listView_Android_Contact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_contacts, container, false);
        listView_Android_Contact = (ListView) rootView.findViewById(R.id.listview_Android_contact);
        fp_get_android_contact();



        return rootView;


    }

    private void fp_get_android_contact() {
        //arrrayList
        ArrayList<Android_contacts> arrayList_android_contact = new ArrayList<Android_contacts>();
        //All from phone book load Contact
        Cursor cursor_android_contact = null;
        ContentResolver contentResolver = getActivity().getContentResolver();

        try {
            cursor_android_contact = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        } catch (Exception ex) {
            Log.e("", ex.getMessage());
        }
        // load contact Ends

        //Checking if hasContacts
        if (cursor_android_contact.getCount() > 0) {
            //when there is contact is present
            //looping all contacts
            while (cursor_android_contact.moveToNext()) {
                //init ---> strings
                Android_contacts android_contact = new Android_contacts();
                String contact_id = cursor_android_contact.getString(cursor_android_contact.getColumnIndex(ContactsContract.Contacts._ID));
                String contact_display_name = cursor_android_contact.getString(cursor_android_contact.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                //set data to pojo and then to contact_display_name
                android_contact.android_contact_name = contact_display_name;

                //set phone number here
                int hasPhoneNumber = Integer.parseInt(cursor_android_contact.getString(cursor_android_contact.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {

                    Cursor phoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                            , null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= ?", new String[]{contact_id}, null
                    );
                    //loading all conatct numbers

                    while (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        android_contact.android_contact_phone_no = phoneNumber;


                    }

                    phoneCursor.close();


                }

                arrayList_android_contact.add(android_contact);

            }

            //Adapter here
            Adapter_for_Android_Contact adapter = new Adapter_for_Android_Contact(getContext(), arrayList_android_contact);
            listView_Android_Contact.setAdapter(adapter);


        }


    }










}
