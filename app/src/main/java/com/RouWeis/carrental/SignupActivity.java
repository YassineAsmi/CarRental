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

public class SignupActivity extends AppCompatActivity {
    private EditText name;
    private EditText mail;
    private EditText pass;
    private Button Sign_up;
    private Button A_exist;
    private FirebaseAuth Auth;
    private ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        init();

        if(Auth.getCurrentUser() !=null){
            Log.d("SigninActivity", "starting Home Activity ...");
            startActivity(new Intent(SignupActivity.this,HomeActivity.class));
            finish();
        }

        Log.d("SigninActivity", "starting button");
        Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString();
                String password = pass.getText().toString();
                Log.d("SigninActivity", "checking if empty");
                if(email.isEmpty()){
                    Toast.makeText(SignupActivity.this,"Invalid Email",Toast.LENGTH_SHORT).show();;
                    return;
                }
                if(password.isEmpty()){
                    Toast.makeText(SignupActivity.this,"Invalid Password",Toast.LENGTH_SHORT).show();;
                    return;
                }

           //     pb.setVisibility(View.VISIBLE);
                Log.d("SigninActivity", "creating user in Firebase");
                Auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                          //  pb.setVisibility(View.INVISIBLE);
                            Toast.makeText(SignupActivity.this,"Registration Success",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignupActivity.this,HomeActivity.class));
                            Log.d("SigninActivity", "User Created");
                        }
                        else{
                           // pb.setVisibility(View.INVISIBLE);
                            Toast.makeText(SignupActivity.this,"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }
        });

    }
    private void init() {
        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);
        pass = findViewById(R.id.pass);
        Sign_up = findViewById(R.id.sign_up_btn);
        Auth = FirebaseAuth.getInstance();
        pb = findViewById(R.id.progressBar);

    }
}