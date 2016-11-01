package com.ank.dypo;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    AccessToken accessToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends","read_custom_friendlists","public_profile");

            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    System.out.println("success");
                    loginResult.getAccessToken();
                    TextView t = (TextView) findViewById(R.id.ank);
                    String s="SUCCESS YO !";
                    t.setText(s);
                    Intent i = new Intent(MainActivity.this, Main2Activity.class);
                    MainActivity.this.startActivity(i);
                }

                //http://graph.facebook.com/67563683055/picture?type=square
                //http://graph.facebook.com/1061853600529424/picture?type=square
                //http://graph.facebook.com/909261389121980/picture?type=square

                @Override
                public void onCancel() {
                    System.out.println("cancelled");
                }

                @Override
                public void onError(FacebookException error) {

                }

            });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    public void getPackageInfo(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.ank.dypo", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));

            }
        } catch (PackageManager.NameNotFoundException e) {
            errorToast(e);

        } catch (NoSuchAlgorithmException e) {
            errorToast(e);
        }
    }

    public void errorToast(Exception e){
        Toast.makeText(getApplicationContext(),e.getMessage()+ " msg =" +e.getCause()+" cause "+
                        e.toString()
                ,Toast.LENGTH_LONG).show();
    }



    //commented code swipe up and swipe down
    public  void commentedCode(){
        LoginManager.getInstance().logInWithPublishPermissions(
                MainActivity.this,
                Arrays.asList("publish_actions"));

        //100001143822514
        CallbackManager callbackManager1 = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager1,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Intent i = new Intent(MainActivity.this, Main3Activity.class);
                        MainActivity.this.startActivity(i);
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });
        LoginManager.getInstance().logInWithReadPermissions
                (this, Arrays.asList("public_profile"));


        AccessTokenTracker accessTokenTracker;
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };

        AccessToken accessToken;
        accessToken = AccessToken.getCurrentAccessToken();
    }
}
