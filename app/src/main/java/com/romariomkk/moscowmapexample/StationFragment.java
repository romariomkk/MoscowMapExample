package com.romariomkk.moscowmapexample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.romariomkk.moscowmapexample.model.RecyclerAdapter;

import java.util.Objects;

/**
 * Created by romariomkk on 29.05.2017.
 */
public class StationFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    public final static String DISTANCE_BASED_FRAGLIST = "By distance";
    public final static String PRICE_BASED_FRAGLIST = "By price";

    private static int fragmentIndex = 0;

    public static StationFragment newInstance(String name) {
        Bundle extras = new Bundle();
        extras.putString(SlidingViewPageAdapter.TITLE, name);

        StationFragment newInstance = new StationFragment();
        newInstance.setArguments(extras);

        fragmentIndex = (Objects.equals(name, DISTANCE_BASED_FRAGLIST)) ? 0 : 1;
        return newInstance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.station_list, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        setupRecyclerView();
        return rootView;
    }

    private void setupRecyclerView() {
        Context context = getContext();
        ContentManager contentManager = ContentManager.getInstance(context);

        adapter = new RecyclerAdapter(context, contentManager.retrieveModels(fragmentIndex == 0
                ? ContentManager.ComparedPoint.DISTANCE
                : ContentManager.ComparedPoint.PRICE));
        adapter.setOnItemClickListener((MainActivity) getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }


}
