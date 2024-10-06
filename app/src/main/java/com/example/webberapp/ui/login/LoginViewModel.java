package com.example.webberapp.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.webberapp.R;
import com.example.webberapp.pojo.AuthTokens;
import com.example.webberapp.services.auth.AuthService;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String email, String password) {
        AuthTokens authTokens = AuthService.getService().login(email, password);
    }

    public void loginDataChanged(String email, String password) {
        if (!isUsernameValid(email)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_email, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUsernameValid(String username) {
        return username != null && username.trim().length() > 5;
//        if (username.contains("@")) {
//            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
//        } else {
//            return !username.trim().isEmpty();
//        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}