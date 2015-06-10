package com.nextdoor.mapdemo;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.PolyUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mitali@nextdoor.com (Mitali Gala)
 */
public class DemoMap extends GoogleMapWrapper {

    private Map<Polygon, Marker> polygonMarkerCache = new HashMap<>();
    private OnPolygonClickListener polygonClickListener;
    private GoogleMap.OnMapClickListener mapClickListener;

    public DemoMap(GoogleMap map) {
        super(map);
        initMapClickListener();
    }

    public Polygon addPolygon(PolygonOptions polygonOptions, Marker marker) {
        Polygon polygon = super.addPolygon(polygonOptions);
        polygonMarkerCache.put(polygon, marker);
        return polygon;
    }

    /*
    ************************************
    *  Listeners
    ************************************
    */

    // Note:  this setter is overridden to allow support for both polygon and map click handling as separate events.
    @Override
    public void setOnMapClickListener(GoogleMap.OnMapClickListener listener) {
        this.mapClickListener = listener;
    }

    public final void setOnPolygonClickListener(OnPolygonClickListener listener) {
        this.polygonClickListener = listener;
    }

    /**
     * Google does not have a polygon click listener.  This method sets up a map click listener such that this map
     * will support {@link com.google.android.gms.maps.GoogleMap.OnMapClickListener} and {@link OnPolygonClickListener}
     * at the same time.  Polygon clicks take precedence over map clicks.  Implementations of
     * {@link OnPolygonClickListener} can return false if they want the event to propagate to the map click listener.
     */
    private void initMapClickListener() {
        super.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                boolean eventConsumed = false;
                // Identify polygon clicks and give them priority over map clicks
                if (polygonClickListener != null) {
                    for (Map.Entry<Polygon, Marker> entry : polygonMarkerCache.entrySet()) {
                        if (PolyUtil.containsLocation(latLng, entry.getKey().getPoints(), false)) {
                            eventConsumed = polygonClickListener.onPolygonClick(
                                    entry.getKey(), entry.getValue(), latLng);
                            break;
                        }
                    }
                }

                // Pass map clicks along to the map click listener if not in a polygon or if poly handler propagates
                if (mapClickListener != null && !eventConsumed) {
                    mapClickListener.onMapClick(latLng);
                }
            }
        });
    }

    /**
     * Defines signatures for methods that are called when a polygon is clicked or tapped.
     */
    public interface OnPolygonClickListener {
        /**
         * Called when a polygon has been clicked or tapped.
         *
         * @param polygon The polygon that was clicked
         * @param marker  The marker associated with this polygon, or null
         * @param latLng  The geo location corresponding to the click
         * @return boolean indicating if the event was consumed
         */
        boolean onPolygonClick(Polygon polygon, Marker marker, LatLng latLng);
    }
}
