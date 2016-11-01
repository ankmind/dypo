package com.ank.dypo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main3Activity extends AppCompatActivity {


    BlockingQueue<Bitmap> imageQueue=new ArrayBlockingQueue<Bitmap>(1000);
    ImageView topImage,bottomImage;

    public void getBitMapForProfilePic(String id,ImageView imageView) throws IOException {
        String s="https://graph.facebook.com/"+id+"/picture?type=large" +
                "";
        ProducerImageAsync producerImageAsync =new ProducerImageAsync(imageQueue);
        producerImageAsync.execute(new URL(s));

        ConsumerImageAsync consumerImageAsync=new ConsumerImageAsync(imageView,this,imageQueue);
        consumerImageAsync.execute();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        topImage = (ImageView) findViewById(R.id.topImage);
        bottomImage = (ImageView) findViewById(R.id.bottomImage);

        View v = findViewById(R.id.swipeView);

        v.setOnTouchListener(new OnSwipeTouchListener(Main3Activity.this) {
            public void onSwipeTop() {
                Toast.makeText(Main3Activity.this, "top 1", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeRight() {
                Toast.makeText(Main3Activity.this, "right 1", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeLeft() {
                Toast.makeText(Main3Activity.this, "left 1", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeBottom() {
                Toast.makeText(Main3Activity.this, "bottom 1", Toast.LENGTH_SHORT).show();
            }

        });

        graphCall();
        try {
            getBitMapForProfilePic("4",topImage);
            getBitMapForProfilePic("5",bottomImage);
            //getBitMapForProfilePic("5");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //topImage.setImageBitmap(imageQueue.take());
           // bottomImage.setImageBitmap(imageQueue.take());
        } catch (Exception e) {
            e.printStackTrace();
            errorToast(e);
        }
    }



    public void graphCall(){
        AccessToken accessToken=AccessToken.getCurrentAccessToken();
//        GraphRequest request = GraphRequest.newMeRequest(
//                accessToken,
//                new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(
//                            JSONObject object,
//                            GraphResponse response) {
//                        // Application code
//                        String s="";
//                        if(object!=null){
//                            s=object.toString();
//                        }
//                        Toast.makeText(Main3Activity.this,"s= "+s+" res= "+ response.toString()
//                                ,Toast.LENGTH_LONG).show();
//
//                        Log.i("anknull",s);
//
//                        try {
//                            JSONArray jsonArray=object.getJSONObject("friendlists").getJSONArray("data");
//                            for(int i=0;i<jsonArray.length();i++){
//                                JSONObject jsonObject=jsonArray.getJSONObject(i);
//                                String id=jsonObject.getString("id");
//                                //getBitMapForProfilePic(id);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            errorToast(e);
//
//                        }
//                    }
//                });


        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/100001143822514/friends",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
            /* handle the result */
                        Toast.makeText(Main3Activity.this," res= "+ response.toString()
                                ,Toast.LENGTH_LONG).show();
                    }
                }
        ).executeAsync();

        Bundle parameters = new Bundle();
        parameters.putString("fields", "friendlists,id");//"id,name,link");
       // request.setParameters(parameters);
        //request.executeAsync();
    }

    public void errorToast(Exception e){
        Toast.makeText(getApplicationContext(),e.getMessage()+ " msg =" +e.getCause()+" cause "+
                        e.toString()
                ,Toast.LENGTH_LONG).show();
    }
//        topImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(Main3Activity.this, "TOP IMAGE CLICKED", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//
//        bottomImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(Main3Activity.this, "bottomImage CLICKED", Toast.LENGTH_SHORT).show();
//            }
//        });
//        bottomImage.setOnTouchListener(new OnSwipeTouchListener(Main3Activity.this) {
//            public void onSwipeTop() {
//                Toast.makeText(Main3Activity.this, "top 2", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeRight() {
//                Toast.makeText(Main3Activity.this, "right 2", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeLeft() {
//                Toast.makeText(Main3Activity.this, "left 2", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeBottom() {
//                Toast.makeText(Main3Activity.this, "bottom 2", Toast.LENGTH_SHORT).show();
//            }
//
//        });anknull: {"friendlists":{"data":[{"id":"1061853600529424"},{"id":"1061853593862758"},
// {"id":"1061853590529425"},{"id":"314677635247028"},{"id":"246548585393267"},{"id":"246548582059934"},{"id":"233107170070742"},{"id":"233107160070743"},{"id":"233107156737410"},{"id":"233107146737411"}]



    // Instantiate the RequestQueue.

    public void makeVolleyRequest(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.google.com";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}


    // Request a string response from the provided URL.


