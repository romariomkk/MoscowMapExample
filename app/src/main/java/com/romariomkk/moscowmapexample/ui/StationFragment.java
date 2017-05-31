package com.romariomkk.moscowmapexample.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.romariomkk.moscowmapexample.util.ContentManager;
import com.romariomkk.moscowmapexample.R;
import com.romariomkk.moscowmapexample.adapter.RecyclerAdapter;
import com.romariomkk.moscowmapexample.adapter.SlidingViewPageAdapter;

import java.util.Objects;

/**
 * Created by romariomkk on 29.05.2017.
 */
public class StationFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    public final static String DISTANCE_BASED_FRAGLIST = "By distance";
    public final static String PRICE_BASED_FRAGLIST = "By price";

    private int fragmentIndex = 0;

    public static StationFragment newInstance(String name) {
        Bundle extras = new Bundle();
        extras.putString(SlidingViewPageAdapter.TITLE, name);

        StationFragment newInstance = new StationFragment();
        newInstance.setArguments(extras);

        int fragmentIndex = Objects.equals(name, DISTANCE_BASED_FRAGLIST) ? 1 : 0;
        extras.putInt("index", fragmentIndex);

        return newInstance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null)
            fragmentIndex = args.getInt("index");
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

        adapter = new RecyclerAdapter(context, contentManager.retrieveModels(fragmentIndex == 1
                ? ContentManager.ComparedPoint.DISTANCE
                : ContentManager.ComparedPoint.PRICE));
        adapter.setOnItemClickListener((MainActivity) getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }


}
