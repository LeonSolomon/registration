package com.awesome.stttt;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    EditText username, mail, phone, password, passwd;
    Button send;
    TextView msg;
    Context context = Registration.this;
    private static final String TAG = "MainActivity";
    //
    Database db;
    //dont understand what this user class is about
    User users = new User();
    RequestQueue requestQueue;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        username = findViewById(R.id.name);
        mail = findViewById(R.id.email);
        phone = findViewById(R.id.phone_no);
        password = findViewById(R.id.passwd);
        passwd = findViewById(R.id.repasswd);
        send = findViewById(R.id.btn);
        send.setOnClickListener(this);

        requestQueue = Volley.newRequestQueue(this);
        //
        //Use TextWatcher for validating input data.
        TextWatcher nameWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().matches("")) {
                    Toast.makeText(Registration.this, "field cannot be empty", Toast.LENGTH_SHORT).show();
                }

            }
        };

        username.addTextChangedListener(nameWatcher);

    }

    @Override
    public void onClick(View v) {

        db = new Database(Registration.this);
        //Get data fed in by the client.
        String user = username.getText().toString();
        String user_mail = mail.getText().toString();
        String user_phone = phone.getText().toString();
        String user_pass = password.getText().toString();
        String confirm_pass = passwd.getText().toString();
        //
        //Validate the inputs..
        if (user.equals("") || user_mail.equals("") || user_phone.equals("") || user_pass.equals("")) {
            Toast.makeText(this, "Field required", Toast.LENGTH_SHORT).show();
        } else {
            if (!user_pass.equals(confirm_pass)) {
                Toast.makeText(this, "Your passwords Don`t match", Toast.LENGTH_SHORT).show();
//            }else {
//                Boolean check_credentials
//            }
            }
            //
            final JSONObject form = new JSONObject();
            try {
                form.put("name", user);
                form.put("mail", user_mail);
                form.put("pass", user_pass);
                form.put("phone", user_phone);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //
            users.signup(form);

            /**
             * The db.senddata is asyncronous and we dont know when it will complete.
             * SO you dont want to call a new intent just after.
             * How to solve this is as follows
             */

            String url = "https://mutall.co.ke/volley/alter.php";
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "onResponse: Response " + response.toString());
                    //only create an intent after successful registration
                    Intent intent = new Intent(Registration.this, Login.class);
                    startActivity(intent);


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //
                    Log.d(TAG, "onErrorResponse: Error" + error.toString());
                    //alert the user that a problem has occured
                }

            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("class", "user");
                    params.put("method", "addclient");
                    params.put("data", String.valueOf(form));
                    return params;
                }
            };
            requestQueue.add(request);

        }
    }
}
