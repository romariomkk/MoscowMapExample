package com.romariomkk.moscowmapexample.model;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.LatLng;

public class StationModelBuilder {
    private LatLng coords;
    private double price;
    private String brandName;
    private String address;
    private Bitmap brandLogo;
    private String lastUpdateTime;
    private double currentDistance;

    public StationModelBuilder setCoords(LatLng coords)
    {
        this.coords = coords;
        return this;
    }

    public StationModelBuilder setPrice(double price)
    {
        this.price = price;
        return this;
    }

    public StationModelBuilder setBrandName(String brandName)
    {
        this.brandName = brandName;
        return this;
    }

    public StationModelBuilder setAddress(String address)
    {
        this.address = address;
        return this;
    }

    public StationModelBuilder setBrandLogo(Bitmap brandLogo)
    {
        this.brandLogo = brandLogo;
        return this;
    }

    public StationModelBuilder setLastUpdateTime(String lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
        return this;
    }

    public StationModelBuilder setCurrentDistance(double currentDistance)
    {
        this.currentDistance = currentDistance;
        return this;
    }

    public StationModel createStationModel()
    {
        return new StationModel(coords, price, brandName, address, brandLogo, lastUpdateTime, currentDistance);
    }
}