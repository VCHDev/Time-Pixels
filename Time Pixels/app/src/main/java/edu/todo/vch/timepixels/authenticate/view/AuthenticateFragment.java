package edu.todo.vch.timepixels.authenticate.view;

import android.content.Context;
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
    private Button signInAppBtn;
    private Button joinAppBtn;
    private ViewPager infoPager;

    private AuthenticateInfoPageAdapter infoPageAdapter;

    private String[] infoData;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        infoData = getResources().getStringArray(R.array.authenticateInfoArray);
        Log.d(TAG, "array data " + Arrays.toString(infoData));
        infoPageAdapter = new AuthenticateInfoPageAdapter(getActivity().getSupportFragmentManager(), infoData);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_authenticate, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        signInAppBtn = (Button) view.findViewById(R.id.signin_button);
        joinAppBtn = (Button) view.findViewById(R.id.join_button);

        infoPager = (ViewPager) view.findViewById(R.id.info_pager);
        infoPager.setAdapter(infoPageAdapter);
        infoPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "page scrolled  " + position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        signInAppBtn.setOnClickListener(new SigninAppBtnListener());
        joinAppBtn.setOnClickListener(new JoinAppBtnListener());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private class SigninAppBtnListener implements View.OnClickListener{

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
