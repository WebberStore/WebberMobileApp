package com.example.webberapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.webberapp.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.webberapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean loggedIn = checkUserLoggedIn();
        if (!loggedIn) return;

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private boolean checkUserLoggedIn() {
        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(getString(R.string.PREFERENCE_FILE_KEY_webber_app), Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(getString(R.string.PREFERENCE_user_email), "");

        if (email.isEmpty()) {
            // user not logged in
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return false;
        }
        return true;
    }
}