package com.example.air_conditioning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class showup extends AppCompatActivity {

    DrawerLayout dl;
    NavigationView nv;
    TextView eknoor;
    TextView sharath;
    TextView raheem;
    TextView eknoormail;
    TextView sharathmail;
    TextView raheemmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showup);
        dl=findViewById(R.id.drawer_layout);
        dl=findViewById(R.id.nav_view);
        dl=findViewById(R.id.drawer_layout);
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
                String phone=eknoormail.getText().toString();
                String s= "mailto:" + phone;
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse(s));
                startActivity(intent);
            }
        });
        sharathmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=sharathmail.getText().toString();
                String s= "mailto:" + phone;
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse(s));
                startActivity(intent);
            }
        });
        raheemmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=raheemmail.getText().toString();
                String s= "mailto:" + phone;
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse(s));
                startActivity(intent);
            }
        });
    }
}
