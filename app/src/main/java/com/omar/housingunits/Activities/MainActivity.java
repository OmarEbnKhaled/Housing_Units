package com.omar.housingunits.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.omar.housingunits.Fragments.CategoryFragment;
import com.omar.housingunits.R;
import com.omar.housingunits.Utilities.Constants;
import com.omar.housingunits.Utilities.PreferenceManager;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private DrawerLayout drawerLayout;
    private Dialog dialog;
    private PreferenceManager sp;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = new PreferenceManager(this);

        navigationView = findViewById(R.id.nav_view);

        loadFragment(new CategoryFragment());

        setupActivity();

    }

    /*===================================================================================*/

    private void setupActivity(){

        Toolbar toolbar = findViewById(R.id.main_toolBar);

        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        MaterialButton header_login = navigationView.getHeaderView(0).findViewById(R.id.header_btn_Login);
        header_login.setOnClickListener(this);

        MaterialButton header_signup = navigationView.getHeaderView(0).findViewById(R.id.header_btn_signup);
        header_signup.setOnClickListener(this);

    }

    /*===================================================================================*/

    public void loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment,fragment)
                    .commit();
        }
    }

    /*===================================================================================*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    /*===================================================================================*/

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionBar_more:
                break;
            case R.id.actionBar_language:
                showLanguageDialog();
                break;
            case R.id.actionBar_result_layout:
                break;
            case R.id.actionBar_currency:
                break;
            default:
                startIntentSecondActivity(item.getItemId());
        }
        return super.onOptionsItemSelected(item);
    }

    /*===================================================================================*/

    private void showLanguageDialog(){
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_language);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        MaterialButton btn_arabic = dialog.findViewById(R.id.btn_arabic);
        btn_arabic.setOnClickListener(this);

        MaterialButton btn_english = dialog.findViewById(R.id.btn_english);
        btn_english.setOnClickListener(this);
    }

    /*===================================================================================*/

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_arabic:
                setLanguage("ar");
                dialog.dismiss();
                break;

            case R.id.btn_english:
                setLanguage("en");
                dialog.dismiss();
                break;

            case R.id.header_btn_Login:
                startIntentSecondActivity(R.id.nav_Profile);
                break;

            case R.id.header_btn_signup:
                startIntentSecondActivity(R.id.nav_signup);
        }
    }

    /*===================================================================================*/

    private void setLanguage(String language){

        Resources resources = getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(language);
        configuration.setLayoutDirection(new Locale(language));
        resources.updateConfiguration(configuration, metrics);
        onConfigurationChanged(configuration);

        Intent intent = getIntent();
        finish();
        startActivity(intent);
        overridePendingTransition(0,0);

    }

    /*===================================================================================*/

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_requests:
                break;
            case R.id.nav_reservations:
                break;
            case R.id.nav_favorite:
                break;
            case R.id.nav_Profile:
                startIntentSecondActivity(item.getItemId());
                break;
            case R.id.nav_settings:
                break;
            case R.id.nav_add_new_unit:
                break;
            case R.id.nav_ur_units:
                break;
            case R.id.nav_reservation_requests:
                break;
            case R.id.nav_call_us:
                break;
            case R.id.nav_about_app:
                break;
            case R.id.nav_share_app:
                break;
            case R.id.nav_logOut:
                sp.putString(Constants.KEY_LOGIN_STATUS, "no");
                Snackbar.make(findViewById(R.id.main_fragment), "Log out is done.", Snackbar.LENGTH_LONG).show();
                setupLogin();
                break;
        }
        return true;
    }

    /*===================================================================================*/

    private void startIntentSecondActivity(int ID_Fragment){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("fragment",ID_Fragment);
        startActivity(intent);
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    /*===================================================================================*/

    public void setupLogin(){

        if (sp.getString(Constants.KEY_LOGIN_STATUS) == null){
            sp.putString(Constants.KEY_LOGIN_STATUS, "no");
        }

        if (sp.getString(Constants.KEY_LOGIN_STATUS).equals("yes")){

            navigationView.getMenu().findItem(R.id.nav_logOut).setVisible(true);
            navigationView.getHeaderView(0).findViewById(R.id.header_lin_profile).setVisibility(View.VISIBLE);
            navigationView.getHeaderView(0).findViewById(R.id.header_lin_new_user).setVisibility(View.GONE);

        }else {

            navigationView.getMenu().findItem(R.id.nav_logOut).setVisible(false);
            navigationView.getHeaderView(0).findViewById(R.id.header_lin_profile).setVisibility(View.GONE);
            navigationView.getHeaderView(0).findViewById(R.id.header_lin_new_user).setVisibility(View.VISIBLE);

        }
    }

    /*===================================================================================*/

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    /*===================================================================================*/

    @Override
    protected void onResume() {

        setupLogin();

        super.onResume();
    }
}