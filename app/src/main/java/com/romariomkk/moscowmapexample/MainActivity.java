package com.romariomkk.moscowmapexample;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.romariomkk.moscowmapexample.model.RecyclerAdapter;
import com.romariomkk.moscowmapexample.model.StationModel;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, RecyclerAdapter.OnItemClickListener {

    private GoogleMap gMap;
    private TextView title;

    private View bottomSheetView;
    private CoordinatorLayout coordLayout;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private int peekHeight;

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
        toolbar.setPadding(0, getStatusBarHeight(), 0, 0);

        title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        title.setText(R.string.app_name);

        setSupportActionBar(toolbar);

        coordLayout = (CoordinatorLayout) findViewById(R.id.coordLayout);
        bottomSheetView = coordLayout.findViewById(R.id.bottomSheetView);

        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheetView);
        int imgHeight = BitmapFactory.decodeResource(getResources(), R.drawable.sticker).getHeight();
        final int singleItemHeight = dpToPx(64);
        final int toolBarHeight = dpToPx(48);
        peekHeight = (imgHeight + singleItemHeight + toolBarHeight);
        behavior.setPeekHeight(peekHeight);
    }

    private int getStatusBarHeight()
    {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
        {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private int dpToPx(int dp)
    {
        return dp * (getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
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
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
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

        LatLng moscow = new LatLng(55.754463, 37.608646);
        gMap.addMarker(new MarkerOptions().position(moscow).title("Marker in Moscow"));
        //// TODO: 29.05.2017 make more points
        gMap.moveCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds(
                new LatLng(moscow.latitude - 0.01, moscow.longitude - 0.01),
                new LatLng(moscow.latitude + 0.01, moscow.longitude + 0.01)), metrics.widthPixels, metrics.heightPixels, 0));

    }

    @Override
    public void onItemClicked(StationModel station)
    {
        LatLng coords = station.coords;
        gMap.addMarker(new MarkerOptions().position(coords).anchor(0.5f, 0.5f).flat(false).title(station.address));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(station.coords, 5));
    }
}
