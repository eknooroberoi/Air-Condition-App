package com.surfaceplot.air_conditioning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText email, pswd;
    Button button;
    TextView text1;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mauthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.emailin);
        pswd=findViewById(R.id.pswdin);
        button=findViewById(R.id.buttonin);
        text1=findViewById(R.id.textin);
        mauthListener =new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=mAuth.getCurrentUser();
                if( user!=null){
                    Toast.makeText(MainActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Ycoord.class));
                }
                else
                    Toast.makeText(MainActivity.this,"You have to login first.",Toast.LENGTH_SHORT).show();
            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailtext= email.getText().toString();
                String pswdtext= pswd.getText().toString();
                if(emailtext.isEmpty()&&pswdtext.isEmpty()){
                    Toast.makeText(MainActivity.this,"Enter both the fields", Toast.LENGTH_SHORT).show();
                }
                else if(emailtext.isEmpty()){
                    email.setError("Please Enter an email ID");
                    email.requestFocus();
                }
                else if(pswdtext.isEmpty()){
                    pswd.setError("Please Enter a password");
                    pswd.requestFocus();
                }
                else if(!emailtext.isEmpty()&&!pswdtext.isEmpty()){
                    mAuth.signInWithEmailAndPassword(emailtext,pswdtext).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Invalid username or password", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(MainActivity.this, Ycoord.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mauthListener);
    }
}
