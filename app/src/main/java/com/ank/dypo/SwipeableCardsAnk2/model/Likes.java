package com.ank.dypo.SwipeableCardsAnk2.model;

/**
 * Created by ankush.g on 01/11/16.
 */

public class Likes {
    public Likes() {
    }

    public static enum Like {
        None(0),
        Liked(1),
        Disliked(2);

        public final int value;

        private Like(int value) {
            this.value = value;
        }

        public static Likes.Like fromValue(int value) {
            Likes.Like[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Likes.Like style = var1[var3];
                if(style.value == value) {
                    return style;
                }
            }

            return null;
        }
    }
}