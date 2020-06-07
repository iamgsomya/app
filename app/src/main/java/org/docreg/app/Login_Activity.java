package org.docreg.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Login_Activity extends AppCompatActivity {
    private EditText InputUserid, InputPassword;
    private Button LoginButton;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        LoginButton = findViewById(R.id.login_btn);
        InputUserid = findViewById(R.id.login_userid_input);
        InputPassword = findViewById(R.id.login_password_input);

        queue = Volley.newRequestQueue(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://ep2.virtualmist.com/user-login";
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i("response",response);
                                try {
                                    JSONObject obj = new JSONObject(response);
                                    int code = obj.getInt("code");
                                    String message = obj.getString("message");
                                    System.out.println("code" + code + "\nmessage:" + message);
                                    if (code ==200 ){
                                        Constants.authToken = obj.getString("token");
                                        Toast toast = Toast.makeText(getApplicationContext(),
                                                "Login Successful",
                                                Toast.LENGTH_SHORT);

                                        toast.show();
                                        Intent intent = new Intent(Login_Activity.this, Home.class);
                                        startActivity(intent);
                                    } else {
                                        Toast toast = Toast.makeText(getApplicationContext(),
                                                "Login Unsuccessful",
                                                Toast.LENGTH_SHORT);

                                        toast.show();
                                        Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                                        startActivity(intent);

                                    }

                                } catch (Exception e) {
                                    System.out.println("Exception caught");
                                }

                                Log.d("Response", response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Log.d("Error.Response", error.toString());
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("userId", InputUserid.getText().toString());
                        params.put("password", InputPassword.getText().toString());
                        return params;
                    }
                };
                queue.add(postRequest);
//                loginUser();

            }
        });
    }

//    private void loginUser() {
//
//        String username = InputUsername.getText().toString();
//        String password = InputPassword.getText().toString();
//        if (TextUtils.isEmpty(username)) {
//            Toast.makeText(this, "please write your Username", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(password)) {
//            Toast.makeText(this, "please write your password", Toast.LENGTH_SHORT).show();
//        } else {
//            Intent intent = new Intent(Login_Activity.this, Home.class);
//            startActivity(intent);
//        }
//    }
}
