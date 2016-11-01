package com.ank.dypo.SwipeableCardsAnk2;

/**
 * Created by ankush.g on 01/11/16.
 */

public class Utils {
    public Utils() {
    }

    public static float functionNormalize(int max, int min, int value) {
        int intermediateValue = max - min;
        value -= intermediateValue;
        Math.abs((float)value / (float)intermediateValue);
        return Math.abs((float)value / (float)intermediateValue);
    }
}