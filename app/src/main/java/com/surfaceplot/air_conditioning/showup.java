package com.surfaceplot.air_conditioning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class showup extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout dl;
    NavigationView nv;
    Toolbar tb;
    TextView eknoor;
    TextView sharath;
    TextView raheem;
    TextView eknoormail;
    TextView sharathmail;
    TextView raheemmail;

    //contact us activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showup);

        dl = findViewById(R.id.drawer_layoutcontactus);
        nv = findViewById(R.id.nav_viewcontactus);
        tb = findViewById(R.id.toolbarcontactus);

        setSupportActionBar(tb);
        nv.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, tb, R.string.nd_open, R.string.nd_close);
        dl.addDrawerListener(toggle);
        toggle.syncState();

        eknoor=findViewById(R.id.textView5);
        sharath=findViewById(R.id.textView6);
        raheem=findViewById(R.id.textView7);
        eknoormail=findViewById(R.id.textView8);
        sharathmail=findViewById(R.id.textView9);
        raheemmail=findViewById(R.id.textView10);

        eknoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=eknoor.getText().toString();
                String s= "tel:+91" + phone;
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(s));
                startActivity(intent);
            }
        });
        sharath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=sharath.getText().toString();
                String s= "tel:+91" + phone;
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(s));
                startActivity(intent);
            }
        });
        raheem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=raheem.getText().toString();
                String s= "tel:+91" + phone;
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(s));
                startActivity(intent);
            }
        });
        eknoormail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              sendMail1();
            }
        });
        sharathmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail2();
            }
        });
        raheemmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail3();
            }
        });
        nv.setNavigationItemSelectedListener(this);
    }
    private void sendMail1(){
        String recepient= eknoormail.getText().toString();
        String recepientlist[]=recepient.split(",");
        Intent intent= new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,recepientlist);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, " choose an email client"));
    }
    private void sendMail2(){
        String recepient= sharathmail.getText().toString();
        String recepientlist[]=recepient.split(",");
        Intent intent= new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,recepientlist);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, " choose an email client"));
    }
    private void sendMail3(){
        String recepient= raheemmail.getText().toString();
        String recepientlist[]=recepient.split(",");
        Intent intent= new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,recepientlist);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, " choose an email client"));
    }
    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(showup.this, MainActivity.class));
                break;
            case R.id.xycoord:
                startActivity(new Intent(showup.this, Xcoord.class));
                break;
            case R.id.surfaceplot:
                startActivity(new Intent(showup.this, Ycoord.class));
                break;
            case R.id.contactus:
                startActivity(new Intent(showup.this, showup.class));
                break;
        }
        return true;
    }
}
