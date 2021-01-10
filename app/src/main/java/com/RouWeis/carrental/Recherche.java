package com.RouWeis.carrental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Recherche extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //menu var
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    private Button signout;
    private Button add;
    private FirebaseAuth Auth;
    List<Cars> contactList=new ArrayList<Cars>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);
        //hooks
        drawerLayout =findViewById(R.id.drawer_layout);
        navigationView =findViewById(R.id.nav_view);
        toolbar =findViewById(R.id.toolbar);

//toolbar
        setSupportActionBar(toolbar);
//navigation drawer menu

        //hide or show menu
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.signout);
        menu.findItem(R.id.mycars);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.compte);

        // -------------------init-----------------------
        signout = findViewById(R.id.signout);
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
                Intent intent3 = new Intent(Recherche.this , Addcars.class);
                startActivity(intent3);
            }
        });

        }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.compte:
                Intent intent = new Intent(this, Compte.class);
                startActivity(intent);
                break;
            case R.id.add_car:
                intent = new Intent(this, Addcars.class);
                startActivity(intent);
                break;
            case R.id.mycars:
                intent = new Intent(this, Mycars.class);
                startActivity(intent);
                break;
           /* case R.id.help:
                intent = new Intent(this, Help.class);
                startActivity(intent);
                break;*/
            case R.id.signout:
                intent = new Intent(this, SignupActivity.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

