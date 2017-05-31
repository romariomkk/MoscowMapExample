package com.romariomkk.moscowmapexample.ui;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.BitmapCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.romariomkk.moscowmapexample.R;
import com.romariomkk.moscowmapexample.adapter.PinInfoAdapter;
import com.romariomkk.moscowmapexample.adapter.RecyclerAdapter;
import com.romariomkk.moscowmapexample.adapter.SlidingViewPageAdapter;
import com.romariomkk.moscowmapexample.model.StationModel;
import com.romariomkk.moscowmapexample.util.ContentManager;
import com.romariomkk.moscowmapexample.util.Utilities;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, RecyclerAdapter.OnItemClickListener {

    private GoogleMap gMap;

    private ViewPager viewPager;

    private Marker currentMarker;
    private BottomSheetBehavior behavior;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        setupToolbar();
        setupMap();
        setupViewPager();
        setupTabLayout();
    }

    private void setupToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.person);
        toolbar.setPadding(0, Utilities.getStatusBarHeight(this), 0, 0);

        TextView title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        title.setText(R.string.app_name);

        setSupportActionBar(toolbar);

        CoordinatorLayout coordLayout = (CoordinatorLayout) findViewById(R.id.coordLayout);
        View bottomSheetView = coordLayout.findViewById(R.id.bottomSheetView);

        behavior = BottomSheetBehavior.from(bottomSheetView);
        behavior.setPeekHeight(getCalculatedPeekHeight());
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private int getCalculatedPeekHeight()
    {
        int imgHeight = BitmapFactory.decodeResource(getResources(), R.drawable.sticker).getHeight();
        final int singleItemHeight = Utilities.dpToPx(this, 64);
        final int toolBarHeight = Utilities.dpToPx(this, 48);
        return (imgHeight + singleItemHeight + toolBarHeight);
    }


    private void setupMap()
    {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void setupViewPager()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        SlidingViewPageAdapter viewPagerAdapter = new SlidingViewPageAdapter(fragmentManager);
        viewPagerAdapter
                .addFragment(StationFragment.newInstance(StationFragment.DISTANCE_BASED_FRAGLIST))
                .addFragment(StationFragment.newInstance(StationFragment.PRICE_BASED_FRAGLIST));

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void setupTabLayout()
    {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.map_menu, menu);
        return true;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        gMap = googleMap;
        PinInfoAdapter adapter = new PinInfoAdapter(this, ContentManager.getInstance(this).getList());
        gMap.setInfoWindowAdapter(adapter);
        gMap.setOnInfoWindowClickListener(Marker::hideInfoWindow);

        LatLng moscow = new LatLng(55.754463, 37.608646);
        gMap.animateCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds(
                new LatLng(moscow.latitude - 0.01, moscow.longitude - 0.01),
                new LatLng(moscow.latitude + 0.01, moscow.longitude + 0.01)),
                metrics.widthPixels, metrics.heightPixels, 0));

    }

    @Override
    public void onItemClicked(StationModel station, int pos)
    {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        LatLng coords = station.coords;
        hidePreviousMarker();
        initNewMarker(pos, coords);

        if (!currentMarker.isInfoWindowShown())
            currentMarker.showInfoWindow();
        else
            currentMarker.hideInfoWindow();

        collapseBottomSheet();

        gMap.animateCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds(
                new LatLng(coords.latitude - 0.01, coords.longitude - 0.01),
                new LatLng(coords.latitude + 0.01, coords.longitude + 0.01)),
                metrics.widthPixels, metrics.heightPixels, 0));
    }

    private void collapseBottomSheet()
    {
        if (behavior != null)
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void initNewMarker(int pos, LatLng coords)
    {
        currentMarker = gMap.addMarker(new MarkerOptions()
                .position(coords)
                .anchor(1f, 1f)
                .flat(false)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.green_arrow))
                .title(Integer.toString(pos)));
    }

    private void hidePreviousMarker()
    {
        if (currentMarker != null)
        {
            currentMarker.hideInfoWindow();
            currentMarker.setVisible(false);
        }
    }
}
