package com.example.hasanzian.contactapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasanZian on 01-07-2017.
 */

class Adapter_for_Android_Contact extends BaseAdapter {
    //Variables
    Context mContext;
    List<Android_contacts> mList_Android_contacts;
    //Variables Ends

    public Adapter_for_Android_Contact(Context mContext, ArrayList<Android_contacts> mContact) {
        this.mContext = mContext;
        this.mList_Android_contacts = mContact;
    }


    @Override
    public int getCount() {
        return mList_Android_contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return mList_Android_contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.contactlist_android_item, null);
        //get controls
        TextView textview_contact_Name = (TextView) view.findViewById(R.id.textview_android_contact_name);
        TextView textview_contact_phone_no = (TextView) view.findViewById(R.id.textview_android_contact_ph_number);
        //get controls

        //setting textview

        textview_contact_Name.setText(mList_Android_contacts.get(position).android_contact_name);
        textview_contact_phone_no.setText(mList_Android_contacts.get(position).android_contact_phone_no);


        return view;
    }
}
