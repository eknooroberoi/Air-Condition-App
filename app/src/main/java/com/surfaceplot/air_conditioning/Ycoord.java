package com.surfaceplot.air_conditioning;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//landing activity where graph is displayed

public class Ycoord extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    WebView wv;
    TextView tv;

    DrawerLayout dl;
    NavigationView nv;
    Toolbar tb;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mauthListener;

    DatabaseReference myipRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://air-conditioning-app.firebaseio.com/");
    private static final String TAG = "PostDetailActivity";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ycoord);
        dl = findViewById(R.id.drawer_layoutplot);
        nv = findViewById(R.id.nav_viewplot);
        tb = findViewById(R.id.toolbarplot);

        setSupportActionBar(tb);

        nv.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, tb, R.string.nd_open, R.string.nd_close);
        dl.addDrawerListener(toggle);
        toggle.syncState();

        tv = findViewById(R.id.textView2);
        wv = findViewById(R.id.plotview);
        wv.setWebViewClient(new WebViewClient());
        final WebSettings settings = wv.getSettings();
        settings.setLoadsImagesAutomatically(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            settings.setSafeBrowsingEnabled(false);
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(wv, true);
        }

        // Extras tried for Android 9.0, can be removed if want.
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);
        settings.setBlockNetworkImage(false);

        myipRef.child("ip").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String ip = dataSnapshot.getValue(String.class);
                ip = "http://" + ip + ":5006/sample";
                Toast.makeText(Ycoord.this, "Please Wait! This might take a while!", Toast.LENGTH_LONG).show();
                wv.loadUrl(ip);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
        nv.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Closing Activity")
                    .setMessage("Are you sure you want to close this activity?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finishAffinity();;
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Ycoord.this, MainActivity.class));
                break;
            case R.id.xycoord:
                startActivity(new Intent(Ycoord.this, Xcoord.class));
                break;
            case R.id.surfaceplot:
                startActivity(new Intent(Ycoord.this, Ycoord.class));
                break;
            case R.id.contactus:
                startActivity(new Intent(Ycoord.this, showup.class));
                break;
        }
        return true;
    }
}
