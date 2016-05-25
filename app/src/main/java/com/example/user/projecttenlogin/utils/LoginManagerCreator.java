package com.example.user.projecttenlogin.utils;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.user.projecttenlogin.ui.ResultActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class LoginManagerCreator {

    // CallBack Facebook
    public static void registerCallBackFacebook(LoginButton loginButton, CallbackManager callbackManager,
                                                final Context context) {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent resultActivity = new Intent(context, ResultActivity.class);
                resultActivity.setAction("facebook");
                context.startActivity(resultActivity);
                Toast.makeText(context.getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(context.getApplicationContext(), "Login attempt canceled.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(context.getApplicationContext(), "Login attempt failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // CallBack Twitter
    public static void registerCallBackTwitter(TwitterLoginButton loginButton, final Context context) {
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = result.data;
                String msg = session.getUserName() + " logged in! (" + session.getUserId() + ")";
                context.startActivity(new Intent(context, ResultActivity.class).setAction("twitter"));
                Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(context.getApplicationContext(), "Login with Twitter failure", Toast.LENGTH_SHORT).show();
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });
    }
}
