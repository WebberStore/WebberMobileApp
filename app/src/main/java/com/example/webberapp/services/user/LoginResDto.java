package com.example.webberapp.services.user;

import com.google.gson.annotations.SerializedName;

public class LoginResDto {
    @SerializedName("id")
    public String id;
    @SerializedName("email")
    public String email;
    @SerializedName("name")
    public String name;
    @SerializedName("password")
    public String password;
    @SerializedName("role")
    public String role;
    @SerializedName("status")
    public String status;
    @SerializedName("approveStatus")
    public String approveStatus;
    @SerializedName("rating")
    public String rating;
    @SerializedName("ratingCount")
    public String ratingCount;
    @SerializedName("createdAt")
    public String createdAt;
    @SerializedName("updatedAt")
    public String updatedAt;
}
