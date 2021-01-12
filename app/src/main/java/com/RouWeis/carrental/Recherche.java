package com.RouWeis.carrental;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Recherche extends AppCompatActivity {
    private Button signout;
    private Button add;
    private Button compte;
    private FirebaseAuth Auth;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);
        // -------------------init-----------------------
        signout = findViewById(R.id.signout1);
        add = findViewById(R.id.add);
        compte = findViewById(R.id.Compte);
        Auth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recyc);
        firebaseFirestore = FirebaseFirestore.getInstance();
        //--------------------Compte Button-----------------
        compte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent5 = new Intent(Recherche.this, compte.class);
                startActivity(intent5);
            }
        });


        // --------------------- RecyclerView-------------------
        Query query = firebaseFirestore.collection("cars").orderBy("Title");
        FirestoreRecyclerOptions<cars> options = new FirestoreRecyclerOptions.Builder<cars>().setQuery(query, cars.class).build();

        adapter = new FirestoreRecyclerAdapter<cars, CarsViewHolder>(options) {
            @NonNull
            @Override
            public CarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_home, parent, false);
                return new CarsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull CarsViewHolder holder, int i, @NonNull cars cars) {
                holder.title.setText("Title: "+cars.getTitle());
                Log.w("recyc", "title"+cars.getTitle());
                holder.adresse.setText("Adresse"+cars.getAdress());
                Log.w("recyc", "title"+cars.getAdress());

            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Recherche.this));
        recyclerView.setAdapter(adapter);

        //----------------------Signout------------------------

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.signOut();
                Intent intent2 = new Intent(Recherche.this, SigninActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
            }
        });
        //--------------------Add---------------------
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Recherche.this, addcars.class);
                startActivity(intent3);
            }
        });

    }

   class CarsViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView adresse;
        private ImageView img;

        public CarsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_home);
            adresse = itemView.findViewById(R.id.adresse_home);
            img = itemView.findViewById(R.id.img_home);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}

