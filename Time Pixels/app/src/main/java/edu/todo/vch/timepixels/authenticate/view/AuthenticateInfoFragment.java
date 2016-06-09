package edu.todo.vch.timepixels.authenticate.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.todo.vch.timepixels.R;

/**
 * Authenticate Info Fragment used to show  introduction information about the application.
 */
public class AuthenticateInfoFragment extends Fragment {
    private static final String DATA = "data";
    private String data;

    public AuthenticateInfoFragment(){}

    public static AuthenticateInfoFragment newInstance(String data){
        AuthenticateInfoFragment authenticateInfoFragment = new AuthenticateInfoFragment();
        Bundle args = new Bundle();
        args.putString(DATA, data);
        authenticateInfoFragment.setArguments(args);

        return authenticateInfoFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = getArguments().getString(DATA, "no data to be restored");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.adapter_authenticate_info_app, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView infoLabel = (TextView) view.findViewById(R.id.info_tv_label);
        infoLabel.setText(data);
    }


}
