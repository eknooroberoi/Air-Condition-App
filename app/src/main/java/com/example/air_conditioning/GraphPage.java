package com.example.air_conditioning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class GraphPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button lobt;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mauthListener;
    DrawerLayout dl;
    NavigationView nv;
    Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_page);
        dl=findViewById(R.id.drawer_layout);
        nv=findViewById(R.id.nav_view);
        tb=findViewById(R.id.toolbar);

        setSupportActionBar(tb);

        nv.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, dl, tb, R.string.nd_open, R.string.nd_close);
        dl.addDrawerListener(toggle);
        toggle.syncState();

        lobt=findViewById(R.id.logoutid);
        lobt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(GraphPage.this, MainActivity.class));
            }
        });
        nv.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if(dl.isDrawerOpen(GravityCompat.START)){
            dl.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(GraphPage.this, MainActivity.class));
                break;
            case R.id.xycoord:
                startActivity(new Intent(GraphPage.this, Xcoord.class));
                break;
            case R.id.surfaceplot:
                startActivity(new Intent(GraphPage.this, Ycoord.class));
                break;
            case R.id.contactus:
                startActivity(new Intent(GraphPage.this, showup.class));
                break;
        }
        return true;
    }
}
