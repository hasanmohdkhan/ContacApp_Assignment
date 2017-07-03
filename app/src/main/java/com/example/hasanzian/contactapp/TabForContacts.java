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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by hasanZian on 30-06-2017.
 */

public class TabForContacts extends Fragment {

    ListView listView_Android_Contact;
    ArrayList<Android_contacts> arrayList_android_contact;
    final Adapter_for_Android_Contact adapter = new Adapter_for_Android_Contact(getContext(), arrayList_android_contact);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_contacts, container, false);
        listView_Android_Contact = (ListView) rootView.findViewById(R.id.listview_Android_contact);
        fp_get_android_contact();
        // options infragments
        setHasOptionsMenu(true);

        listView_Android_Contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Object ms=adapter.getItem(position);

                Toast.makeText(getActivity(), " Click ", Toast.LENGTH_SHORT).show();
            }
        });






        return rootView;


    }


    public void beginSearch(String query) {
        Log.e("QueryFragment", query);
        Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();
        adapter.getFilter().filter(query);
    }

    //

    void fp_get_android_contact() {
        //arrrayList
        final ArrayList<Android_contacts> arrayList_android_contact = new ArrayList<Android_contacts>();

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

                //adding name and pnone number from phone to your Android_conatact
                // then filling it in Arraylist
                arrayList_android_contact.add(android_contact);
                //====== Sorting Arraylist using Collection and camparstor
                Collections.sort(arrayList_android_contact, new Comparator<Android_contacts>() {
                    @Override
                    public int compare(Android_contacts o1, Android_contacts o2) {
                        return o1.android_contact_name.compareToIgnoreCase(o2.android_contact_name);
                    }


                });


            }
            //===== End of Sorting Arraylist using Collection and camparstor Block ====///

            //Adapter here
            final Adapter_for_Android_Contact adapter = new Adapter_for_Android_Contact(getContext(), arrayList_android_contact);
            listView_Android_Contact.setAdapter(adapter);


        }


    }




}
