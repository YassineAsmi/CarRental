package com.RouWeis.carrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Recherche extends AppCompatActivity {
    private Button signout;
    private Button add;
    private FirebaseAuth Auth;
    List<cars> contactList=new ArrayList<cars>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);
        // -------------------init-----------------------
        signout = findViewById(R.id.signout1);
        add = findViewById(R.id.add);
        Auth =FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recyc);

        // --------------------- RecyclerView-------------------
        MyAdapter myAdapter = new MyAdapter(contactList);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //----------------------Signout------------------------
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.signOut();
                Intent intent2 = new Intent(Recherche.this ,SigninActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
            }
        });
        //--------------------Add---------------------
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Recherche.this ,addcars.class);
                startActivity(intent3);
            }
        });
    }
}