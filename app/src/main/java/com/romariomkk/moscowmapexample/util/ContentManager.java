package com.romariomkk.moscowmapexample.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.model.LatLng;
import com.romariomkk.moscowmapexample.R;
import com.romariomkk.moscowmapexample.model.StationModel;
import com.romariomkk.moscowmapexample.model.StationModelBuilder;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by romariomkk on 29.05.2017.
 */
public class ContentManager {

    private static volatile ContentManager INSTANCE = null;

    private Context context;
    private ArrayList<StationModel> list;

    public static ContentManager getInstance(Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (ContentManager.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = new ContentManager(context);
                }
            }
        }
        return INSTANCE;
    }

    private ContentManager(Context c)
    {
        context = c;
        initList();
    }

    private void initList()
    {
        Resources resources = context.getResources();

        list = new ArrayList<StationModel>() {{
            String[] addresses = resources.getStringArray(R.array.streets);
            String[] times = resources.getStringArray(R.array.times);

            add(new StationModelBuilder()
                    .setCoords(new LatLng(55.745742, 37.550884))
                    .setPrice(35.5)
                    .setBrandName(Brands.LUKOIL.toString())
                    .setAddress(addresses[0])
                    .setBrandLogo(BitmapFactory.decodeResource(resources, R.drawable.lukoil))
                    .setLastUpdateTime(times[0])
                    .setCurrentDistance(0.4)
                    .createStationModel());
            add(new StationModelBuilder().setCoords(new LatLng(55.746370, 37.559575))
                    .setPrice(34.5)
                    .setBrandName(Brands.ROSGAZ.toString())
                    .setAddress(addresses[1])
                    .setBrandLogo(BitmapFactory.decodeResource(resources, R.drawable.rosgaz))
                    .setLastUpdateTime(times[1])
                    .setCurrentDistance(2.5)
                    .createStationModel());
            add(new StationModelBuilder()
                    .setCoords(new LatLng(55.746370, 37.559575))
                    .setPrice(34.6).setBrandName(Brands.ROSGAZ.toString())
                    .setAddress(addresses[2])
                    .setBrandLogo(BitmapFactory.decodeResource(resources, R.drawable.rosgaz))
                    .setLastUpdateTime(times[2])
                    .setCurrentDistance(5.6)
                    .createStationModel());
            add(new StationModelBuilder()
                    .setCoords(new LatLng(55.741729, 37.546915))
                    .setPrice(36.5)
                    .setBrandName(Brands.LUKOIL.toString())
                    .setAddress(addresses[3])
                    .setBrandLogo(BitmapFactory.decodeResource(resources, R.drawable.lukoil))
                    .setLastUpdateTime(times[3])
                    .setCurrentDistance(4)
                    .createStationModel());
            add(new StationModelBuilder()
                    .setCoords(new LatLng(55.741974, 37.553760))
                    .setPrice(31.5)
                    .setBrandName(Brands.ROSGAZ.toString())
                    .setAddress(addresses[4])
                    .setBrandLogo(BitmapFactory.decodeResource(resources, R.drawable.rosgaz))
                    .setLastUpdateTime(times[4])
                    .setCurrentDistance(2.3)
                    .createStationModel());
        }};
    }

    private enum Brands {
        LUKOIL, ROSGAZ
    }

    public enum ComparedPoint {
        PRICE,
        DISTANCE
    }

    public ArrayList<StationModel> getList()
    {
        return list;
    }

    public ArrayList<StationModel> retrieveModels(ComparedPoint point)
    {
        Collections.sort(list, point.equals(ComparedPoint.PRICE)
                ? (st1, st2) -> {
                    if (st1.price < st2.price)
                    {
                        return 1;
                    }
                    if (st1.price > st2.price)
                    {
                        return -1;
                    }
                    return 0;
                }
                : (st3, st4) -> {
                    if (st3.currentDistance < st4.currentDistance)
                    {
                        return 1;
                    }
                    if (st3.currentDistance > st4.currentDistance)
                    {
                        return -1;
                    }
                    return 0;
                });

        return list;
    }

}
