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

//signup activity

public class SignUp extends AppCompatActivity {

    EditText email, pswd;
    Button button;
    TextView text1;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.emailup);
        pswd=findViewById(R.id.pswdup);
        button=findViewById(R.id.buttonup);
        text1=findViewById(R.id.textup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailtext= email.getText().toString();
                String pswdtext= pswd.getText().toString();
                if(pswdtext.length()<7) {
                    Toast.makeText(SignUp.this, "Password too short", Toast.LENGTH_SHORT).show();
                }
                if(emailtext.isEmpty()&&pswdtext.isEmpty()){
                    Toast.makeText(SignUp.this,"Enter both the fields", Toast.LENGTH_SHORT).show();
                }
                else if(emailtext.isEmpty()){
                    email.setError("Please Enter an email ID");
                    email.requestFocus();
                }
                else if(pswdtext.isEmpty()){
                    pswd.setError("Please Enter a password");
                    pswd.requestFocus();
                }
                else if((!emailtext.isEmpty()&&!pswdtext.isEmpty())&&pswdtext.length()>7){
                    mAuth.createUserWithEmailAndPassword(emailtext,pswdtext).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(SignUp.this,"Sign Up Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(SignUp.this, Ycoord.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(SignUp.this,"Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, MainActivity.class));
            }
        });

    }

}
