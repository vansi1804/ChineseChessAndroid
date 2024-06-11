package com.example.chinesechesstrainning.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.chinesechesstrainning.R;
import com.example.chinesechesstrainning.api.RetrofitClient;
import com.example.chinesechesstrainning.api.TrainingAPI;
import com.example.chinesechesstrainning.enumerable.MediaStatus;
import com.example.chinesechesstrainning.model.PieceDTO;
import com.example.chinesechesstrainning.model.PlayBoardDTO;
import com.example.chinesechesstrainning.model.move.MoveHistoryDTO;
import com.example.chinesechesstrainning.model.training.TrainingDetailDTO;
import com.example.chinesechesstrainning.service.media.SpeakerService;
import com.example.chinesechesstrainning.support.PlayBoardSupport;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainingDetailsActivity extends HeaderActivity {
    private TrainingAPI trainingAPI;
    private TextView tvTrainingTitle;
    private ImageButton[][] imagePlayBoards;
    private ImageButton imgBtnSwapBoard;
    private Long currentTurn = 0L;
    private TextView tvTurn;
    private TextView tvMoveDescription;
    private ImageButton imgBtnPreviousTurn;
    private ImageButton imgBtnNextTurn;
    private TrainingDetailDTO trainingDetailDTO;
    private final ImageButton[][] startPlayBoards = PlayBoardSupport.getStartPlayBoard(this);

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_details);

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

        imagePlayBoards = mapImageButtonPlayBoard(false);

        imgBtnSwapBoard = findViewById(R.id.btn_swap_board);
        imgBtnSwapBoard.setTag("false");
        imgBtnSwapBoard.setOnClickListener(this);

        tvTurn = findViewById(R.id.tv_turn);
        tvMoveDescription = findViewById(R.id.tv_move_description);

        imgBtnPreviousTurn = findViewById(R.id.btn_previous_turn);
        imgBtnPreviousTurn.setOnClickListener(this);

        imgBtnNextTurn = findViewById(R.id.btn_next_turn);
        imgBtnNextTurn.setOnClickListener(this);

        if (getIntent().getExtras() != null) {
            imgBtnSpeaker.setTag(getIntent().getExtras().getString("speaker"));
            imgBtnMusic.setTag(getIntent().getExtras().getString("music"));
            tvTrainingTitle.setText(getIntent().getExtras().getString("title"));
            trainingDetailDTO = new TrainingDetailDTO();
            callFindTrainingDetailsAPI(getIntent().getExtras().getLong("trainingId"));

            getIntent().getExtras().clear();
        } else {
            imgBtnMusic.setTag(MediaStatus.OFF.name());
            imgBtnSpeaker.setTag(MediaStatus.OFF.name());
            tvTrainingTitle.setText(null);
        }

        setSpeaker(MediaStatus.valueOf(imgBtnSpeaker.getTag().toString()));
        setMusic(MediaStatus.valueOf(imgBtnMusic.getTag().toString()));

        buildTurnEvent(currentTurn);
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == imgBtnSwapBoard) {
            setSwapBoardOnClick();
        } else if (v == imgBtnPreviousTurn || v == imgBtnNextTurn) {
            if (MediaStatus.ON.equals(imgBtnSpeaker.getTag())) {
                startService(new Intent(this, SpeakerService.class));
            }

            buildTurnEvent((v == imgBtnPreviousTurn) ? --currentTurn : ++currentTurn);
        }
    }

    private void setSwapBoardOnClick() {
        if (imgBtnSwapBoard.getTag().equals("true")) {
            imgBtnSwapBoard.setTag("false");
            imagePlayBoards = mapImageButtonPlayBoard(false);
        } else {
            imgBtnSwapBoard.setTag("true");
            imagePlayBoards = mapImageButtonPlayBoard(true);
        }

        buildTurnEvent(currentTurn);
    }

    @Override
    protected void setBackOnClick() {
        Intent intent = new Intent(this, TrainingActivity.class);
        intent.putExtra("source", "TrainingDetailsActivity");
        intent.putExtra("title", tvTrainingTitle.getText());
        intent.putExtra("speaker", imgBtnSpeaker.getTag().toString());
        intent.putExtra("music", imgBtnMusic.getTag().toString());
        startActivity(intent);
    }

    public ImageButton[][] mapImageButtonPlayBoard(boolean isSwap) {
        ImageButton[][] imageButtons = PlayBoardSupport.init(this);
        for (int col = 0; col < imageButtons.length; col++) {
            for (int row = 0; row < imageButtons[0].length; row++) {
                int i;
                int j;
                if (isSwap) {
                    i = imageButtons.length - 1 - col;
                    j = imageButtons[0].length - 1 - row;
                } else {
                    i = col;
                    j = row;
                }
                @SuppressLint("DiscouragedApi")
                int resourceId = getResources().getIdentifier(
                        "img_btn_position_" + i + "_" + j,
                        "id",
                        getPackageName()
                );
                imageButtons[col][row] = findViewById(resourceId);
                imageButtons[col][row].setTag(col + "-" + row);

                imageButtons[col][row].setOnClickListener(this);
            }
        }
        return imageButtons;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setImageButtonPlayBoard(PlayBoardDTO playBoardDTO, PieceDTO movingPieceDTO) {
        PlayBoardSupport.setImageButtonPlayBoard(this, imagePlayBoards, playBoardDTO, movingPieceDTO);
    }


    public void move(Long turn) {
        if (trainingDetailDTO != null) {
            MoveHistoryDTO moveHistoryDTO = trainingDetailDTO.getMoveHistoryDTOs().get(turn);
            if (moveHistoryDTO != null) {
                tvMoveDescription.setText(moveHistoryDTO.getDescription());
                setImageButtonPlayBoard(moveHistoryDTO.getPlayBoardDTO(), moveHistoryDTO.getMovingPieceDTO());
            }
        } else {
            tvMoveDescription.setText(null);
        }
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    public void buildTurnEvent(Long turn) {
        tvTurn.setText(turn + "/" + (trainingDetailDTO != null ? trainingDetailDTO.getTotalTurn() : 0));
        move(turn);

        imgBtnNextTurn.setEnabled(true);
        imgBtnNextTurn.setBackground(getDrawable(R.drawable.next_turn));
        imgBtnPreviousTurn.setEnabled(true);
        imgBtnPreviousTurn.setBackground(getDrawable(R.drawable.previous_turn));

        if (turn == 0) {
            PlayBoardSupport.copyImageButtonDrawables(startPlayBoards, imagePlayBoards);
            imgBtnPreviousTurn.setEnabled(false);
            imgBtnPreviousTurn.setBackground(getDrawable(R.drawable.previous_turn_disable));
        } else if (turn == (trainingDetailDTO != null ? trainingDetailDTO.getTotalTurn() : 0)) {
            imgBtnNextTurn.setEnabled(false);
            imgBtnNextTurn.setBackground(getDrawable(R.drawable.next_turn_disable));
        } else {
            imagePlayBoards[0][1].setImageResource(R.drawable.black_soldier);
        }
    }

    private void callFindTrainingDetailsAPI(Long trainingId) {
        String tag = "Training-findDetails";
        Call<TrainingDetailDTO> call = trainingAPI.findDetailsById(trainingId);
        call.enqueue(new Callback<TrainingDetailDTO>() {
            @Override
            public void onResponse(Call<TrainingDetailDTO> call, Response<TrainingDetailDTO> response) {
                Log.d(tag, "Response code: " + response.code());
                if (response.isSuccessful()) {
                    trainingDetailDTO = response.body();
                } else {
                    Log.e(tag, "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<TrainingDetailDTO> call, Throwable throwable) {
                Log.e(tag, "API call failed", throwable);
                call.cancel();
            }
        });

    }


}
