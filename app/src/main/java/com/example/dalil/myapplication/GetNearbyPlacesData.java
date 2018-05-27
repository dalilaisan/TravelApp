package com.example.dalil.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

class   GetNearbyPlacesData extends AsyncTask<Object, String, String> {

    private static final String TAG = "GetNearbyPlacesData";
    private String googlePlacesData;
    private GoogleMap mMap;
    String url;

    @Override
    protected String doInBackground(Object... objects){
        Log.d(TAG, "doInBackground: initializing");
        mMap = (GoogleMap)objects[0];
        url = (String)objects[1];

        DownloadURL downloadURL = new DownloadURL();
        try {
            googlePlacesData = downloadURL.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s){
        Log.d(TAG, "onPostExecute: initializing");

        List<HashMap<String, String>> nearbyPlaceList;
        DataParser parser = new DataParser();
        nearbyPlaceList = parser.parse(s);
        Log.d(TAG, "onPostExecute: called parse method");
        showNearbyPlaces(nearbyPlaceList);
    }

    private void showNearbyPlaces(List<HashMap<String, String>> nearbyPlaceList) {

        Log.d(TAG, "showNearbyPlaces: nearbyPlaceList.size= " + nearbyPlaceList.size());

        for(int i = 0; i < nearbyPlaceList.size(); i++)
        {
            Log.d(TAG, "showNearbyPlaces: entering for loop");
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = nearbyPlaceList.get(i);

            String placeName = googlePlace.get("place_name");
            String vicinity = googlePlace.get("vicinity");
            double lat = Double.parseDouble( googlePlace.get("lat"));
            double lng = Double.parseDouble( googlePlace.get("lng"));

            Log.d(TAG, "showNearbyPlaces: just got lat nad lng");

            LatLng latLng = new LatLng( lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(placeName + " : "+ vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

            Log.d(TAG, "showNearbyPlaces: about to move camera and add markers");

            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10));

            Log.d(TAG, "showNearbyPlaces: is it done is it done is it done is it done");
        }
    }
}
