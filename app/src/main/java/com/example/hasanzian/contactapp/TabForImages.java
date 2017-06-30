package com.example.hasanzian.contactapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Created by hasanZian on 30-06-2017.
 */

public class TabForImages extends Fragment{

   public GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_images, container, false);


        gridView=(GridView) rootView.findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(getActivity()));


        return rootView;



    }

}
