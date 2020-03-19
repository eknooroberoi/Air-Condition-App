package com.example.air_conditioning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Xcoord extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private Button button;
    private EditText xcoordinates[];
    private EditText ycoordinates[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xcoord);
        button = findViewById(R.id.sendx);
        final String[] XX={"X1","X2","X3","X4","X5","X6","X7","X8"};
        final String[] YY={"Y1","Y2","Y3","Y4","Y5","Y6","Y7","Y8"};
        xcoordinates = new EditText[]{
                (EditText) findViewById(R.id.x1),
                (EditText) findViewById(R.id.x2),
                (EditText) findViewById(R.id.x3),
                (EditText) findViewById(R.id.x4),
                (EditText) findViewById(R.id.x5),
                (EditText) findViewById(R.id.x6),
                (EditText) findViewById(R.id.x7),
                (EditText) findViewById(R.id.x8)
        };
        ycoordinates = new EditText[]{
                (EditText) findViewById(R.id.y1),
                (EditText) findViewById(R.id.y2),
                (EditText) findViewById(R.id.y3),
                (EditText) findViewById(R.id.y4),
                (EditText) findViewById(R.id.y5),
                (EditText) findViewById(R.id.y6),
                (EditText) findViewById(R.id.y7),
                (EditText) findViewById(R.id.y8)
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = false;
                for (int i = 0; i < 8; i++) {
                    if (xcoordinates[i].getText().toString().equals("") || ycoordinates[i].getText().toString().equals("")) {
                        Toast.makeText(Xcoord.this, "Check if all columns are filled!", Toast.LENGTH_SHORT).show();
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    DatabaseReference myXRef = database.getReference("XCOORD");
                    Map<String, String> xmap = new HashMap<String, String>();
                    for (int index = 0; index < 8; index++) {
                        xmap.put(XX[index], xcoordinates[index].getText().toString());
                    }
                    myXRef.setValue(xmap);
                    DatabaseReference myYRef = database.getReference("YCOORD");
                    Map<String, String> ymap = new HashMap<String, String>();
                    for (int index = 0; index < 8; index++) {
                        ymap.put(YY[index], ycoordinates[index].getText().toString());
                    }
                    myYRef.setValue(ymap);
                }
            }
        });
    }
}
