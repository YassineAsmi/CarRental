package com.RouWeis.carrental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SigninActivity extends AppCompatActivity {
private EditText mail;
private EditText pass;
private Button Sign_in;
private Button signup_intent;
private FirebaseAuth Auth;
private DatabaseReference ref;
private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        init();
        Auth =FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("Admin");
        Log.d("SignupActivity", "signing in ...");
        Sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString();
                String password = pass.getText().toString();
                Log.d("SignupActivity", "check if empty");

                if(email.isEmpty()){
                    Toast.makeText(SigninActivity.this,"Invalid Email",Toast.LENGTH_SHORT).show();;

                }
                if(password.isEmpty()){
                    Toast.makeText(SigninActivity.this,"Invalid Password",Toast.LENGTH_SHORT).show();;

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
            Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
            startActivity(intent);
        }
    });
    }

 /*   private void signinConfirmation(final String email ,final String password) {
        Query query = ref.orderByChild("Admin").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists() && snapshot.hasChild("email")){
                    String emailDB = snapshot.child("email").getValue(String.class);
                    if(emailDB.equalsIgnoreCase(email)){
                        signinUser(email,password);
                    }
                    else{
                       // pb.setVisibility(View.INVISIBLE);
                        Toast.makeText(SigninActivity.this,"Admin Email Needed",Toast.LENGTH_SHORT).show();;

                    }
                }
                    else{
                      //  pb.setVisibility(View.INVISIBLE);
                        Toast.makeText(SigninActivity.this,"Admin Email Needed",Toast.LENGTH_SHORT).show();;

                    }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SigninActivity.this,"Error",Toast.LENGTH_SHORT).show();;
             //   pb.setVisibility(View.INVISIBLE);

            }
        });
    }*/

    private void signinUser(String email, String password) {
        Auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                   // pb.setVisibility(View.INVISIBLE);

                    startActivity(new Intent(SigninActivity.this,HomeActivity.class));
                    Log.d("SigninActivity", "Atempt to connect success");

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