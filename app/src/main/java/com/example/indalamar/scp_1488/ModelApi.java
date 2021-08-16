package com.example.indalamar.scp_1488;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ModelApi {

    @GET("method/photos.get?owner_id=-53759180&album_id=wall&count=50&v=5.92&access_token=???")
    Call<Model> getMyJSON();
}
