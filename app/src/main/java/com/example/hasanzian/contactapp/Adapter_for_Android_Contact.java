package com.example.hasanzian.contactapp;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasanZian on 01-0 7-2017.
 */

class Adapter_for_Android_Contact extends BaseAdapter implements Filterable {
    //Variables
    Context mContext;
    List<Android_contacts> mList_Android_contacts;
    ValueFilter valueFilter;

    List<Android_contacts> mStringFilterList;
    //Variables Ends

    public Adapter_for_Android_Contact(Context mContext, ArrayList<Android_contacts> mContact) {
        this.mContext = mContext;
        this.mList_Android_contacts = mContact;
        mStringFilterList = mList_Android_contacts;
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
        Android_contacts android_contacts = mList_Android_contacts.get(position);
        String name = android_contacts.getAndroid_contact_name();


        //setting textview

        textview_contact_Name.setText(mList_Android_contacts.get(position).android_contact_name);
        textview_contact_phone_no.setText(mList_Android_contacts.get(position).android_contact_phone_no);


        return view;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }


    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String str = constraint.toString().toUpperCase();
            Log.e("constraint", str);
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<Android_contacts> filterList = new ArrayList<Android_contacts>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).getAndroid_contact_name().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {
                        Android_contacts android_contacts = mStringFilterList.get(i);
                        filterList.add(android_contacts);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mList_Android_contacts = (ArrayList<Android_contacts>) results.values;
            notifyDataSetChanged();
        }


    }


}
