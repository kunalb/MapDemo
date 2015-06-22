package com.nextdoor.mapdemo;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final double LAT_ONE = 37.88780128700078;
    private static final double LONG_ONE = -122.20860095153483;
    private static final double LAT_TWO = 36.88780128710078;
    private static final double LONG_TWO = -121.20860095153483;
    private static final double LAT_THREE = 38.88780128710078;
    private static final double LONG_THREE = -120.20860095153483;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    /**
     * Add default markers to Google maps.
     * This adds a Marker at a set location and adds default InfoWindow that has a title and snippet.
     */
    private void addDefaultMarkers() {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(LAT_ONE, LONG_ONE)).title
                ("Marker").snippet("This is the default info window"));
    }

    /**
     * Add custom markers to Google maps.
     * This adds markers to some set locations and adds {@link CustomInfoWindow} that has a icon, title and snippet.
     */
    private void addSameTypeCustomMarkers() {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(LAT_ONE, LONG_ONE)).title
                ("Bulldog").snippet("She is missing"));

        googleMap.addMarker(new MarkerOptions().position(new LatLng(LAT_TWO,
                LONG_TWO)).title("Poodle").snippet("He was pet-napped"));
        googleMap.setInfoWindowAdapter(new CustomInfoWindow(getLayoutInflater().inflate(R.layout.custom_info_window, null)));
    }

    /**
     * Add custom markers to Google maps.
     * This adds markers to some set locations and adds {@link CustomInfoWindow} that has a photo, title and snippet.
     */
    private void addSameTypeCustomMarkersWithPhotos() {
        Map<String, String> images = new HashMap<>();
        Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(LAT_THREE, LONG_ONE))
                .title("Bulldog").snippet("She is missing"));
        images.put(marker.getId(), "http://upload.wikimedia.org/wikipedia/commons/e/ef/Female_English_Bulldog.jpg");

        googleMap.addMarker(new MarkerOptions().position(new LatLng(LAT_ONE,
                LONG_ONE)).title("Poodle").snippet("He was pet-napped"));
        googleMap.setInfoWindowAdapter(new CustomInfoWindow(getLayoutInflater().inflate(R.layout.custom_info_window, null)
                , null, images));
    }

    /**
     * Add custom markers to Google maps.
     * This adds markers to some set locations and adds {@link CustomInfoWindow} that has a photo, title and snippet.
     */
    private void addDifferentTypeCustomMarkers() {
        Set<String> catSet = new HashSet<>();
        googleMap.addMarker(new MarkerOptions().position(new LatLng(LAT_THREE, LONG_ONE))
                .title("Bulldog").snippet("She is missing"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(LAT_TWO, LONG_TWO))
                .title("Poodle").snippet("He was pet-napped"));

        Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(LAT_ONE,
                LONG_THREE)).title("Tabby cat").snippet("She is lost"));
        catSet.add(marker.getId());
        googleMap.setInfoWindowAdapter(new CustomInfoWindow(getLayoutInflater().inflate(R.layout.custom_info_window, null)
                , catSet));
    }

    /**
     * Add custom markers to Google maps.
     * This adds markers to some set locations and adds {@link CustomInfoWindow} that has a photo, title and snippet.
     * It also adds different types of markers.
     */
    private void addDifferentTypeCustomMarkersWithImages() {
        Set<String> catSet = new HashSet<>();
        Map<String, String> images = new HashMap<>();
        Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(LAT_THREE,
                LONG_ONE)).title("Bulldog").snippet("She is missing"));
        images.put(marker.getId(), "http://upload.wikimedia.org/wikipedia/commons/e/ef/Female_English_Bulldog.jpg");
        googleMap.addMarker(new MarkerOptions().position(new LatLng(LAT_TWO, LONG_TWO))
                .title("Poodle").snippet("He was pet-napped"));

        marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(LAT_ONE,
                LONG_THREE)).title("Tabby cat").snippet("She is lost"));
        catSet.add(marker.getId());
        googleMap.setInfoWindowAdapter(new CustomInfoWindow(getLayoutInflater().inflate(R.layout.custom_info_window, null)
                , catSet, images));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
//        addDefaultMarkers();
//        addSameTypeCustomMarkers();
        addSameTypeCustomMarkersWithPhotos();
//        addDifferentTypeCustomMarkers();
//        addDifferentTypeCustomMarkersWithImages();
    }
}
