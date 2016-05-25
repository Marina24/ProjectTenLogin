package com.example.user.projecttenlogin.ui;

import android.content.Intent;
import android.service.textservice.SpellCheckerService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.projecttenlogin.R;
import com.example.user.projecttenlogin.utils.LoginManagerCreator;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "am4zAFIPr5kzp5cbMUeQPHEBu";
    private static final String TWITTER_SECRET = "4uQdYgvqQ0zkgK0FtuyITFNBpYt30k84sxHaErv4DCzqTbHmRa";


    private LoginButton mLoginButtonFacebook;
    private CallbackManager mCallbackManager;

    private TwitterLoginButton mLoginButtonTwitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize sdk
        FacebookSdk.sdkInitialize(getApplicationContext());
        // Initialize our instance of CallbackManager
        mCallbackManager = CallbackManager.Factory.create();

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));


        setContentView(R.layout.activity_main);

        mLoginButtonFacebook = (LoginButton) findViewById(R.id.btn_login_facebook);
        mLoginButtonTwitter = (TwitterLoginButton) findViewById(R.id.btn_login_twitter);

        // Callback Facebook
        LoginManagerCreator.registerCallBackFacebook(mLoginButtonFacebook, mCallbackManager, this);
        // Callback Twitter
        LoginManagerCreator.registerCallBackTwitter(mLoginButtonTwitter, this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE == requestCode) {
            mLoginButtonTwitter.onActivityResult(requestCode, resultCode, data);
        } else {
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
}
