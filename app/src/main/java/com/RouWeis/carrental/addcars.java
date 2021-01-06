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
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class addcars extends AppCompatActivity {
ImageView image;
Button addphoto;
Button btnloc;
FusedLocationProviderClient fusedLocationProviderClient;
private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMESSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcars);
        image = findViewById(R.id.image);
        addphoto = findViewById(R.id.addphoto);
        btnloc = findViewById(R.id.btnloc);
        location();

        addphoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
        String[] permissions ={Manifest.permission.READ_EXTERNAL_STORAGE};
        requestPermissions(permissions,PERMESSION_CODE);

    }
    else {
        pickImageFromGallery();
    }
}
else {
    pickImageFromGallery();
}
            }


        });
    }
//cheking permisssions
    private void location() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        btnloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(addcars.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    //getting location
                    fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            Location location = task.getResult();
                            if(location != null){
                                Geocoder geocoder = new Geocoder(addcars.this, Locale.getDefault());
                                try {
                                    List<Address> addresses =geocoder.getFromLocation(location.getLatitude(),location.getAltitude(),1);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    });
                }else {
                    ActivityCompat.requestPermissions(addcars.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
                }
            }
        });

    }



    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMESSION_CODE:{
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }
                else {
                    Toast.makeText(this,"Permession invalide !",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            image.setImageURI(data.getData());
        }
    }
}
