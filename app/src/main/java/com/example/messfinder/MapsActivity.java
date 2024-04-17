package com.example.messfinder;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.messfinder.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private GoogleMap mMap;
    EditText loction_input;

    ListView listView;
    TextView textView;

    public int value = 0;
    private ActivityMapsBinding binding;
    String Mess_name [] = { "Krishna Pure Veg","Sarkar Misal House","Sadguru Pure Veg","Ashirvad Pure Veg",
            "Lakshmi Pure veg","Swami Hotel","Sanjog Hotel","Yash Pure Veg"};
    String Rate_Rice_plate[] = {"70₹","60₹","60₹","70₹","70₹","100₹","80₹","80₹"};
    String Rate_snacks [] = { "Not_Available","20-40₹","20-35₹","Not_Available","Not_Available",
            "50-100₹", "Not-Available","20-25₹"};
    String Rating[] = {"*****","***","**","*****","**","****","*","**"};
    private android.widget.Toast Toast;
    private Marker tagMarker[]  = new Marker[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loction_input = findViewById(R.id.loction_input);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        listView=(ListView)findViewById(R.id.listView);
        textView=(TextView)findViewById(R.id.textView);


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.activity_list_item, android.R.id.text1, Mess_name);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                String value=adapter.getItem(position);
                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();

            }
        });


        loction_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                return false;
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        double Latl[] ={18.455676902328005,18.456248949808582,18.456948864122268,18.45739303903353,18.459472613005005,18.45703141140265, 18.463693111148306,18.463516130438535};
        double Long[] = {73.87234684876951,73.8695088971252,73.86948761248853, 73.86935280978953,73.86929129709137,73.86936156913751,73.86831021924804,73.86805526124338};
        LatLng k = null;
        for( int i = 0;i<8;i++)
        {
             k = new LatLng(Latl[i],Long[i]);
             tagMarker[i] = mMap.addMarker(new MarkerOptions().position(k).title(Mess_name[i]));
             tagMarker[i].setTag(i);
        }
        mMap.setOnMarkerClickListener(this);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(k));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(k,16f));

    }


    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Integer tag = (Integer) marker.getTag();

        if(tag != null){
            Toast t = Toast.makeText(getApplicationContext(), "Details_Found...", Toast.LENGTH_SHORT);
            t.setMargin(50, 50);
            t.show();
            Intent I = new Intent(MapsActivity.this , game.class);
            I.putExtra("name_key1", Mess_name[tag]);
            I.putExtra("name_key2", Rate_Rice_plate[tag]);
            I.putExtra("reg_key1", Rate_snacks[tag]);
            I.putExtra("reg_key2", Rating[tag]);
            startActivity(I);
        }

            return false;
        }
}