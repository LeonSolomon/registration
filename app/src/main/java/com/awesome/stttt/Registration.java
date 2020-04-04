package com.awesome.stttt;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONObject;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    EditText username, mail, phone, password, passwd;
    Button send;
    TextView msg;
    Context context = Registration.this;
    private static final String TAG = "MainActivity";
    //
    Database db;
    User users = new User();

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
            db.senddata("user", "addclient", form);
            Intent intent = new Intent(Registration.this, Login.class);
            startActivity(intent);
        }
    }
}
