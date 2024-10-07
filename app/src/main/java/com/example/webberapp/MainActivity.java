package com.example.webberapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.webberapp.pojo.AuthTokens;
import com.example.webberapp.state.StateStore;
import com.example.webberapp.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.webberapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean loggedIn = checkUserLoggedIn();
        if (!loggedIn) return;

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_cart, R.id.navigation_browse, R.id.navigation_account).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private boolean checkUserLoggedIn() {
        AuthTokens authTokens = new StateStore(getApplicationContext()).getAuthTokens();
        if (authTokens == null) {
            // user not logged in
            Log.d("__LOG", "Auth tokens are null");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return false;
        }
        return true;
    }
}