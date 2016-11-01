package com.ank.dypo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LongSparseArray;
import android.widget.ImageView;

import java.util.concurrent.BlockingQueue;

/**
 * Created by ankush.g on 31/10/16.
 */

public class ConsumerImageAsync extends AsyncTask<Long,Long,Long> {

    BlockingQueue<Bitmap> queue;
    Activity activity;
    ImageView imageView;


    ConsumerImageAsync(ImageView imageView, Activity activity,BlockingQueue<Bitmap> queue){
        this.queue=queue;
        this.imageView=imageView;
        this.activity=activity;
    }


    @Override
    protected Long doInBackground(Long... longs) {
        try {
            final Bitmap bitmap=queue.take();
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
