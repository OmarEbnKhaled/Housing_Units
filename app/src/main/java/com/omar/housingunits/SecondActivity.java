package com.omar.housingunits;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.omar.housingunits.Fragments.CartFragment;
import com.omar.housingunits.Fragments.LoginFragment;
import com.omar.housingunits.Fragments.NotificationFragment;

import java.util.Objects;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar toolbar = findViewById(R.id.second_toolBar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Bundle extras;
        if (savedInstanceState == null) {
            extras = getIntent().getExtras();
            if(extras != null) {
                setFragment(extras.getInt("fragment"));
            }else
                finish();
        } else {
            finish();
        }
    }

    @SuppressLint("NonConstantResourceId")
    private void setFragment(int ID_fragment){
        switch (ID_fragment){
            case R.id.actionBar_notification:
                loadFragments(new NotificationFragment());
                break;
            case R.id.actionBar_cart:
                loadFragments(new CartFragment());
                break;
        }
    }

    private void loadFragments(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout,fragment)
                    .commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}