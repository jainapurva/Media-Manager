package com.example.apurva.mediamanager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button register_button;
    String email,password;
    EditText email_input, password_input;
    TextView sign_in;
    Intent intent;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        Log.d("1","line 1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar= (ProgressBar) findViewById(R.id.login_progress);
        register_button= findViewById(R.id.register_button);
        email_input= findViewById(R.id.email);
        password_input= findViewById(R.id.password);
        sign_in=findViewById(R.id.textViewSignIn);

        firebaseAuth=FirebaseAuth.getInstance();


        register_button.setOnClickListener(this);
        sign_in.setOnClickListener(this);
    }

    private void registerUser(){
        email=email_input.getText().toString().trim();
        password=password_input.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(getApplicationContext(),"Please enter email",
                    Toast.LENGTH_SHORT).show();
            email_input.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(getApplicationContext(),"Please enter password",
                    Toast.LENGTH_SHORT).show();
            password_input.requestFocus();
            return;
        }

        if(password.length()<6){
            Toast.makeText(getApplicationContext(),"Minimum length of password should be 6",
                    Toast.LENGTH_SHORT).show();
            password_input.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                            intent=new Intent(getApplicationContext(),UserDetailActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }else {
                            //progressDialog.dismiss();
                            //Toast.makeText(getApplicationContext(),
                                    //"Failed to register"+ task.getException(),Toast.LENGTH_SHORT).show();
                            //intent=new Intent(getApplicationContext(),MenuActivity.class);
                            //startActivity(intent);

                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(), "Email already registered, signing in",
                                        Toast.LENGTH_SHORT).show();
                                userLogin();
                            }else{
                                Toast.makeText(getApplicationContext(),task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void userLogin(){

        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.signInWithEmailAndPassword(email,password).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    intent=new Intent(getApplicationContext(),MenuActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);


                }else {
                    //signIn failed
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        if(view==register_button){
            registerUser();
        }

        if(view==sign_in){
            //open signin activity
        }
    }
}