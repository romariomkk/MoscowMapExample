package com.romariomkk.moscowmapexample;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.model.LatLng;
import com.romariomkk.moscowmapexample.model.StationModel;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by romariomkk on 30.12.2016.
 */
public class ContentManager {

    Context context;

    ArrayList<StationModel> list;

    public ContentManager(Context c) {
        context = c;
    }

    private enum Brands {
        LUKOIL,
        ROSGAZ
    }

    public enum ComparedPoint {
        PRICE,
        DISTANCE
    }

    public ArrayList<StationModel> retrieveModels(ComparedPoint point) {
        list = new ArrayList<StationModel>() {{
            new StationModel(new LatLng(55.745742, 37.550884), 35.5, Brands.LUKOIL.toString(), "ул", BitmapFactory.decodeResource(context.getResources(), R.drawable.lukoil), "2 часа назад", 0.2);
            new StationModel(new LatLng(55.746370, 37.559575), 34.5, Brands.ROSGAZ.toString(), "ss", BitmapFactory.decodeResource(context.getResources(), R.drawable.rosgaz), "1 час назад", 2);
            new StationModel(new LatLng(55.746370, 37.559575), 34.6, Brands.ROSGAZ.toString(), "ss", BitmapFactory.decodeResource(context.getResources(), R.drawable.rosgaz), "10 минут назад", 0.8);
            new StationModel(new LatLng(55.741729, 37.546915), 36.5, Brands.LUKOIL.toString(), "ss", BitmapFactory.decodeResource(context.getResources(), R.drawable.lukoil), "Сейчас", 4);
            new StationModel(new LatLng(55.741974, 37.553760), 31.5, Brands.ROSGAZ.toString(), "ss", BitmapFactory.decodeResource(context.getResources(), R.drawable.rosgaz), "Полчаса назад", 12);
        }};

        Collections.sort(list, point.equals(ComparedPoint.PRICE)
                ? (st1, st2) -> {
            if (st1.price < st2.price) {
                return 1;
            }
            if (st1.price > st2.price) {
                return -1;
            }
            return 0;
        }
                : (st1, st2) -> {
            if (st1.currentDistance < st2.currentDistance) {
                return 1;
            }
            if (st1.currentDistance > st2.currentDistance) {
                return -1;
            }
            return 0;
        });

        return list;
    }

}
