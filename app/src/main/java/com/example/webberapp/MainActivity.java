package com.example.webberapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.webberapp.pojo.User;
import com.example.webberapp.state.StateStore;
import com.example.webberapp.ui.login.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private boolean checkUserLoggedIn() {
        User user = new StateStore(getApplicationContext()).getUser();
        if (user == null) {
            // user not logged in
            Log.d("__LOG", "User is null");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return false;
        }
        return true;
    }
}