package com.example.webberapp.services.user;

import com.example.webberapp.pojo.User;
import com.example.webberapp.services.ApiClient;
import com.example.webberapp.utils.CustomExceptionHandler;

import java.io.IOException;

import retrofit2.Call;

public class UserService {
    private static UserService userService;
    private final UserApiInterface userApiInterface;

    private UserService() {
        this.userApiInterface = ApiClient.getClient().create(UserApiInterface.class);
    }

    public static UserService getService() {
        if (userService == null) userService = new UserService();
        return userService;
    }

    public User login(String username, String password) throws InterruptedException {
        LoginReqDto req = new LoginReqDto(username, password);
        final LoginResDto[] res = new LoginResDto[1];
        Call<LoginResDto> call = userApiInterface.login(req);

        Thread t = new Thread(() -> {
            try {
                res[0] = call.execute().body();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        t.setUncaughtExceptionHandler(new CustomExceptionHandler());
        t.start();
        t.join();

        if (res[0] == null) return null;
        User user = new User();
        user.id = res[0].id;
        user.email = res[0].email;
        user.name = res[0].name;
        user.password = res[0].password;
        user.role = res[0].role;
        user.status = res[0].status;
        user.approveStatus = res[0].approveStatus;
        user.rating = res[0].rating;
        user.ratingCount = res[0].ratingCount;
        user.createdAt = res[0].createdAt;
        user.updatedAt = res[0].updatedAt;
        return user;
    }
}
