package com.example.mack.serviceproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.AsyncTask;   //library to download images.
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.util.Log;


/*
Whenever service ins called first onCreate is called (The system calls this method when the service is first created, to perform one-time setup procedures (before it calls either onStartCommand() or onBind()). If the service is already running, this method is not called.)


 */
public class downloadService extends Service {
    String link;   //using a global variable to pass the url.
    public downloadService() {
    }

    private ImageView downloadedImg;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // this is binding the service
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // this will be called whenever service starts  (called by startService())

        link = intent.getStringExtra("link");
        ImageDownloader imageDownloader = new ImageDownloader();   //creating a object for downloading image
        imageDownloader.execute();  //calling the object
        return START_STICKY;
    }

    class ImageDownloader extends AsyncTask{
        // why we need to keep it as abstract class

        @Override
        protected Object doInBackground(Object[] params) {
            //Download the image using async task.

            String url;// = params.toString(); //hope it gives back the string//mayank :- no its not
            url = link;

            //mechanically passing the string value
            //url = "https://i.ytimg.com/vi/UgBBitvVHAg/hqdefault.jpg";

            Log.i("Mayank Service started", url);  //mayank logging
            int file_length=0;   //to get the file length to show it in progress bar
            try {
                // what does these individual steps do and why so many steps
                URL imageLink = new URL(url);  //converting the string to link
                URLConnection urlconnection = imageLink.openConnection();
                urlconnection.connect();  //connection established
                file_length=urlconnection.getContentLength(); //obtaining the file size
                File mack = new File("sdcard/mack"); //creatign the folder.
                if(!mack.exists()){
                    mack.mkdir(); //if folder is not there then make directory
                }
                File input_file = new File(mack,"downloadedImage.jpg");
                InputStream inputStream = new BufferedInputStream(imageLink.openStream(),8192);
                byte[] data = new byte[1024];
                int total =0;
                int count =0;
                OutputStream outputStream= new FileOutputStream(input_file);
                while ((count=inputStream.read(data))!=-1){
                    total+=count;
                    outputStream.write(data,0,count);
                    //int progress =(int) count*100/file_length;
                    //publishProgress(progress);

                }
        inputStream.close();
        outputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // open conncetion misgh cause soem IO exceptions
                e.printStackTrace();
            }
            Log.i("Mayank Service Closed","End");
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            //Need to save the image after execution and also display the done messege

            super.onPostExecute(o);
        }
    }
}
