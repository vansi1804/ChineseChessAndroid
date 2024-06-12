package com.example.chinesechesstrainning.api;

import com.example.chinesechesstrainning.model.move.AvailableMoveRequestDTO;
import com.example.chinesechesstrainning.model.move.MoveCreationDTO;
import com.example.chinesechesstrainning.model.move.MoveResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MoveAPI {

    @POST("moves/available")
    Call<List<int[]>> findAllAvailable(@Body AvailableMoveRequestDTO availableMoveRequestDTO);

    @POST("moves")
    Call<MoveResponseDTO> makeMove(@Body MoveCreationDTO moveCreationDTO);
}
