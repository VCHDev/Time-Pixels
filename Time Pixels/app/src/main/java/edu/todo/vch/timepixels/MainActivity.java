package edu.todo.vch.timepixels;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.todo.vch.timepixels.model.FlickrClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlickrClient client = new FlickrClient();

        String testUrl = null;



            testUrl = client.generateHMACSha1();



        System.out.println("-----------------> 1 " + testUrl);
    }
}
