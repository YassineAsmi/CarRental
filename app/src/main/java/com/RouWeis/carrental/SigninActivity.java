package com.RouWeis.carrental;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class SigninActivity extends AppCompatActivity {
private EditText mail;
private EditText pass;
private Button Sign_in;
private Button signup_intent;
public FirebaseAuth Auth;
private DatabaseReference ref;
private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        init();
        Auth =FirebaseAuth.getInstance();
        //ref = FirebaseDatabase.getInstance().getReference().child("Admin");
        Log.d("SignupActivity", "signing in ...");
        Sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString();
                String password = pass.getText().toString();
                Log.d("SignupActivity", "check if empty");

                if(email.isEmpty()){
                    Toast.makeText(SigninActivity.this,"Invalid Email",Toast.LENGTH_SHORT).show();
                }
                if(password.isEmpty()){
                    Toast.makeText(SigninActivity.this,"Invalid Password",Toast.LENGTH_SHORT).show();

                }
                //pb.setVisibility(View.VISIBLE);
                Log.d("SignupActivity", "checking if user exist in firebase");
                //signinConfirmation(email,password);
                signinUser(email,password);

            }
        });


    signup_intent.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent2 = new Intent(SigninActivity.this ,SignupActivity.class);
            startActivity(intent2);
        }
    });

}


    private void signinUser(String email, String password) {
        Auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                   // pb.setVisibility(View.INVISIBLE);

                    startActivity(new Intent(SigninActivity.this,Recherche.class));
                    Log.d("SigninActivity", "Attempt to connect success");

                }
                else{
                   // pb.setVisibility(View.INVISIBLE);
                    Toast.makeText(SigninActivity.this,"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
    mail = findViewById(R.id.mail);
    pass = findViewById(R.id.pass);
    Sign_in = findViewById(R.id.sign_in_btn);
    signup_intent = findViewById(R.id.sign_up_intent);
    pb = findViewById(R.id.progressBar);

    }
}