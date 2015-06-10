package com.nextdoor.mapdemo;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class PolygonActivity extends FragmentActivity implements OnMapReadyCallback {

    private DemoMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polygon);
        ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    private void setupClickListener() {
        googleMap.setOnPolygonClickListener(new DemoMap.OnPolygonClickListener() {
            @Override
            public boolean onPolygonClick(Polygon polygon, Marker marker, LatLng latLng) {
                if (marker != null && !marker.isInfoWindowShown()) {
                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
                    if (!marker.isInfoWindowShown()) {
                        marker.showInfoWindow();
                    }
                }
                return true;
            }
        });
    }
    private Marker getMarker() {
        Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(37.88780128710078,
                -121.20860095153483))
                .title("Pets missing").snippet("Total of 3"));
        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.transparent_1px));
        return marker;
    }

    private void addPolygon() {
        Polygon polygon = googleMap.addPolygon(new PolygonOptions()
                .add(new LatLng(38.88780128710078, -122.20860095153483),
                        new LatLng(36.88780128710078, -121.20860095153483),
                        new LatLng(37.88780128700078, -120.20860095153483)), getMarker());
        polygon.setFillColor(Color.GRAY);
        polygon.setStrokeColor(Color.DKGRAY);
        setupClickListener();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = new DemoMap(googleMap);
        addPolygon();
    }
}
