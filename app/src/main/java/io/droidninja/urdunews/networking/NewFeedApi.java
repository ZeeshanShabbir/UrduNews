package io.droidninja.urdunews.networking;

import io.droidninja.urdunews.model.FeedsDTO;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Zeeshan on 1/22/17.
 */

public interface NewFeedApi {
    @GET("/?json=get_recent_posts")
    Call<FeedsDTO> getPosts();
}
