package com.ank.dypo.SwipeableCardsAnk2.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ank.dypo.SwipeableCardsAnk2.model.CardModel;


/**
 * Created by ankush.g on 01/11/16.
 */

public final class SimpleCardStackAdapter extends CardStackAdapter {
    public SimpleCardStackAdapter(Context mContext) {
        super(mContext);
    }

    public View getCardView(int position, CardModel model, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            convertView = inflater.inflate(com.andtinder.R.layout.std_card_inner, parent, false);
            assert convertView != null;
        }

        ((ImageView)convertView.findViewById(com.andtinder.R.id.image)).setImageDrawable(model.getCardImageDrawable());
        ((TextView)convertView.findViewById(com.andtinder.R.id.title)).setText(model.getTitle());
        ((TextView)convertView.findViewById(com.andtinder.R.id.description)).setText(model.getDescription());
        return convertView;
    }
}