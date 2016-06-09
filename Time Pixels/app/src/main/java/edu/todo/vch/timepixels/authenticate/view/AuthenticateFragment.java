package edu.todo.vch.timepixels.authenticate.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Arrays;

import edu.todo.vch.timepixels.R;
import edu.todo.vch.timepixels.authenticate.adapter.AuthenticateInfoPageAdapter;

/**
 * AuthenticateFragment.
 */
public class AuthenticateFragment extends Fragment {
    private static final String TAG = AuthenticateFragment.class.getSimpleName();

    private LinearLayout pageDotIndicatorLayout;
    private ImageView[] dotsPageIndicators;
    private int dotsCount;

    private AuthenticateInfoPageAdapter infoPageAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] infoData = getResources().getStringArray(R.array.authenticateInfoArray);
        infoPageAdapter = new AuthenticateInfoPageAdapter(getActivity().getSupportFragmentManager(), infoData);
        dotsCount = infoPageAdapter.getCount();
        dotsPageIndicators = new ImageView[dotsCount];

        Log.d(TAG, "array data " + Arrays.toString(infoData));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_authenticate, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        pageDotIndicatorLayout = (LinearLayout) view.findViewById(R.id.view_page_indicator);

        Button signInAppBtn = (Button) view.findViewById(R.id.sign_in_button);
        Button joinAppBtn = (Button) view.findViewById(R.id.join_button);

        ViewPager infoPager = (ViewPager) view.findViewById(R.id.info_pager);
        infoPager.setAdapter(infoPageAdapter);
        infoPager.addOnPageChangeListener(new ViewPagerChangeListener());
        buildUIPageIndicator();

        signInAppBtn.setOnClickListener(new SignInAppBtnListener());
        joinAppBtn.setOnClickListener(new JoinAppBtnListener());
    }

    private void buildUIPageIndicator(){
        for (int i = 0 ; i < dotsCount; i++) {
            dotsPageIndicators[i] = new ImageView(getContext());
            dotsPageIndicators[i].setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.unselected_dot_item, null));

            pageDotIndicatorLayout.addView(dotsPageIndicators[i], createLayoutParams());
        }

        dotsPageIndicators[0].setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.selected_dot_item, null));
    }

    @NonNull
    private LinearLayout.LayoutParams createLayoutParams() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(12, 0, 12, 0);
        return params;
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

    private class ViewPagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            for (int i = 0 ; i <  dotsCount; i++)
                dotsPageIndicators[i].setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.unselected_dot_item, null));

            dotsPageIndicators[position].setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.selected_dot_item, null));
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
