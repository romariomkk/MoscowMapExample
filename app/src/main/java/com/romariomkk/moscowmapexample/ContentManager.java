package com.romariomkk.moscowmapexample;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.model.LatLng;
import com.romariomkk.moscowmapexample.model.StationModel;

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
    }

    private enum Brands {
        LUKOIL, ROSGAZ
    }

    public enum ComparedPoint {
        PRICE,
        DISTANCE
    }

    public ArrayList<StationModel> retrieveModels(ComparedPoint point)
    {
        Resources resources = context.getResources();
        list = new ArrayList<StationModel>() {{
            add(new StationModel(new LatLng(55.745742, 37.550884), 35.5, Brands.LUKOIL.toString(), "ул",
                    BitmapFactory.decodeResource(resources, R.drawable.lukoil), "2 часа назад", 0.2));
            add(new StationModel(new LatLng(55.746370, 37.559575), 34.5, Brands.ROSGAZ.toString(), "ss",
                    BitmapFactory.decodeResource(resources, R.drawable.rosgaz), "1 час назад", 2));
            add(new StationModel(new LatLng(55.746370, 37.559575), 34.6, Brands.ROSGAZ.toString(), "ss",
                    BitmapFactory.decodeResource(resources, R.drawable.rosgaz), "10 минут назад", 0.8));
            add(new StationModel(new LatLng(55.741729, 37.546915), 36.5, Brands.LUKOIL.toString(), "ss",
                    BitmapFactory.decodeResource(resources, R.drawable.lukoil), "Сейчас", 4));
            add(new StationModel(new LatLng(55.741974, 37.553760), 31.5, Brands.ROSGAZ.toString(), "ss",
                    BitmapFactory.decodeResource(resources, R.drawable.rosgaz), "Полчаса назад", 12));
        }};

        Collections.sort(list, point.equals(ComparedPoint.PRICE)
                ?
                (st1, st2) -> {
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
                : (st1, st2) -> {
                    if (st1.currentDistance < st2.currentDistance)
                    {
                        return 1;
                    }
                    if (st1.currentDistance > st2.currentDistance)
                    {
                        return -1;
                    }
                    return 0;
                });

        return list;
    }

}
