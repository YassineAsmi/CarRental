package com.RouWeis.carrental;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Addcars extends AppCompatActivity {
ImageView image;
Button addphoto;
Button btnloc;
ImageButton imgbtn;
ImageView imgv;
Button btnadd;
EditText title;
EditText desc;
TextView txt;
TextView display_location;
FusedLocationProviderClient fusedLocationProviderClient;
FirebaseDatabase rootNode;
DatabaseReference reference;
private static final int GALLERY_REQUEST = 9;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcars);
        image = findViewById(R.id.image);
        btnadd = findViewById(R.id.update);
        imgv = findViewById(R.id.car_view);
        display_location = findViewById(R.id.location_display);
        txt = findViewById(R.id.txt);
        addphoto = findViewById(R.id.update);
        btnloc = findViewById(R.id.btnloc);
        title = findViewById(R.id.title_input);
        desc = findViewById(R.id.desc_input);
        location();
        //Select image from gallery
        imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImgFromGallery();
                txt.setText("");

            }
        });
            

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("cars");
                reference.setValue("test id it work");
            }
        });
    }
    private void pickImgFromGallery() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST || resultCode == RESULT_OK || data != null) {

            //Get selected image uri here
            Uri imageUri = data.getData();
            imgv.setImageURI(imageUri);
        }
    }
    //cheking permisssions
    private void location() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        btnloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(Addcars.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    //getting location
                    fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            Location location = task.getResult();
                            if(location != null){
                                try {
                                    Geocoder geocoder = new Geocoder(Addcars.this, Locale.getDefault());
                                    List<Address> addresses =geocoder.getFromLocation(location.getLatitude(),location.getAltitude(),1);
                                    display_location.setText("Adresse:"+ addresses.get(0).getAddressLine(0)+"  Country : "+addresses.get(0).getCountryName());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    });
                }else {
                    ActivityCompat.requestPermissions(Addcars.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
                }
            }
        });

    }








}
