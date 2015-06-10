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

    private GoogleMap googleMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    private void addDefaultMarkers() {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.88780128700078, -122.20860095153483)).title
                ("Marker").snippet("This is the default info window"));
    }

    private void addSameTypeCustomMarkers() {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(38.88780128710078, -122.20860095153483)).title
                ("Bulldog").snippet("He is missing"));

        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.88780128700078,
                -122.20860095153483)).title("Poodle").snippet("She was pet-napped"));
        googleMap.setInfoWindowAdapter(new CustomInfoWindow(getLayoutInflater().inflate(R.layout.custom_info_window, null)));
    }

    private void addSameTypeCustomMarkersWithPhotos() {
        Map<String, String> images = new HashMap<>();
        Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(38.88780128710078, -122.20860095153483))
                .title("Bulldog").snippet("He is missing"));
        images.put(marker.getId(), "http://upload.wikimedia.org/wikipedia/commons/e/ef/Female_English_Bulldog.jpg");

        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.88780128700078,
                -122.20860095153483)).title("Poodle").snippet("She was pet-napped"));
        googleMap.setInfoWindowAdapter(new CustomInfoWindow(getLayoutInflater().inflate(R.layout.custom_info_window, null)
                , null, images));
    }

    private void addDifferentTypeCustomMarkers() {
        Set<String> dogSet = new HashSet<>();
        Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(38.88780128710078, -122.20860095153483))
                .title("Bulldog").snippet("He is missing"));
        dogSet.add(marker.getId());
        marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(36.88780128710078, -121.20860095153483))
                .title("Poodle").snippet("She was pet-napped"));
        dogSet.add(marker.getId());

        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.88780128700078,
                -120.20860095153483)).title("Tabby cat").snippet("She is lost"));
        googleMap.setInfoWindowAdapter(new CustomInfoWindow(getLayoutInflater().inflate(R.layout.custom_info_window, null)
                , dogSet));
    }

    private void addDifferentTypeCustomMarkersWithImages() {
        Set<String> dogSet = new HashSet<>();
        Map<String, String> images = new HashMap<>();
        Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(38.88780128710078, -122.20860095153483))
                .title("Bulldog").snippet("He is missing"));
        dogSet.add(marker.getId());
        images.put(marker.getId(), "http://upload.wikimedia.org/wikipedia/commons/e/ef/Female_English_Bulldog.jpg");
        marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(36.88780128710078, -121.20860095153483))
                .title("Poodle").snippet("She was pet-napped"));
        dogSet.add(marker.getId());

        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.88780128700078,
                -120.20860095153483)).title("Tabby cat").snippet("She is lost"));
        googleMap.setInfoWindowAdapter(new CustomInfoWindow(getLayoutInflater().inflate(R.layout.custom_info_window, null)
                , dogSet, images));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
//        addDefaultMarkers();
//        addSameTypeCustomMarkers();
//        addSameTypeCustomMarkersWithPhotos();
//        addDifferentTypeCustomMarkers();
//        addDifferentTypeCustomMarkersWithImages();
    }
}
