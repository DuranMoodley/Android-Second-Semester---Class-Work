/*
StudentMap.java
Displays a map to the the students and tracks students location, draws polylines
Student: Duran Moodley  Student Number: 13016335
Lecturer : Rajesh Chanderman
Assignment : 1
Date Updated : 9/4/16
 */

package attendance.student.prjstudentattendancerecord;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

public class StudentMap extends FragmentActivity implements OnMapReadyCallback , com.google.android.gms.location.LocationListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
    private GoogleMap mMap;
    private PolylineOptions polylineOptions;
    private Polyline polyline;
    private GoogleApiClient googleApiClient;
    private LocationRequest objLocationRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        createLocationRequest();
        setUpGoogleApiClient();
    }
    //**********************************************************************************************
    private void createLocationRequest()
    {
        objLocationRequest = LocationRequest.create()
                .setInterval(10000)
                .setFastestInterval(5000).setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
    //**********************************************************************
    private void setUpGoogleApiClient()
    {
        googleApiClient = new GoogleApiClient.Builder(StudentMap.this).addApi(LocationServices.API).
                addConnectionCallbacks(this).
                addOnConnectionFailedListener(this).build();
        googleApiClient.connect();
    }
    //**********************************************************************
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        //Set polyline options
        mMap.setMyLocationEnabled(true);
        polylineOptions = new PolylineOptions().width(10).color(Color.BLUE).geodesic(true);
        polyline = mMap.addPolyline(polylineOptions);
        polyline.setVisible(true);
    }
    //*************************************************************************
    @Override
    public void onConnected(@Nullable Bundle bundle)
    {
        startLocationUpdates();
    }
    //*************************************************************************
    @Override
    public void onConnectionSuspended(int i)
    {
        //Blank Method
    }
    //*************************************************************************
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {
        Toast.makeText(this, "Sorry, A problem has occured. Please Restart Application.", Toast.LENGTH_SHORT).show();
    }
    //*************************************************************************
    @Override
    public void onLocationChanged(Location location)
    {
        if(location != null)
        {
            LatLng newPoint = new LatLng(location.getLatitude(), location.getLongitude());
            List<LatLng> points = polyline.getPoints();
            points.add(newPoint);
            polyline.setPoints(points);
        }
        else{
            Toast.makeText(StudentMap.this,"Please put on your location settings",Toast.LENGTH_LONG).show();
        }
    }
    //*************************************************************************
    private void stopLocationUpdates()
    {
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);
    }
    //***********************************************************************
    private void startLocationUpdates()
    {
        if (ActivityCompat.checkSelfPermission(StudentMap.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(StudentMap.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, objLocationRequest, this);
    }
    //***********************************************************************
    @Override
    public void onPause()
    {
        super.onPause();
        stopLocationUpdates();
    }
    //**************************************************************************************
    @Override
    public void onResume()
    {
        super.onResume();
        if(googleApiClient.isConnected())
        {
            startLocationUpdates();
        }
    }
    //**************************************************************************************
    @Override
    public void onStart()
    {
        super.onStart();
        googleApiClient.connect();
    }
}
