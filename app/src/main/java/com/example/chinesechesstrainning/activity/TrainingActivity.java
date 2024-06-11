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
import com.example.chinesechesstrainning.api.RetrofitClient;
import com.example.chinesechesstrainning.api.TrainingAPI;
import com.example.chinesechesstrainning.enumerable.MediaStatus;
import com.example.chinesechesstrainning.model.training.TrainingDTO;
import com.example.chinesechesstrainning.model.training.TrainingDetailDTO;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainingActivity extends HeaderActivity {
    private TrainingAPI trainingAPI;

    private TextView tvTrainingTitle;
    private RecyclerView recyclerView;
    private List<TrainingDTO> trainingDTOs;

    private static final Stack<List<TrainingDTO>> trainingDTOStack = new Stack<>();

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
            tvTrainingTitle.setText(getIntent().getExtras().getString("title"));

            if (Objects.equals(getIntent().getExtras().getString("source"), "MainActivity")) {
                trainingDTOs = new ArrayList<>();
                callFindAllTrainingsAPI();
            } else {
                trainingDTOs = trainingDTOStack.pop();
            }
            getIntent().getExtras().clear();
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
        if (trainingDTOStack.isEmpty()) {
            setHomeOnClick();
        } else {
            int lastConnectCharacter = tvTrainingTitle.getText().toString().lastIndexOf('-');
            if (lastConnectCharacter > 0) {
                tvTrainingTitle.setText(tvTrainingTitle.getText().toString().substring(0, lastConnectCharacter));
            } else {
                tvTrainingTitle.setText("");
            }

            trainingDTOs = trainingDTOStack.pop();
            setMatchesIntoRecyclerView(trainingDTOs);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setMatchesIntoRecyclerView(List<TrainingDTO> trainingDTOS) {
        recyclerView.setAdapter(new TrainingItemAdapter((ArrayList<TrainingDTO>) trainingDTOS, this, trainingClicked -> {
            Log.d("TAG", "trainingId: " + trainingClicked.toString());

            String title = tvTrainingTitle.getText().length() == 0
                    ? trainingClicked.getTitle() : tvTrainingTitle.getText() + "-" + trainingClicked.getTitle();

            if (!CollectionUtils.isEmpty(trainingClicked.getChildTrainingDTOs())) {
                tvTrainingTitle.setText(title);
                trainingDTOStack.push(trainingDTOs);
                trainingDTOs = trainingClicked.getChildTrainingDTOs();
                setMatchesIntoRecyclerView(trainingDTOs);
            } else {
                Intent intent = new Intent(this, TrainingDetailsActivity.class);
                intent.putExtra("source", "TrainingActivity");
                intent.putExtra("title", title);
                intent.putExtra("speaker", imgBtnSpeaker.getTag().toString());
                intent.putExtra("music", imgBtnMusic.getTag().toString());
                intent.putExtra("trainingId", trainingClicked.getId());
                startActivity(intent);
            }
        }));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void callFindAllTrainingsAPI() {
        String tag = "Training-findAll";

        Call<List<TrainingDTO>> call = trainingAPI.findAll();
        call.enqueue(new Callback<List<TrainingDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<TrainingDTO>> call, @NonNull Response<List<TrainingDTO>> response) {
                Log.d(tag, "Response code: " + response.code());
                if (response.isSuccessful()) {
                    trainingDTOs = response.body();
                    setMatchesIntoRecyclerView(trainingDTOs);
                } else {
                    Log.e(tag, "Response not successful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<TrainingDTO>> call, @NonNull Throwable throwable) {
                Log.e(tag, "API call failed", throwable);
                call.cancel();
            }
        });
    }
}
