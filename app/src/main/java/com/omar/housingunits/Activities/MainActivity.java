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
import com.omar.housingunits.Fragments.CategoryFragment;
import com.omar.housingunits.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupActivity();

        loadFragment(new CategoryFragment());
    }

    private void setupActivity(){
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.main_toolBar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment,fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

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
                break;
        }
        return super.onOptionsItemSelected(item);
    }

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
            case R.id.item_btn_details:
                startActivity(new Intent(MainActivity.this, UnitShowActivity.class));
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    private void startIntentSecondActivity(int ID_Fragment){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("fragment",ID_Fragment);
        startActivity(intent);
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}