package edu.todo.vch.timepixels;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.todo.vch.timepixels.model.FlickrAutentificationClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startRequestForTokens();

    }

    private void startRequestForTokens(){
        Thread startRequest = new Thread(new Runnable() {
            @Override
            public void run() {
                FlickrAutentificationClient autentificationClient = new FlickrAutentificationClient();
                autentificationClient.autentificate();
            }
        });
        startRequest.start();
    }
}
