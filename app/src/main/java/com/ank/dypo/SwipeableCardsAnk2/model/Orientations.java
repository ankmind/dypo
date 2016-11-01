package com.ank.dypo.SwipeableCardsAnk2.model;

/**
 * Created by ankush.g on 01/11/16.
 */

public class Orientations {
    public Orientations() {
    }

    public static enum Orientation {
        Ordered,
        Disordered;

        private Orientation() {
        }

        public static Orientations.Orientation fromIndex(int index) {
            Orientations.Orientation[] values = values();
            if(index >= 0 && index < values.length) {
                return values[index];
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
    }
}
