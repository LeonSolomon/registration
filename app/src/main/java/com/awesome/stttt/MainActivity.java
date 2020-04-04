package com.awesome.stttt;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TextView tview;
    Login login = new Login();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        tview = findViewById(R.id.welcome);
//        Bundle bundle = getIntent().getExtras();
//        String msg = bundle.getString("username");
//        tview.setText("Welcome "+ msg);
        }


}
