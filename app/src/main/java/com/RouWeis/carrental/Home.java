package com.RouWeis.carrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {
    //init var
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout = findViewById(R.id.drawer_layout);

    }
    public void ClickMenu (View view){
        //open drawer
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        //open layout
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view){
        //close drawer
        closeDrawer(drawerLayout);

    }

    private static void closeDrawer(DrawerLayout drawerLayout) {
        //close drawer
        //condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            //whene drawer is open
            //close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickProfil(View view){
        redirectActivity(this,compte.class);
    }
    public void ClickMycars(View view){
        redirectActivity(this,mycars.class);
    }
    public void ClickSearch(View view){
        redirectActivity(this,Recherche.class);
    }
    public void ClickAddcar(View view){
        redirectActivity(this,addcars.class);
    }
    //public void ClickSignout(View view){redirectActivity(this,Signout.class);}

  /*  public static void signout(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Signout");
        builder.setMessage("Are you sure you want to signout ?");
        //yes
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //finish act
                activity.finishAffinity();
                //Exit app
                System.exit(0);
            }
        });
        //no
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();

            }
        });
//show dialog
        builder.show();

    }*/

    private void redirectActivity(Activity activity,Class aclasse) {
//intent
        Intent intent =new Intent(activity,aclasse);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }
    protected void onPause(){
        super.onPause();
        closeDrawer(drawerLayout);
    }
}