package com.example.chinesechesstrainning.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chinesechesstrainning.R;
import com.example.chinesechesstrainning.adapter.TrainingItemAdapter;
import com.example.chinesechesstrainning.api.RetrofitClient;
import com.example.chinesechesstrainning.api.TrainingAPI;
import com.example.chinesechesstrainning.model.training.TrainingDTO;
import com.example.chinesechesstrainning.service.media.MusicService;
import com.example.chinesechesstrainning.support.Support;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainingActivity extends AppCompatActivity implements View.OnClickListener {

    private TrainingAPI trainingAPI;
    private ImageButton imgBtnBack;
    private ImageButton imgBtnHome;
    private ImageButton imgBtnSpeaker;
    private ImageButton imgBtnMusic;
    private TextView tvTrainingTitle;
    private RecyclerView recyclerView;
    private List<TrainingDTO> trainingDTOs;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        trainingAPI = RetrofitClient.getRetrofitInstance().create(TrainingAPI.class);

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
                tvTrainingTitle.setText(null);
                trainingDTOs = Support.findAllByParentTrainingId(null);
            } else {
                tvTrainingTitle.setText(getIntent().getExtras().getString("title"));
                trainingDTOs = Support.findAllByParentTrainingId(trainingDTO.getParentTrainingId());
            }

            getIntent().getExtras().clear();
        } else {
            imgBtnMusic.setTag("off");
            imgBtnSpeaker.setTag("off");
            tvTrainingTitle.setText(null);
            trainingDTOs = Support.findAllByParentTrainingId(null);
        }

        setImgBtnMusicService(imgBtnMusic.getTag().toString());
        setImgBtnSpeakerService(imgBtnSpeaker.getTag().toString());

        setMatchesIntoRecyclerView(trainingDTOs);
    }

    @Override
    public void onClick(View v) {
        if (v == imgBtnHome) {
            homeOnClick();
        } else if (v == imgBtnBack) {
            backOnClick();
        } else if (v == imgBtnSpeaker) {
            setImgBtnSpeakerService(imgBtnSpeaker.getTag().equals("on") ? "off" : "on");
        } else if (v == imgBtnMusic) {
            setImgBtnMusicService(imgBtnMusic.getTag().equals("on") ? "off" : "on");
        }
    }

    @SuppressLint("SetTextI18n")
    private void setMatchesIntoRecyclerView(List<TrainingDTO> trainingDTOS) {
        recyclerView.setAdapter(new TrainingItemAdapter((ArrayList<TrainingDTO>) trainingDTOS, this, trainingClicked -> {
            TrainingDTO trainingDTO = Support.findTrainingById(trainingClicked.getId());

            String title = tvTrainingTitle.getText().length() == 0
                    ? trainingDTO.getTitle() : tvTrainingTitle.getText() + "-" + trainingDTO.getTitle();

            if (!trainingDTO.getChildTrainingDTOs().isEmpty()) {
                imgBtnBack.setVisibility(View.VISIBLE);
                imgBtnHome.setVisibility(View.VISIBLE);
                tvTrainingTitle.setText(title);
                setMatchesIntoRecyclerView(trainingDTO.getChildTrainingDTOs());
                trainingDTOs = trainingDTO.getChildTrainingDTOs();
            } else {
                if (imgBtnMusic.getTag().toString().equals("on")) {
                    stopService(new Intent(this, MusicService.class));
                }
                Intent intent = new Intent(this, PlayBoardActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("speaker", imgBtnSpeaker.getTag().toString());
                intent.putExtra("music", imgBtnMusic.getTag().toString());
                intent.putExtra("trainingId", trainingDTO.getId());
                startActivity(intent);
            }
        }));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setImgBtnMusicService(String tag) {
        imgBtnMusic.setTag(tag);
        if (imgBtnMusic.getTag().toString().equals("on")) {
            imgBtnMusic.setBackground(getDrawable(R.drawable.music_on));
            if (MusicService.getInstance() == null) {
                startService(new Intent(this, MusicService.class));
            }
        } else {
            imgBtnMusic.setBackground(getDrawable(R.drawable.music_off));
            stopService(new Intent(this, MusicService.class));
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setImgBtnSpeakerService(String tag) {
        imgBtnSpeaker.setTag(tag);
        if (imgBtnSpeaker.getTag().toString().equals("on")) {
            imgBtnSpeaker.setBackground(getDrawable(R.drawable.speaker_on));
        } else {
            imgBtnSpeaker.setBackground(getDrawable(R.drawable.speaker_off));
        }
    }

    public void callFindAllTrainingsAPI(){
        Call<List<TrainingDTO>> call = trainingAPI.findAll();
        call.enqueue(new Callback<List<TrainingDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<TrainingDTO>> call, @NonNull Response<List<TrainingDTO>> response) {
                Log.d("Training-findAll",response.code()+"");
                trainingDTOs = response.body();
            }

            @Override
            public void onFailure(@NonNull Call<List<TrainingDTO>> call, @NonNull Throwable throwable) {
                Log.d("Training-findAll", Objects.requireNonNull(throwable.getMessage()));
                call.cancel();
            }
        });
    }

    public void homeOnClick(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("speaker", imgBtnSpeaker.getTag().toString());
        intent.putExtra("music", imgBtnMusic.getTag().toString());
        startActivity(intent);
    }

    public void backOnClick(){
        TrainingDTO parentTrainingDTO = Support.findTrainingById(trainingDTOs.get(0).getParentTrainingId());
        if (parentTrainingDTO == null){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("speaker", imgBtnSpeaker.getTag().toString());
            intent.putExtra("music", imgBtnMusic.getTag().toString());

            startActivity(intent);
        }else{
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

}
