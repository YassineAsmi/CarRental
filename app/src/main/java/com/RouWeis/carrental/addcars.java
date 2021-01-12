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
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class addcars extends AppCompatActivity {
    public String imageurl;
    ImageView image;
    Button addphoto;
    Button btnloc;
    ImageView imgv;
    Button btnadd;
    EditText title;
    EditText desc;
    EditText price;
    TextView txt;
    TextView display_location;
    RadioButton auto;
    RadioButton man;
    FusedLocationProviderClient fusedLocationProviderClient;
    private StorageReference StorageRef;
    private static final int GALLERY_REQUEST = 9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcars);
        image = findViewById(R.id.image);
        man = findViewById(R.id.man);
        auto = findViewById(R.id.auto);
        btnadd = findViewById(R.id.btn_add_info);
        imgv = findViewById(R.id.car_view);
        price = findViewById(R.id.prix);
        display_location = findViewById(R.id.location_display);
        txt = findViewById(R.id.txt);
        addphoto = findViewById(R.id.btn_add_info);
        btnloc = findViewById(R.id.btnloc);
        title = findViewById(R.id.title_input);
        desc = findViewById(R.id.desc_input);
        StorageRef = FirebaseStorage.getInstance().getReference();
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
                String titleDB = title.getText().toString();
                String descDB = desc.getText().toString();
                String locDB = display_location.getText().toString();
                String manDB = man.getText().toString();
                String autoDB = auto.getText().toString();
                String priceDB = price.getText().toString();
                String imageDB = imageurl;
                cars car = new cars();

                if (man.isChecked()) {
                    car.setBoite(manDB);
                } else {
                    car.setBoite(autoDB);
                }
                car.setTitle(titleDB);
                car.setDesc(descDB);
                car.setAdress(locDB);
                car.setPrice(priceDB);
                car.setImg(imageDB);
                Map<String, Object> carsH = new HashMap<>();
                carsH.put("Title:", car.getTitle());
                Log.w("hashmap", "title" + titleDB);
                carsH.put("Description:", car.getDesc());
                Log.w("hashmap", "title" + titleDB);
                carsH.put("boite:", car.getBoite());
                carsH.put("Adress:", car.getAdress());
                carsH.put("Price:", car.getPrice());
                carsH.put("Image:", car.getImg());


                FirebaseFirestore db = FirebaseFirestore.getInstance();

                // Add a new document with a generated ID
                db.collection("cars")
                        .add(carsH)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                                Intent intent2 = new Intent(addcars.this, Recherche.class);
                                startActivity(intent2);
                                Toast.makeText(getApplicationContext(), "Your car Added Succefully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("TAG", "Error adding document", e);
                            }
                        });
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST || resultCode == RESULT_OK || data != null) {

            //Get selected image uri here
            Uri imageUri = data.getData();
            imgv.setImageURI(imageUri);
            Log.d("photo", "imageuri " + imageUri);

            uploadpic(imageUri);

        }
    }


    //cheking permisssions
    private void location() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        btnloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(addcars.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //getting location
                    fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            Location location = task.getResult();
                            if (location != null) {
                                try {
                                    Geocoder geocoder = new Geocoder(addcars.this, Locale.getDefault());
                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getAltitude(), 1);
                                    display_location.setText(addresses.get(0).getAddressLine(0) + "  Country : " + addresses.get(0).getCountryName());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    });
                } else {
                    ActivityCompat.requestPermissions(addcars.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });

    }






    private void pickImgFromGallery() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST);
    }

    private void uploadpic(Uri uri) {
        final String randomkey = UUID.randomUUID().toString();
        StorageReference riversRef = StorageRef.child("/images" + randomkey);

        riversRef.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                imageurl = uri.toString();
                                Log.d("photo", "imageuri insde uplaod "+imageurl );
                                Toast.makeText(getApplicationContext(), "Image Uploaded Successfully !!", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Toast.makeText(getApplicationContext(), "Error on Uplaoding Image", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}