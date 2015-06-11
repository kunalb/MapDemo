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

    private GoogleMap googleMap;

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
                ("Bulldog").snippet("She is missing"));

        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.88780128700078,
                -122.20860095153483)).title("Poodle").snippet("He was pet-napped"));
        googleMap.setInfoWindowAdapter(new CustomInfoWindow(getLayoutInflater().inflate(R.layout.custom_info_window, null)));
    }

    private void addSameTypeCustomMarkersWithPhotos() {
        Map<String, String> images = new HashMap<>();
        Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(38.88780128710078, -122.20860095153483))
                .title("Bulldog").snippet("She is missing"));
        images.put(marker.getId(), "http://upload.wikimedia.org/wikipedia/commons/e/ef/Female_English_Bulldog.jpg");

        googleMap.addMarker(new MarkerOptions().position(new LatLng(37.88780128700078,
                -122.20860095153483)).title("Poodle").snippet("He was pet-napped"));
        googleMap.setInfoWindowAdapter(new CustomInfoWindow(getLayoutInflater().inflate(R.layout.custom_info_window, null)
                , null, images));
    }

    private void addDifferentTypeCustomMarkers() {
        Set<String> catSet = new HashSet<>();
        googleMap.addMarker(new MarkerOptions().position(new LatLng(38.88780128710078, -122.20860095153483))
                .title("Bulldog").snippet("She is missing"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(36.88780128710078, -121.20860095153483))
                .title("Poodle").snippet("He was pet-napped"));

        Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(37.88780128700078,
                -120.20860095153483)).title("Tabby cat").snippet("She is lost"));
        catSet.add(marker.getId());
        googleMap.setInfoWindowAdapter(new CustomInfoWindow(getLayoutInflater().inflate(R.layout.custom_info_window, null)
                , catSet));
    }

    private void addDifferentTypeCustomMarkersWithImages() {
        Set<String> catSet = new HashSet<>();
        Map<String, String> images = new HashMap<>();
        Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(38.88780128710078,
                -122.20860095153483)).title("Bulldog").snippet("She is missing"));
        images.put(marker.getId(), "http://upload.wikimedia.org/wikipedia/commons/e/ef/Female_English_Bulldog.jpg");
        googleMap.addMarker(new MarkerOptions().position(new LatLng(36.88780128710078, -121.20860095153483))
                .title("Poodle").snippet("He was pet-napped"));

        marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(37.88780128700078,
                -120.20860095153483)).title("Tabby cat").snippet("She is lost"));
        catSet.add(marker.getId());
        googleMap.setInfoWindowAdapter(new CustomInfoWindow(getLayoutInflater().inflate(R.layout.custom_info_window, null)
                , catSet, images));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        addDefaultMarkers();
//        addSameTypeCustomMarkers();
//        addSameTypeCustomMarkersWithPhotos();
//        addDifferentTypeCustomMarkers();
//        addDifferentTypeCustomMarkersWithImages();
    }
}
