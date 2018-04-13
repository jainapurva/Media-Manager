package com.example.apurva.mediamanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.apurva.mediamanager.LoginActivity;

import org.jetbrains.annotations.Nullable;

/**
 * Created by Apurva on 10-02-2018.
 */

public class SplashScreen extends AppCompatActivity {

    @Override

    protected void onCreate(@Nullable Bundle savedBundleInstance){
        super.onCreate(savedBundleInstance);
        Intent intent= new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();


    }
}
