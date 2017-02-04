package io.droidninja.urdunews.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.droidninja.urdunews.R;
import io.droidninja.urdunews.model.FeedsDTO;
import io.droidninja.urdunews.networking.NewFeedApi;
import io.droidninja.urdunews.ui.fragments.FeedFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAIN_ACTIVITY";
    NewFeedApi newFeedApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new FeedFragment(), this.toString())
                    .commit();
        }
    }
}

