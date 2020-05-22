package org.docreg.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity {
    private EditText InputUsername,InputPassword;
    private Button LoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        LoginButton=findViewById(R.id.login_btn);
        InputUsername=findViewById(R.id.login_username_input);
        InputPassword=findViewById(R.id.login_password_input);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUser();

            }
        });
    }
    private void loginUser() {

        String username=InputUsername.getText().toString();
        String password=InputPassword.getText().toString();
        if(TextUtils.isEmpty(username))
        {
            Toast.makeText( this,"please write your Username",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText( this,"please write your password",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent=new Intent(Login_Activity.this,Home.class);
            startActivity(intent);
        }
    }
}
