package com.example.indalamar.scp_1488;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ModelApi {

    @GET("method/photos.get?owner_id=-53759180&album_id=wall&count=50&v=5.92&access_token=54548621624eeb2de6fc4e8057a8d05dddf63b78a364828f72d2a6e494563c0bfb1f381a16cfc1ff876f0")
    Call<Model> getMyJSON();
}
