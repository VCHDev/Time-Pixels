package edu.todo.vch.timepixels;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;

import edu.todo.vch.timepixels.model.FlickrAutentificationClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlickrAutentificationClient client = new FlickrAutentificationClient();

        String testUrl = null;

        try {
            testUrl = client.createTokenRequest();


            System.out.println("-----------------> 1 " + testUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
