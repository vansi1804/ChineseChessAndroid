package com.example.chinesechesstrainning.api;

import com.example.chinesechesstrainning.model.PlayBoardDTO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlayBoardAPI {
    @GET("play-board/generate")
    Call<PlayBoardDTO> generate();
}
