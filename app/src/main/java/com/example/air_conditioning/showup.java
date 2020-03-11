package com.example.air_conditioning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class showup extends AppCompatActivity {

    DrawerLayout dl;
    NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showup);
        dl=findViewById(R.id.drawer_layout);
        dl=findViewById(R.id.nav_view);
        dl=findViewById(R.id.drawer_layout);
    }
}
