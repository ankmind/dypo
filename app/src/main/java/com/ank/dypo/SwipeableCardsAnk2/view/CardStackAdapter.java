package com.ank.dypo.SwipeableCardsAnk2.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ank.dypo.SwipeableCardsAnk2.model.CardModel;
import com.ank.dypo.SwipeableCardsAnk2.view.*;
//import com.ank.dypo.SwipeableCardsAnk2.
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ankush.g on 01/11/16.
 */

public abstract class CardStackAdapter extends BaseCardStackAdapter {
    private final Context mContext;
    private final Object mLock = new Object();
    private ArrayList<CardModel> mData;

    public CardStackAdapter(Context context) {
        this.mContext = context;
        this.mData = new ArrayList();
    }

    public CardStackAdapter(Context context, Collection<? extends CardModel> items) {
        this.mContext = context;
        this.mData = new ArrayList(items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        FrameLayout wrapper = (FrameLayout)convertView;
        FrameLayout innerWrapper;
        View cardView;
        if(wrapper == null) {
            wrapper = new FrameLayout(this.mContext);
            wrapper.setBackgroundResource(com.ank.dypo.SwipeableCardsAnk2.R.drawable.card_bg);
            if(this.shouldFillCardBackground()) {
                innerWrapper = new FrameLayout(this.mContext);
                innerWrapper.setBackgroundColor(this.mContext.getResources().getColor(com.ank.dypo.SwipeableCardsAnk2.R.color.card_bg));
                wrapper.addView(innerWrapper);
            } else {
                innerWrapper = wrapper;
            }

            cardView = this.getCardView(position, this.getCardModel(position), (View)null, parent);
            innerWrapper.addView(cardView);
        } else {
            if(this.shouldFillCardBackground()) {
                innerWrapper = (FrameLayout)wrapper.getChildAt(0);
            } else {
                innerWrapper = wrapper;
            }

            cardView = innerWrapper.getChildAt(0);
            View convertedCardView = this.getCardView(position, this.getCardModel(position), cardView, parent);
            if(convertedCardView != cardView) {
                wrapper.removeView(cardView);
                wrapper.addView(convertedCardView);
            }
        }

        return wrapper;
    }

    protected abstract View getCardView(int var1, CardModel var2, View var3, ViewGroup var4);

    public boolean shouldFillCardBackground() {
        return true;
    }

    public void add(CardModel item) {
        Object var2 = this.mLock;
        synchronized(this.mLock) {
            this.mData.add(item);
        }

        this.notifyDataSetChanged();
    }

    public CardModel pop() {
        Object var2 = this.mLock;
        CardModel model;
        synchronized(this.mLock) {
            model = (CardModel)this.mData.remove(this.mData.size() - 1);
        }

        this.notifyDataSetChanged();
        return model;
    }

    public Object getItem(int position) {
        return this.getCardModel(position);
    }

    public CardModel getCardModel(int position) {
        Object var2 = this.mLock;
        synchronized(this.mLock) {
            return (CardModel)this.mData.get(this.mData.size() - 1 - position);
        }
    }

    public int getCount() {
        return this.mData.size();
    }

    public long getItemId(int position) {
        return (long)this.getItem(position).hashCode();
    }

    public Context getContext() {
        return this.mContext;
    }
}