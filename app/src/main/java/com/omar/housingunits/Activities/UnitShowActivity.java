package com.omar.housingunits.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.omar.housingunits.Adapters.ViewPagerAdapter;
import com.omar.housingunits.Adapters.SliderAdapter;
import com.omar.housingunits.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import toan.android.floatingactionmenu.FloatingActionButton;
import toan.android.floatingactionmenu.FloatingActionsMenu;

public class UnitShowActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionsMenu fab_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_show);

        setupToolBar();

        setupTabLayout();

        setSlider();
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
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(fm, getLifecycle());
        pager2.setAdapter(viewPagerAdapter);

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

        FloatingActionButton fab_call, fab_message, fab_add_contact, fab_copy;

        fab_call = findViewById(R.id.fab_call);
        fab_call.setOnClickListener(this);

        fab_message = findViewById(R.id.fab_message);
        fab_message.setOnClickListener(this);

        fab_add_contact = findViewById(R.id.fab_add_contact);
        fab_add_contact.setOnClickListener(this);

        fab_copy = findViewById(R.id.fab_copy);
        fab_copy.setOnClickListener(this);

        View view = findViewById(R.id.background_fab_menu);
        fab_menu = findViewById(R.id.fab_menu);
        fab_menu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onMenuCollapsed() {
                view.setVisibility(View.GONE);
            }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                fab_menu.collapse();
                return true;
            }
        });

    }

    private void setSlider() {
        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.omar);
        List<Bitmap> bitmaps = new ArrayList<>();
        for (int i=0; i<=7; i++){
            bitmaps.add(bitmap);
        }

        SliderView sliderView = findViewById(R.id.slider);
        SliderAdapter sliderAdapter = new SliderAdapter(bitmaps);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.fab_call:
                Intent intentDial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0123456789"));
                startActivity(intentDial);
                break;
            case R.id.fab_message:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "0123456789"));
                intent.putExtra("sms_body", "Housing Units");
                startActivity(intent);
                break;
            case R.id.fab_add_contact:
                addContact();
                break;
            case R.id.fab_copy:
                copyPhoneNumber();
                break;
        }
    }

    private void addContact(){
        Intent contactIntent = new Intent(ContactsContract.Intents.Insert. ACTION ) ;
        contactIntent.setType(ContactsContract.RawContacts. CONTENT_TYPE ) ;
        contactIntent
                .putExtra(ContactsContract.Intents.Insert. NAME , "Housing Units")
                .putExtra(ContactsContract.Intents.Insert. PHONE , "0123456789") ;
        startActivity(contactIntent);
    }

    private void copyPhoneNumber(){
        ClipboardManager clipboard = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        }
        ClipData clip = ClipData.newPlainText("label", "0123456789");
        if (clipboard != null) {
            clipboard.setPrimaryClip(clip);
            Snackbar.make((View) findViewById(R.id.background_fab_menu), R.string.phone_no_copied, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.unit_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (fab_menu.isExpanded()){
            fab_menu.collapse();
        }else {
            super.onBackPressed();
        }
    }
}