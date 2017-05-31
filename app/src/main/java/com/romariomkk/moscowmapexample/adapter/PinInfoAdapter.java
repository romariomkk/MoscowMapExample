package com.romariomkk.moscowmapexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.romariomkk.moscowmapexample.R;
import com.romariomkk.moscowmapexample.model.StationModel;
import com.romariomkk.moscowmapexample.util.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romariomkk on 30.05.2017.
 */
public class PinInfoAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;
    private List<StationModel> pinList;

    public PinInfoAdapter(Context context, ArrayList<StationModel> pinList)
    {
        this.context = context;
        this.pinList = pinList;
    }

    @Override
    public View getInfoWindow(Marker marker)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(R.layout.info_pin_layout, null, false);

        int pos = Integer.valueOf(marker.getTitle());

        TextView priceText = (TextView) convertView.findViewById(R.id.pin_price);
        TextView addressText = (TextView) convertView.findViewById(R.id.pin_address);
        ImageView logoImg = (ImageView) convertView.findViewById(R.id.pin_logo);

        StationModel station = pinList.get(pos);

        priceText.setText(String.format(context.getString(R.string.priceValue), station.price));
        addressText.setText(station.address);
        logoImg.setImageBitmap(station.brandLogo);

        int minWidth = Utilities.getDisplaySize(context).x;

        convertView.setMinimumWidth(minWidth / 2);
        //convertView.setPadding(0, 0, 0, 0);

        return convertView;
    }

    @Override
    public View getInfoContents(Marker marker)
    {
        return null;
    }
}
