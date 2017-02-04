package io.droidninja.urdunews;

import android.app.Activity;
import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import io.droidninja.urdunews.networking.NewFeedApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by Zeeshan on 1/22/17.
 */

public class UrduNewsApplication extends Application {
    private NewFeedApi newFeedApi;


    public static UrduNewsApplication get(Activity activity){
        return (UrduNewsApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Timber.plant(new Timber.DebugTree());

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });



        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Picasso picasso = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();

        Retrofit expressNewRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("https://www.express.pk")
                .build();
        newFeedApi = expressNewRetrofit.create(NewFeedApi.class);
    }

    public NewFeedApi getNewFeedApi(){
        return newFeedApi;
    }
}
