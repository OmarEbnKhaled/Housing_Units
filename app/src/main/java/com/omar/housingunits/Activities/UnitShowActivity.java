package com.omar.housingunits.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.omar.housingunits.Adapters.FragmentAdapter;
import com.omar.housingunits.R;

import java.util.Objects;

public class UnitShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_show);

        setupToolBar();

        setupTabLayout();
    }

    private void setupToolBar(){
        Toolbar toolbar = findViewById(R.id.unit_show_toolBar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void setupTabLayout(){

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 pager2 = findViewById(R.id.view_pager2);

        FragmentManager fm = getSupportFragmentManager();
        FragmentAdapter fragmentAdapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(fragmentAdapter);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.details));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.reviews));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.about_owner));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.location_map));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}