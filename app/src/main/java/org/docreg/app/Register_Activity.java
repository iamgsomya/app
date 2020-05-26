package org.docreg.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register_Activity extends AppCompatActivity {
    private EditText  InputId,InputUsername,InputPassword,InputEmail,InputPhone,InputCity;
    private Button RegisterButton;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        InputId=findViewById(R.id.User_id);
        InputUsername=findViewById(R.id.Username_input);
        InputPassword=findViewById(R.id.UserPassword_input);
        InputEmail=findViewById(R.id.Useremail_input);




        InputPhone=findViewById(R.id.Userphone_input);
        InputCity=findViewById(R.id.Usercity_input);
        RegisterButton=findViewById(R.id.Register_btn);
        queue = Volley.newRequestQueue(this);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String url = "https://ep2.virtualmist.com/phpmyadmin/register-new-user";
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                              try{
                                  JSONObject jsonObject=new JSONObject(response);
                                  String code=jsonObject.getString("code");
                                  String message=jsonObject.getString("message");
                                  System.out.println("code"+code+"\nmessage:"+message);

                              }
                              catch (Exception e){
                                  System.out.println("exception caught");

                              }
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Log.d("Error.Response", error.toString());
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("userId", InputId.getText().toString());
                        params.put("name", InputUsername.getText().toString());
                        params.put("password",InputPassword.getText().toString() );
                        params.put("email", InputEmail.getText().toString());
                        params.put("phone", InputPhone.getText().toString());
                        params.put("city", InputCity.getText().toString());


                        return params;
                    }
                };
                queue.add(postRequest);



            }
        });

    }
}
