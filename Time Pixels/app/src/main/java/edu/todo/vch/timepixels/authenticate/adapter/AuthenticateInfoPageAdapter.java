package edu.todo.vch.timepixels.authenticate.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import edu.todo.vch.timepixels.authenticate.view.AuthenticateInfoFragment;

/**
 * Authenticate Info Pager Adapter used to inflate {@link  edu.todo.vch.timepixels.authenticate.view.AuthenticateInfoFragment}
 */
public class AuthenticateInfoPageAdapter extends FragmentPagerAdapter {
    private final String[] infoData;

    public AuthenticateInfoPageAdapter(FragmentManager fragmentManager, String[] infoData) {
        super(fragmentManager);
        this.infoData = infoData;
    }

    @Override
    public Fragment getItem(int position) {
        return AuthenticateInfoFragment.newInstance(infoData[position]);
    }

    @Override
    public int getCount() {
        return infoData.length;
    }
}
