package com.example.flyman.playground;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.example.flyman.playground.model.ApiClient;
import com.example.flyman.playground.model.ParkJson;
import com.example.flyman.playground.widget.SelectToolbar;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends FragmentActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private SelectToolbar mToolbar;
    private ParkMapFragment mapFragment;

    //TODO use the current location
    private double currentLat = 37.773972;
    private double currentLng = -122.431297;
    private final static int NUM_TO_SHOW = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapFragment = (ParkMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        CameraUpdate center =
                CameraUpdateFactory.newLatLng(new LatLng(currentLat, currentLng));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

        mapFragment.getMap().moveCamera(center);
        mapFragment.getMap().animateCamera(zoom);

        mToolbar = (SelectToolbar) findViewById(R.id.select_toolbar);

        mToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParkingRxjava();
            }
        });
    }

    private void getParkingRxjava() {
        ApiClient.ApiStores apiStores = ApiClient.retrofit().create(ApiClient.ApiStores.class);
        Observable<List<ParkJson>> observable = apiStores.getAvailableParking(Double.toString(currentLat), Double.toString(currentLng));
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ParkJson>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.getMessage());
                    }

                    @Override
                    public void onNext(List<ParkJson> result) {
                        Log.d(TAG, "search result size: " + result.size());
                        for(ParkJson json : result) {
                            Log.d(TAG, "search result: " + ", lat: " + json.getLat() + ", lng: " + json.getLng());
                        }
                        Map<String, String> searchResult = new HashMap<>();
                        for(int i = 0; i < result.size() && i < NUM_TO_SHOW; i++) {
                            searchResult.put(result.get(i).getLat(), result.get(i).getLng());
                        }

                        mapFragment.populateMarkers(searchResult);
                    }
                });
    }
}