package com.ank.dypo;

import android.content.res.Resources;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

//import com.andtinder.model.Orientations;
//import com.andtinder.model.CardModel;
//import com.andtinder.model.Likes;
//
//import com.andtinder.view.CardStackAdapter;
//
//import com.andtinder.view.CardContainer;
//
//import com.andtinder.view.SimpleCardStackAdapter;
//
//import com.andtinder.view.BaseCardStackAdapter;
//
//import com.andtinder.view.CardContainer;
//import com.ank.dypo.SwipeableCardsAnk.CardModel;
//import com.ank.dypo.SwipeableCardsAnk.SimpleCardStackAdapter;
//import com.andtinder.BuildConfig;
//import com.andtinder.view.CardContainer;

import com.ank.dypo.SwipeableCardsAnk2.model.CardModel;
import com.ank.dypo.SwipeableCardsAnk2.view.CardContainer;
import com.ank.dypo.SwipeableCardsAnk2.view.SimpleCardStackAdapter;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import org.json.JSONObject;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main2Activity extends AppCompatActivity {

    CardContainer mCardContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);

        mCardContainer = (CardContainer) findViewById(R.id.layoutview);

        Resources r = getResources();
        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(this);

        adapter.add(new CardModel("Title1", "Description goes here", r.getDrawable(R.drawable.picture1)));
        adapter.add(new CardModel("Title2", "Description goes here", r.getDrawable(R.drawable.picture2)));
        adapter.add(new CardModel("Title3", "Description goes here", r.getDrawable(R.drawable.picture3)));
        adapter.add(new CardModel("Title4", "Description goes here", r.getDrawable(R.drawable.picture1)));

        CardModel cardModel = new CardModel("Title1", "Description goes here",
                r.getDrawable(R.drawable.picture1));

        cardModel.setOnClickListener(new CardModel.OnClickListener() {
            @Override
            public void OnClickListener() {
                Log.i("Swipeable Cards","I am pressing the card");
            }
        });

        cardModel.setOnCardDimissedListener(new CardModel.OnCardDimissedListener() {
            @Override
            public void onLike() {
                Log.i("Swipeable Cards","I like the card");
            }

            @Override
            public void onDislike() {
                Log.i("Swipeable Cards","I dislike the card");
            }
        });

        adapter.add(cardModel);

        mCardContainer.setAdapter(adapter);
    }




    //unused
    public void graphCall(AccessToken accessToken){
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        // Application code
                        String s="";
                        if(object!=null){
                            s=object.toString();
                        }
                        Toast.makeText(Main2Activity.this,"s= "+s+" res= "+ response.toString()
                                ,Toast.LENGTH_LONG).show();
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "friendlists,id");//"id,name,link");
        request.setParameters(parameters);
        request.executeAsync();
    }

//    {
//        "id": "12345678",
//            "birthday": "1/1/1950",
//            "first_name": "Chris",
//            "gender": "male",
//            "last_name": "Colm",
//            "link": "http://www.facebook.com/12345678",
//            "location": {
//        "id": "110843418940484",
//                "name": "Seattle, Washington"
//    },
//        "locale": "en_US",
//            "name": "Chris Colm",
//            "timezone": -8,
//            "updated_time": "2010-01-01T16:40:43+0000",
//            "verified": true
//    }
}
