package org.docreg.app;

import android.content.Context;
import android.content.SharedPreferences;

public class Auth
{
    private String token;
    private String userId;
    private String tokenExp;
    private String name;
    private String email;
    private String phone;
    private String city;
    private String state;
    private SharedPreferences sharedPref;
//    SharedPreferences.Editor myEdit;
    Auth(Context context) {
        this.sharedPref = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
    }

    public String getName() {
        name = sharedPref.getString("name",null);
        return name;
    }

    public void setName(String name) {
        this.name = name;
        sharedPref.edit().putString("name", name).apply();
    }

    public String getEmail() {
        email = sharedPref.getString("email",null);
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        sharedPref.edit().putString("email", email).apply();
    }

    public String getPhone() {
        phone = sharedPref.getString("phone",null);
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        sharedPref.edit().putString("phone", phone).apply();
    }

    public String getCity() {
        city = sharedPref.getString("city",null);
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        sharedPref.edit().putString("city", city).apply();
    }

    public String getState() {
        state = sharedPref.getString("state",null);
        return state;
    }

    public void setState(String state) {
        this.state = state;
        sharedPref.edit().putString("state", state).apply();
    }

    public String getUserId() {
        userId = sharedPref.getString("userid",null);
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
        sharedPref.edit().putString("userid", userId).apply();

    }
    public String getToken() {
        token = sharedPref.getString("token",null);
        return token;

    }
    public void setToken(String token) {
        this.token = token;
        sharedPref.edit().putString("token", token).apply();
    }
    public String getTokenExp() {
        userId = sharedPref.getString("tokenexp", null);
        return tokenExp;
    }
    public void setTokenExp(String tokenExp) {
        this.tokenExp = tokenExp;
        sharedPref.edit().putString("tokenexp", userId).apply();
    }

    public void removeUser(){
        setName(null);
        setToken(null);
        setState(null);
        setCity(null);
        setPhone(null);
        setEmail(null);
        setTokenExp(null);
        setUserId(null);
    }
}

