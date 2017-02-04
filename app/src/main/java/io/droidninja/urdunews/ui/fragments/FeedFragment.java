package io.droidninja.urdunews.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.droidninja.urdunews.R;
import io.droidninja.urdunews.model.FeedsDTO;
import io.droidninja.urdunews.networking.NewFeedApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Zeeshan on 1/24/17.
 */

public class FeedFragment extends Fragment {
    public  final String TAG = getClass().getName();
    private NewFeedApi newFeedApi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setup();
       /* String url = "https://www.express.pk/?json=get_recent_posts";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);*/
        return inflater.inflate(R.layout.activity_main, container, false);



    }

    private void setup() {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.express.pk")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        newFeedApi = retrofit.create(NewFeedApi.class);
        Call<FeedsDTO> posts = newFeedApi.getPosts();

        posts.enqueue(new Callback<FeedsDTO>() {
            @Override
            public void onResponse(Call<FeedsDTO> call, Response<FeedsDTO> response) {
                Log.d(TAG, response.toString());
                FeedsDTO posts = response.body();
            }

            @Override
            public void onFailure(Call<FeedsDTO> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }
}
