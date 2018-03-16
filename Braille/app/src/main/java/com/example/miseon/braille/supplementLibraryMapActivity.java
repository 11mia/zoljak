package com.example.miseon.braille;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class supplementLibraryMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    LatLng newloc;
    String address;
    String name;
    String detail;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_map);
        setTitle("점자 도서관 상세 정보");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent it = getIntent();
        name = it.getStringExtra("name");
        address = it.getStringExtra("address");
        detail = it.getStringExtra("detail");
        //Toast.makeText(this,"name : "+name+", address : "+address,Toast.LENGTH_LONG).show();
        TextView tvname = (TextView)findViewById(R.id.name);
        TextView tvdetail = (TextView)findViewById(R.id.detail);
        tvname.setText(name);
        tvdetail.setText(detail);




        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //onBackPressed();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        final Geocoder geocoder = new Geocoder(this);
        Location loc = findGeoPoint(this,detail);
        Log.d("name : ",detail);
        newloc = new LatLng(loc.getLatitude(),loc.getLongitude());

        if(loc.getLatitude()==0&&loc.getLongitude()==0){
            Toast toast = Toast.makeText(this,"해당되는 주소 정보가 없습니다.",Toast.LENGTH_SHORT);
            ViewGroup group = (ViewGroup) toast.getView();
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
            toast.show();
            return;
        }

        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(newloc).title(name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newloc));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(17));
    }

    public static Location findGeoPoint(Context mcontext, String address) {
        Geocoder coder = new Geocoder(mcontext);
        List<Address> addr = null;
        Location loc = new Location("");

        try {
            addr = coder.getFromLocationName(address, 1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (addr != null) {
            Log.v("addr!=null","true");
            for (int i = 0; i < addr.size(); i++) {
                Address lating = addr.get(i);
                double lat = lating.getLatitude(); // 위도가져오기
                double lon = lating.getLongitude(); // 경도가져오기
                loc.setLatitude(lat);
                loc.setLongitude(lon);
            }
        }
        return loc;





    }


}


