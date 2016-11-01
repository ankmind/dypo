package com.ank.dypo.SwipeableCardsAnk2.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by ankush.g on 01/11/16.
 */

public class CardModel {
    private String title;
    private String description;
    private Drawable cardImageDrawable;
    private Drawable cardLikeImageDrawable;
    private Drawable cardDislikeImageDrawable;
    private CardModel.OnCardDimissedListener mOnCardDimissedListener;
    private CardModel.OnClickListener mOnClickListener;

    public CardModel() {
        this((String)null, (String)null, (Drawable)((Drawable)null));
    }

    public CardModel(String title, String description, Drawable cardImage) {
        this.mOnCardDimissedListener = null;
        this.mOnClickListener = null;
        this.title = title;
        this.description = description;
        this.cardImageDrawable = cardImage;
    }

    public CardModel(String title, String description, Bitmap cardImage) {
        this.mOnCardDimissedListener = null;
        this.mOnClickListener = null;
        this.title = title;
        this.description = description;
        this.cardImageDrawable = new BitmapDrawable((Resources)null, cardImage);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Drawable getCardImageDrawable() {
        return this.cardImageDrawable;
    }

    public void setCardImageDrawable(Drawable cardImageDrawable) {
        this.cardImageDrawable = cardImageDrawable;
    }

    public Drawable getCardLikeImageDrawable() {
        return this.cardLikeImageDrawable;
    }

    public void setCardLikeImageDrawable(Drawable cardLikeImageDrawable) {
        this.cardLikeImageDrawable = cardLikeImageDrawable;
    }

    public Drawable getCardDislikeImageDrawable() {
        return this.cardDislikeImageDrawable;
    }

    public void setCardDislikeImageDrawable(Drawable cardDislikeImageDrawable) {
        this.cardDislikeImageDrawable = cardDislikeImageDrawable;
    }

    public void setOnCardDimissedListener(CardModel.OnCardDimissedListener listener) {
        this.mOnCardDimissedListener = listener;
    }

    public CardModel.OnCardDimissedListener getOnCardDimissedListener() {
        return this.mOnCardDimissedListener;
    }

    public void setOnClickListener(CardModel.OnClickListener listener) {
        this.mOnClickListener = listener;
    }

    public CardModel.OnClickListener getOnClickListener() {
        return this.mOnClickListener;
    }

    public interface OnClickListener {
        void OnClickListener();
    }

    public interface OnCardDimissedListener {
        void onLike();

        void onDislike();
    }
}