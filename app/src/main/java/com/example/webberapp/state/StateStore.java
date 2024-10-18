package com.example.webberapp.state;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.webberapp.R;
import com.example.webberapp.pojo.User;
import com.google.gson.Gson;

public class StateStore {
    private final SharedPreferences prefs;
    private final Context context;

    public StateStore(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences(context.getString(R.string.PREFERENCE_FILE_KEY_webber_app), Context.MODE_PRIVATE);
    }

    public void setUser(User user) {
        Editor prefsEditor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString(this.context.getString(R.string.PREFERENCE_user), json);
        prefsEditor.commit();
    }

    public User getUser() {
        Gson gson = new Gson();
        String json = this.prefs.getString(this.context.getString(R.string.PREFERENCE_user), "");
        return gson.fromJson(json, User.class);
    }
}
