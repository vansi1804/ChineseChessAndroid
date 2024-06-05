package com.example.chinesechesstrainning.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chinesechesstrainning.R;
import com.example.chinesechesstrainning.adapter.TrainingItemAdapter;
import com.example.chinesechesstrainning.api.TrainingAPI;
import com.example.chinesechesstrainning.enumerable.MediaStatus;
import com.example.chinesechesstrainning.model.training.TrainingDTO;
import com.example.chinesechesstrainning.support.Support;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainingActivity extends HeaderActivity {

    private TrainingAPI trainingAPI;
    private TextView tvTrainingTitle;
    private RecyclerView recyclerView;
    private List<TrainingDTO> trainingDTOs;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        imgBtnHome = findViewById(R.id.img_btn_home);
        imgBtnHome.setOnClickListener(this);

        imgBtnBack = findViewById(R.id.img_btn_back);
        imgBtnBack.setOnClickListener(this);

        imgBtnSpeaker = findViewById(R.id.img_btn_speaker);
        imgBtnSpeaker.setOnClickListener(this);

        imgBtnMusic = findViewById(R.id.img_btn_music);
        imgBtnMusic.setOnClickListener(this);

        tvTrainingTitle = findViewById(R.id.tv_training_title);
        tvTrainingTitle.setSelected(true);

        recyclerView = findViewById(R.id.rc_training_item);

        if (getIntent().getExtras() != null) {
            imgBtnSpeaker.setTag(getIntent().getExtras().getString("speaker"));
            imgBtnMusic.setTag(getIntent().getExtras().getString("music"));

            TrainingDTO trainingDTO = Support.findTrainingById(getIntent().getExtras().getLong("trainingId"));
            if (trainingDTO == null || trainingDTO.getParentTrainingId() == null) {
                trainingDTOs = Support.findAllByParentTrainingId(null);
            } else {
                trainingDTOs = Support.findAllByParentTrainingId(trainingDTO.getParentTrainingId());
            }
            tvTrainingTitle.setText(getIntent().getExtras().getString("title"));

            getIntent().getExtras().clear();
        } else {
            imgBtnMusic.setTag(MediaStatus.OFF.name());
            imgBtnSpeaker.setTag(MediaStatus.OFF.name());
            trainingDTOs = Support.findAllByParentTrainingId(null);
        }

        setSpeaker(MediaStatus.valueOf(imgBtnSpeaker.getTag().toString()));
        setMusic(MediaStatus.valueOf(imgBtnMusic.getTag().toString()));

        setMatchesIntoRecyclerView(trainingDTOs);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    protected void setBackOnClick() {
        TrainingDTO parentTrainingDTO = Support.findTrainingById(trainingDTOs.get(0).getParentTrainingId());
        if (parentTrainingDTO == null) {
            setHomeOnClick();
        } else {
            trainingDTOs = Support.findAllByParentTrainingId(parentTrainingDTO.getParentTrainingId());
            int lastedEnterChar = tvTrainingTitle.getText().toString().lastIndexOf('-');
            if (lastedEnterChar > 0) {
                tvTrainingTitle.setText(tvTrainingTitle.getText().toString().substring(0, lastedEnterChar));
            } else {
                tvTrainingTitle.setText("");
            }
            setMatchesIntoRecyclerView(trainingDTOs);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setMatchesIntoRecyclerView(List<TrainingDTO> trainingDTOS) {
        recyclerView.setAdapter(new TrainingItemAdapter((ArrayList<TrainingDTO>) trainingDTOS, this, trainingClicked -> {
            TrainingDTO trainingDTO = Support.findTrainingById(trainingClicked.getId());

            String title = tvTrainingTitle.getText().length() == 0
                    ? trainingDTO.getTitle() : tvTrainingTitle.getText() + "-" + trainingDTO.getTitle();

            if (!trainingDTO.getChildTrainingDTOs().isEmpty()) {
                tvTrainingTitle.setText(title);
                setMatchesIntoRecyclerView(trainingDTO.getChildTrainingDTOs());
                trainingDTOs = trainingDTO.getChildTrainingDTOs();
            } else {
                Intent intent = new Intent(this, TrainingDetailsActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("speaker", imgBtnSpeaker.getTag().toString());
                intent.putExtra("music", imgBtnMusic.getTag().toString());
                intent.putExtra("trainingId", trainingDTO.getId());
                startActivity(intent);
            }
        }));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void callFindAllTrainingsAPI() {
        Call<List<TrainingDTO>> call = trainingAPI.findAll();
        call.enqueue(new Callback<List<TrainingDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<TrainingDTO>> call, @NonNull Response<List<TrainingDTO>> response) {
                Log.d("Training-findAll", response.code() + "");
                trainingDTOs = response.body();
            }

            @Override
            public void onFailure(@NonNull Call<List<TrainingDTO>> call, @NonNull Throwable throwable) {
                Log.d("Training-findAll", Objects.requireNonNull(throwable.getMessage()));
                call.cancel();
            }
        });
    }
}
