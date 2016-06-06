package edu.todo.vch.timepixels;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.todo.vch.timepixels.model.FlickrAutentificationClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FlickrAutentificationClient client = new FlickrAutentificationClient();

        String testUrl = null;


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                client.autentificate();
            }
        });

        t.start();


    }
}
