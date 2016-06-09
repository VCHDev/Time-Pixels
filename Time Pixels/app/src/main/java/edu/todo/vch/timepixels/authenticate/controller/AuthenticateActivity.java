package edu.todo.vch.timepixels.authenticate.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.todo.vch.timepixels.R;
import edu.todo.vch.timepixels.authenticate.model.AuthenticateClient;
import edu.todo.vch.timepixels.authenticate.view.AuthenticateFragment;

/**
 * Authenticate Activity, the main controller for the application.
 */
public class AuthenticateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startRequestForTokens();

        if (null != findViewById(R.id.main_container ))
            startFragmentTransaction();
    }

    private void startFragmentTransaction(){
       getSupportFragmentManager()
               .beginTransaction()
               .replace(R.id.main_container,
                        new AuthenticateFragment(),
                        AuthenticateFragment.class.getSimpleName())
               .commit();
    }

    private void startRequestForTokens(){
         new Thread(new Runnable() {
            @Override
            public void run() {
                 new AuthenticateClient().authenticate();
            }
        }).start();
    }
}
