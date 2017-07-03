package com.example.hasanzian.contactapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by hasanZian on 30-06-2017.
 */


    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sample_0, R.drawable.sample_1,  //1
            R.drawable.sample_3, R.drawable.sample_4,   //2
            R.drawable.sample_5, R.drawable.sample_17,   //3
            R.drawable.sample_7, R.drawable.sample_8,       //4
            R.drawable.sample_9, R.drawable.sample_10,        //5
            R.drawable.sample_5, R.drawable.sample_17,            //6
            R.drawable.sample_7, R.drawable.sample_8,            //7
            R.drawable.sample_0, R.drawable.sample_1,          //8
            R.drawable.sample_3, R.drawable.sample_4,

    };

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setAdjustViewBounds(true);
                imageView.setPadding(1, 1, 1, 1);

            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }
    }








