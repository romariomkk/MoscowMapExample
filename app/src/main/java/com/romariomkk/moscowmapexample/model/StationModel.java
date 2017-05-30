package com.romariomkk.moscowmapexample.model;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by romariomkk on 29.05.2017.
 */
public class StationModel {

    public double price;
    public Bitmap brandLogo;
    public String brandName;
    public String address;

    public String lastUpdateTime;
    public double currentDistance;

    public LatLng coords;

    public StationModel(LatLng coords, double price, String brandName, String address, Bitmap brandLogo, String lastUpdateTime, double currentDistance) {
        this.coords = coords;
        this.price = price;
        this.brandName = brandName;
        this.address = address;
        this.brandLogo = brandLogo;
        this.lastUpdateTime = lastUpdateTime;
        this.currentDistance = currentDistance;
    }

}
