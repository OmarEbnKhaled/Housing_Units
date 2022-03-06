package com.omar.housingunits.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.omar.housingunits.Fragments.CartFragment;
import com.omar.housingunits.Fragments.LoginFragment;
import com.omar.housingunits.Fragments.NotificationFragment;
import com.omar.housingunits.Fragments.ProfileFragment;
import com.omar.housingunits.Fragments.SignUpFragment;
import com.omar.housingunits.R;
import com.omar.housingunits.Utilities.Constants;
import com.omar.housingunits.Utilities.PreferenceManager;

import java.util.Objects;

public class SecondActivity extends AppCompatActivity {

    private PreferenceManager sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        sp = new PreferenceManager(this);

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

            case R.id.nav_Profile:
                Log.d("login", "setFragment: "+sp.getString(Constants.KEY_LOGIN_STATUS));
                if (sp.getString(Constants.KEY_LOGIN_STATUS).equals("yes")){
                    loadFragments(new ProfileFragment());
                }else if (sp.getString(Constants.KEY_LOGIN_STATUS).equals("no") || sp.getString(Constants.KEY_LOGIN_STATUS) == null) {
                    loadFragments(new LoginFragment());
                }
                break;

            case R.id.nav_signup:
                loadFragments(new SignUpFragment());
                break;

        }
    }

    private void loadFragments(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.second_frameLayout,fragment)
                    .commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}