package com.example.apurva.mediamanager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.Window;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.apurva.mediamanager.Menu_fragment.HomeFragment;
import com.example.apurva.mediamanager.Menu_fragment.NotificationFragment;
import com.example.apurva.mediamanager.Menu_fragment.SettingsFragment;
import com.example.apurva.mediamanager.Menu_fragment.ToolsFragment;

public class MenuActivity extends AppCompatActivity {


    //private android.support.v7.widget.Toolbar mTopToolbar;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            android.support.v4.app.FragmentManager fragmentManager= getSupportFragmentManager();
            FragmentTransaction transaction= fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.fragment_container,new HomeFragment()).commit();
                    return true;

                case R.id.navigation_notifications:
                    transaction.replace(R.id.fragment_container,new NotificationFragment()).commit();
                    return true;

                case R.id.navigation_settings:
                    transaction.replace(R.id.fragment_container,new SettingsFragment()).commit();
                    return true;

                case R.id.navigation_gallery:
                    Intent intent=new Intent();
                    intent.setType("image/*");;
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    //startActivityForResult(Intent.createChooser(intent," "), PICK_IMAGE);
                    //transaction.replace(R.id.fragment_container,new SettingsFragment()).commit();
                    return true;


            }
            return false;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_menu);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        android.support.v4.app.FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container,new HomeFragment()).commit();
    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.action_add:
                Toast.makeText(getApplicationContext(),"To add new account",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_image:
                Toast.makeText(getApplicationContext(),"To view profile",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
