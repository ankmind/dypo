package com.ank.dypo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.BlockingQueue;

/**
 * Created by ankush.g on 31/10/16.
 */

public class ProducerImageAsync extends AsyncTask<URL,Long,Bitmap> {

    BlockingQueue<Bitmap> queue;

    ProducerImageAsync(BlockingQueue<Bitmap> queue) {
        this.queue=queue;
    }
        @Override
        protected Bitmap doInBackground(URL... urls) {
            try {
                URL url =  urls[0];
                final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                queue.add(bmp);
                return bmp;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {

        }
    }

