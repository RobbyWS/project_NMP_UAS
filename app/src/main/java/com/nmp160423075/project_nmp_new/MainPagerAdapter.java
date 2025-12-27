package com.nmp160423075.project_nmp_new;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;
public class MainPagerAdapter extends FragmentStateAdapter{
    ArrayList<Fragment> fragments;

    public MainPagerAdapter(@NonNull FragmentActivity activity,
                            ArrayList<Fragment> fragments) {
        super(activity);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
