package com.romariomkk.moscowmapexample.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.romariomkk.moscowmapexample.R;

public class CustomStationItem extends RelativeLayout {

    private TextView priceText;
    private TextView lastUpdateText;
    private ImageView brandLogoImage;
    private TextView brandNameText;
    private TextView addressText;
    private TextView currentDistance;

    private StationModel station;

    public CustomStationItem(Context context){
        super(context);
        initInflater();
        initFields();
    }

    private void initInflater() {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        priceText.setText(String.valueOf(station.price));
        lastUpdateText.setText(station.lastUpdateTime);
        brandLogoImage.setImageBitmap(station.brandLogo);
        brandNameText.setText(station.brandName);
        addressText.setText(station.address);
        currentDistance.setText(String.valueOf(station.currentDistance));
    }

}
