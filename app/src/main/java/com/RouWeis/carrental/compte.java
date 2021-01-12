package com.RouWeis.carrental;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class compte extends AppCompatActivity {

    private TextView tel;
    private TextView name;
    private TextView mail;
    private FirebaseAuth auth;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);
        name = findViewById(R.id.name1);
        mail = findViewById(R.id.email1);
        tel = findViewById(R.id.phone1);
        auth = FirebaseAuth.getInstance();
        userID=auth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference documentReference = db.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    name.setText(value.getString(""));
                    name.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("readcompte","name:"+name);

                        }
                    });
                    mail.setText(value.getString("Email"));
                Log.d("readcompte","mail:"+mail);
                tel.setText(value.getString("Tel"));
                Log.d("readcompte","tel:"+tel);
                 mail.setText(value.getString("Email"));
                    tel.setText(value.getString("Tel"));

            }
        });


    }
}