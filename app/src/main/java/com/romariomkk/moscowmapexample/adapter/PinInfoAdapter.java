package com.romariomkk.moscowmapexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.romariomkk.moscowmapexample.model.StationModel;

/**
 * Created by romariomkk on 29.05.2017.
 */
public class PinInfoAdapter implements GoogleMap.InfoWindowAdapter {
    private Context context;
    private StationModel station;


    @Override
    public View getInfoWindow(Marker marker)
    {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(0, null);
        return null;
    }
}
