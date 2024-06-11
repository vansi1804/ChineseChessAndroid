package com.example.chinesechesstrainning.api;

import com.example.chinesechesstrainning.model.training.TrainingDTO;
import com.example.chinesechesstrainning.model.training.TrainingDetailDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TrainingAPI {

    @GET("trainings")
    Call<List<TrainingDTO>> findAll();

    @GET("trainings/{id}/details")
    Call<TrainingDetailDTO> findDetailsById(@Path("id") long id);

}
