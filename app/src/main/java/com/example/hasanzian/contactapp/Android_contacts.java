package com.example.hasanzian.contactapp;

/**
 * Created by hasanZian on 01-07-2017.
 */

public class Android_contacts {
    public String android_contact_name = "";
    public String android_contact_phone_no = "";

    public Android_contacts() {

    }

    public Android_contacts(String android_contact_name, String android_contact_phone_no) {
        this.android_contact_name = android_contact_name;
        this.android_contact_phone_no = android_contact_phone_no;
    }

    public String getAndroid_contact_name() {
        return android_contact_name;
    }

    public void setAndroid_contact_name(String android_contact_name) {
        this.android_contact_name = android_contact_name;
    }

    public String getAndroid_contact_phone_no() {
        return android_contact_phone_no;
    }

    public void setAndroid_contact_phone_no(String android_contact_phone_no) {
        this.android_contact_phone_no = android_contact_phone_no;
    }
}