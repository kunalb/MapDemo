package com.nextdoor.mapdemo;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public abstract class GoogleMapWrapper {

    private GoogleMap map;

    public GoogleMapWrapper(GoogleMap map) {
        this.map = map;
    }

    /**
     * @see com.google.android.gms.maps.GoogleMap#animateCamera(com.google.android.gms.maps.CameraUpdate)
     */
    public void animateCamera(CameraUpdate update) {
        map.animateCamera(update);
    }

    /**
     * @see com.google.android.gms.maps.GoogleMap#addPolygon(com.google.android.gms.maps.model.PolygonOptions)
     * @return Polygon
     */
    public Polygon addPolygon(PolygonOptions polygonOptions) {
        return map.addPolygon(polygonOptions);
    }

    /**
     * @see com.google.android.gms.maps.GoogleMap#addMarker(com.google.android.gms.maps.model.MarkerOptions)
     * @return Marker
     */
    public Marker addMarker(MarkerOptions markerOptions) {
        return map.addMarker(markerOptions);
    }

    /**
     * @see com.google.android.gms.maps.GoogleMap#getMapType()
     * @return int
     */
    public int getMapType() {
        return map.getMapType();
    }

    /**
     * @see com.google.android.gms.maps.GoogleMap#setMapType(int)
     */
    public void setMapType(int mapType) {
        map.setMapType(mapType);
    }


    /**
     * @see com.google.android.gms.maps.GoogleMap#setOnMapClickListener(
     * com.google.android.gms.maps.GoogleMap.OnMapClickListener)
     */
    public void setOnMapClickListener(GoogleMap.OnMapClickListener listener) {
        map.setOnMapClickListener(listener);
    }

}
