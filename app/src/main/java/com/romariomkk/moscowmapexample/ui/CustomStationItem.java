package com.romariomkk.moscowmapexample.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.romariomkk.moscowmapexample.R;
import com.romariomkk.moscowmapexample.model.StationModel;

import java.util.Locale;

public class CustomStationItem extends RelativeLayout {

    private TextView priceText;
    private TextView lastUpdateText;
    private ImageView brandLogoImage;
    private TextView brandNameText;
    private TextView addressText;
    private TextView currentDistance;

    private StationModel station;

    public CustomStationItem(Context context){
        this(context, null);
    }

    public CustomStationItem(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initInflater();
        initFields();
    }

    private void initInflater() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.custom_station_item, this, true);
    }

    private void initFields() {
        priceText = (TextView) findViewById(R.id.priceText);
        lastUpdateText = (TextView) findViewById(R.id.lastUpdateText);
        brandLogoImage = (ImageView) findViewById(R.id.brandLogoImage);
        brandNameText = (TextView) findViewById(R.id.brandNameText);
        addressText = (TextView) findViewById(R.id.addressText);
        currentDistance = (TextView) findViewById(R.id.distance_text);
    }

    public void setStation(StationModel st){
        this.station = st;

        refreshUI();
    }

    private void refreshUI()
    {
        priceText.setText(String.format(Locale.ENGLISH, getResources().getString(R.string.priceValue), station.price));
        lastUpdateText.setText(station.lastUpdateTime);
        brandLogoImage.setImageBitmap(station.brandLogo);
        brandNameText.setText(station.brandName);
        addressText.setText(station.address);
        currentDistance.setText(String.format(Locale.ENGLISH, getResources().getString(R.string.distance), station.currentDistance));
    }

}
