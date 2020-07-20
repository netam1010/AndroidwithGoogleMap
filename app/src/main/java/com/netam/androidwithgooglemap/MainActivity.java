package com.netam.androidwithgooglemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText tx_source , tx_destination;
    private String source , destination;
    private Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx_source = findViewById(R.id.source);
        tx_destination = findViewById(R.id.destination);
        search =findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*Get value form Input using Id*/
                source = tx_source.getText().toString();
                destination = tx_destination.getText().toString();

                if (source.equals("") || destination.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Fill address input ",Toast.LENGTH_LONG).show();
                }
                else
                {
                    DisplayTrack(source,destination);
                }
            }
        });

    } // End Oncreate

    private void DisplayTrack(String source, String destination) {

        try {
            Uri i = Uri.parse("https://www.google.co.in/maps/dir/"+ source + "/" + destination);
            Intent intent = new Intent(Intent.ACTION_VIEW,i);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
           Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}