package org.docreg.app;

import android.content.Context;
import android.content.SharedPreferences;

public class Auth
{
    Context context;
    private String userId;
    private String token;
    private String tokenExp;
    SharedPreferences sharedPref;
    SharedPreferences.Editor myEdit;
    public Auth(Context context) {
        this.context =  context;
        this.sharedPref = context.getSharedPreferences("MySharedPref",context.MODE_PRIVATE);
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
    }

