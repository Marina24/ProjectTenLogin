package com.example.user.projecttenlogin.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.projecttenlogin.R;
import com.facebook.login.LoginManager;
import com.twitter.sdk.android.core.TwitterCore;

public class ResultActivity extends AppCompatActivity {

    private TextView mTextResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mTextResult = (TextView) findViewById(R.id.txt_result_login);

        mTextResult.setText(getResources().getText(R.string.log_in_result));
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getAction().equals("facebook")) {
                super.onBackPressed();
                LoginManager.getInstance().logOut();
                Toast.makeText(getApplicationContext(), "Log out!", Toast.LENGTH_SHORT).show();
            } else {
                super.onBackPressed();
                TwitterCore.getInstance().logOut();
                Toast.makeText(getApplicationContext(), "Log out!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
