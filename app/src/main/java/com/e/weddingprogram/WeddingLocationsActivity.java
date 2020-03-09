package com.e.weddingprogram;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.e.weddingprogram.task_results.MapTaskResult;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.android.PolyUtil;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeddingLocationsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationClickListener {
    private GoogleMap mMap;
    final int LOCATION_REQUEST_CODE=1;

    Marker userLocationMarker;
    com.google.maps.model.LatLng userLocatioLatLong;

    private FusedLocationProviderClient fusedLocationClient;
    LocationRequest locationRequest;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding_locations);
        ButterKnife.bind(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        initMap();

    }

    public void initMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        Thread thread = new Thread() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMap = googleMap;
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        //Check or Request Location Permission and enable my location layer
                        requestLocationPermissionIfNotGranted();
                    }
                });
            }
        };
        thread.start();

    }

    private class MapSetupTask extends AsyncTask<Void, Integer, MapTaskResult> {
        @Override
        protected MapTaskResult doInBackground(Void... voids) {
            DirectionsResult churchDirectionResult=null;
            com.google.maps.model.LatLng churchLatLong=getLatLongFromAddress("Christian Pentecostal Mission, Umuahia");
            if(churchLatLong!=null){
               churchDirectionResult=getDirectionResult(userLocatioLatLong,churchLatLong);
            }

            List<LatLng> churchLocationPath=getPolylinePathsFromDirectionResult(churchDirectionResult);


            DirectionsResult receptionDirectionResult=null;
            com.google.maps.model.LatLng receptionLatLong=getLatLongFromAddress("Umuahia North Local Government Offices, Umuahia");
            if(receptionLatLong!=null){
                receptionDirectionResult=getDirectionResult(userLocatioLatLong,receptionLatLong);
            }

            List<LatLng> receptionLocationPath=getPolylinePathsFromDirectionResult(receptionDirectionResult);

            return new MapTaskResult(receptionLatLong,churchLatLong,churchLocationPath,receptionLocationPath);
        }

        public com.google.maps.model.LatLng getLatLongFromAddress(String address){
            com.google.maps.model.LatLng latLng=null;
            Address address1=getLocationFromAddress(address);
            if(address1!=null){
                latLng=new com.google.maps.model.LatLng(address1.getLatitude(),address1.getLongitude());
            }
            return latLng;
        }

        @Override
        protected void onPostExecute(MapTaskResult mapTaskResult) {
            if(mapTaskResult.getChurchLatLong()!=null){
                addMakerToMap(mapTaskResult.getChurchLatLong().lat,mapTaskResult.getChurchLatLong().lng,"Church Address");
            }

            if(mapTaskResult.getReceptionLatLong()!=null){
                addMakerToMap(mapTaskResult.getReceptionLatLong().lat,mapTaskResult.getReceptionLatLong().lng,"Reception Address");
            }

            if(mapTaskResult.getChurchLocationPath()!=null && userLocatioLatLong!=null){
                addPolyLineToMap(mapTaskResult.getChurchLocationPath());
            }

            if(mapTaskResult.getReceptionLocationPath()!=null && userLocatioLatLong!=null){
                addPolyLineToMap(mapTaskResult.getReceptionLocationPath());
            }

        }
    }


    public void addPolyLineToMap(List<LatLng> route){
        mMap.addPolyline(new PolylineOptions().addAll(route));
    }


    OnSuccessListener<Location> onSuccessListener=new OnSuccessListener<Location>() {
        @Override
        public void onSuccess(Location location) {
            // Got last known location. In some rare situations this can be null.
            if (location != null) {
                // Logic to handle location object
                userLocatioLatLong=new com.google.maps.model.LatLng(location.getLatitude(),location.getLongitude());
                if(userLocationMarker==null){
                    userLocationMarker=addMakerToMap(location.getLatitude(),location.getLongitude(),"Your Location");
                }else {
                    userLocationMarker.setPosition(new LatLng(location.getLatitude(),location.getLongitude()));
                }

                new MapSetupTask().execute();
            }
        }
    };

    public void requestLocationPermissionIfNotGranted(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationClickListener(this);
            fusedLocationClient.getLastLocation().addOnSuccessListener(this,onSuccessListener);
        } else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},LOCATION_REQUEST_CODE);
            }
            // Show rationale and request permission.
        }
    }

    public Address getLocationFromAddress(String strAddress){

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        Address location=null;

        try {
            address = coder.getFromLocationName(strAddress,5);
            if (address==null) {
                return null;
            }
            location=address.get(0);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return location;
    }

    private GeoApiContext getGeoContext() {
        GeoApiContext geoApiContext = new GeoApiContext();
        return geoApiContext.setQueryRateLimit(3).
                setApiKey(getString(R.string.google_maps_key)).setConnectTimeout(1, TimeUnit.SECONDS)
                .setReadTimeout(1, TimeUnit.SECONDS)
                .setWriteTimeout(1, TimeUnit.SECONDS);
    }

    public DirectionsResult getDirectionResult(com.google.maps.model.LatLng origin, com.google.maps.model.LatLng destination){
        DateTime now = new DateTime();
        DirectionsResult result=null;
        try {
             result = DirectionsApi.newRequest(getGeoContext()).
                    mode(TravelMode.DRIVING).origin(origin).destination(destination).departureTime(now).await();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private List<LatLng> getPolylinePathsFromDirectionResult(DirectionsResult results) {
        if(results==null){
            return null;
        }
        List<LatLng> decodedPath = PolyUtil.decode(results.routes[0].overviewPolyline.getEncodedPath());
       return decodedPath;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (permissions.length == 2 &&
                    permissions[0] .equals(Manifest.permission.ACCESS_FINE_LOCATION)  &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    permissions[1] .equals(Manifest.permission.ACCESS_COARSE_LOCATION)  &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                requestLocationPermissionIfNotGranted();
            } else {
                // Permission was denied. Display an error message.
                Toast.makeText(this,"Permissiion Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public Marker addMakerToMap(double v, double v1, String description){
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(v, v1);
        Marker marker=mMap.addMarker(new MarkerOptions().position(sydney).title(description).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 12.0f ) );
        return marker;
    }

    @OnClick(R.id.backBtn)
    public void onViewClicked() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        if(userLocationMarker==null){
            userLocatioLatLong=new com.google.maps.model.LatLng(location.getLatitude(),location.getLongitude());
            userLocationMarker=addMakerToMap(location.getLatitude(),location.getLongitude(),"Your Location");
        }else {
            userLocationMarker.setPosition(new LatLng(location.getLatitude(),location.getLongitude()));
        }
    }
}
