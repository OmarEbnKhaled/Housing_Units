package com.omar.housingunits.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.omar.housingunits.Fragments.AboutOwnerFragment;
import com.omar.housingunits.Fragments.DetailsFragment;
import com.omar.housingunits.Fragments.LocationMapFragment;
import com.omar.housingunits.Fragments.ReviewsFragment;

public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1:
                return new ReviewsFragment();
            case 2:
                return new AboutOwnerFragment();
            case 3:
                return new LocationMapFragment();
        }

        return new DetailsFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
