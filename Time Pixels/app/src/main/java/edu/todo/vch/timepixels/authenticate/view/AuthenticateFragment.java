package edu.todo.vch.timepixels.authenticate.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;

import edu.todo.vch.timepixels.R;
import edu.todo.vch.timepixels.authenticate.adapter.AuthenticateInfoPageAdapter;

/**
 * AuthenticateFragment.
 */
public class AuthenticateFragment extends Fragment {
    private static final String TAG = AuthenticateFragment.class.getSimpleName();

    private AuthenticateInfoPageAdapter infoPageAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] infoData = getResources().getStringArray(R.array.authenticateInfoArray);
        infoPageAdapter = new AuthenticateInfoPageAdapter(getActivity().getSupportFragmentManager(), infoData);

        Log.d(TAG, "array data " + Arrays.toString(infoData));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_authenticate, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button signInAppBtn = (Button) view.findViewById(R.id.sign_in_button);
        Button joinAppBtn = (Button) view.findViewById(R.id.join_button);

        ViewPager infoPager = (ViewPager) view.findViewById(R.id.info_pager);
        infoPager.setAdapter(infoPageAdapter);

        signInAppBtn.setOnClickListener(new SignInAppBtnListener());
        joinAppBtn.setOnClickListener(new JoinAppBtnListener());
    }

    private class SignInAppBtnListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            createToast("Sign In");
        }
    }

    private class JoinAppBtnListener implements  View.OnClickListener {

        @Override
        public void onClick(View v) {
            createToast("Join App");
        }
    }

    private void createToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
