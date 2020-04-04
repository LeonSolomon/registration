package com.awesome.stttt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Login";
    Context ctx = Login.this;
    EditText user, pass;
    Button login;
    TextView forgtpass, Signup;
    CheckBox check;
    User users = new User();
    Database db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        user = findViewById(R.id.user_crd);
        pass = findViewById(R.id.pass_crd);
        check = findViewById(R.id.checkBox);
        forgtpass = findViewById(R.id.forgotpass);
        forgtpass.setOnClickListener(this);
        Signup = findViewById(R.id.signup);
        Signup.setOnClickListener(this);
        login = findViewById(R.id.login_sys);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        db = new Database(this);
       switch (v.getId()){
           case R.id.login_sys:
                Log.d(TAG, "onClick: login clicked");
                String user_credentialname = user.getText().toString();
                String user_credentialpass = pass.getText().toString();
                //users.login(user_credentialname, user_credentialpass);
                JSONObject obj = new JSONObject();
                try {
                   obj.put("username", user_credentialname);
                   obj.put("password", user_credentialpass);

               }catch (JSONException e) {
                   e.printStackTrace();
               }
               db.senddata("user","checkclient",obj);
               Intent i = new Intent(Login.this,MainActivity.class);
               i.putExtra("username",user_credentialname);
               //startActivity(i);
               break;
           case R.id.forgotpass:
               Log.d(TAG, "onClick: forgotpass clicked");
               resetpassword();
               break;
           case R.id.signup:
               Log.d(TAG, "onClick: signup clicked");
               Intent intent = new Intent(Login.this, Registration.class);
               startActivity(intent);
               break;
           default:this.finish();
       }

    }

    private void resetpassword() {
        //reset from email
        //ask the user to check his email for a reset code.
    }

}
