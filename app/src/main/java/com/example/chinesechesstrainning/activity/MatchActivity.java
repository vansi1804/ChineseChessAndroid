package com.example.chinesechesstrainning.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.chinesechesstrainning.R;
import com.example.chinesechesstrainning.api.MoveAPI;
import com.example.chinesechesstrainning.api.PlayBoardAPI;
import com.example.chinesechesstrainning.api.RetrofitClient;
import com.example.chinesechesstrainning.enumerable.MediaStatus;
import com.example.chinesechesstrainning.enumerable.PlayBoardSize;
import com.example.chinesechesstrainning.model.PieceDTO;
import com.example.chinesechesstrainning.model.PlayBoardDTO;
import com.example.chinesechesstrainning.model.move.AvailableMoveRequestDTO;
import com.example.chinesechesstrainning.model.move.MoveCreationDTO;
import com.example.chinesechesstrainning.model.move.MoveResponseDTO;
import com.example.chinesechesstrainning.service.media.speaker.CaptureSpeakerService;
import com.example.chinesechesstrainning.service.media.speaker.CheckSpeakerService;
import com.example.chinesechesstrainning.service.media.speaker.CheckmateSpeakerService;
import com.example.chinesechesstrainning.service.media.speaker.MoveSpeakerService;
import com.example.chinesechesstrainning.support.PlayBoardSupport;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchActivity extends HeaderActivity {
    private PlayBoardAPI playBoardAPI;
    private MoveAPI moveAPI;

    private TextView tvRedPlayerScore;
    private TextView tvBlackPlayerScore;
    private static ImageButton[][] imgBtnPlayBoards;
    private Button btnSuggestDraw;
    private Button btnSuggestLose;
    private Button btnRestartMatch;
    private PlayBoardDTO playBoardDTO;
    private boolean isSelectClick;
    private boolean isRedTurn;
    private PieceDTO selectedPieceDTO;
    private List<int[]> availableMoveIndexes;
    private MoveResponseDTO moveResponseDTO;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        playBoardAPI = RetrofitClient.getRetrofitInstance().create(PlayBoardAPI.class);
        moveAPI = RetrofitClient.getRetrofitInstance().create(MoveAPI.class);

        imgBtnHome = findViewById(R.id.img_btn_home);
        imgBtnHome.setOnClickListener(this);

        imgBtnBack = findViewById(R.id.img_btn_back);
        imgBtnBack.setOnClickListener(this);

        imgBtnSpeaker = findViewById(R.id.img_btn_speaker);
        imgBtnSpeaker.setOnClickListener(this);

        imgBtnMusic = findViewById(R.id.img_btn_music);
        imgBtnMusic.setOnClickListener(this);

        tvRedPlayerScore = findViewById(R.id.red_player_score);
        tvRedPlayerScore.setText("0");
        tvBlackPlayerScore = findViewById(R.id.black_player_score);
        tvBlackPlayerScore.setText("0");

        imgBtnPlayBoards = new ImageButton[PlayBoardSize.COL.getSize()][PlayBoardSize.ROW.getSize()];
        initImageButtonPlayBoards();
        PlayBoardSupport.setEnable(imgBtnPlayBoards, false);

        playBoardDTO = new PlayBoardDTO();
        availableMoveIndexes = new ArrayList<>();
        moveResponseDTO = new MoveResponseDTO();

        btnSuggestDraw = findViewById(R.id.btn_suggest_draw);
        btnSuggestDraw.setOnClickListener(this);
        btnSuggestDraw.setVisibility(View.INVISIBLE);

        btnSuggestLose = findViewById(R.id.btn_suggest_lose);
        btnSuggestLose.setOnClickListener(this);
        btnSuggestLose.setVisibility(View.INVISIBLE);

        btnRestartMatch = findViewById(R.id.btn_restart);
        btnRestartMatch.setOnClickListener(this);

        if (getIntent().getExtras() != null) {
            imgBtnSpeaker.setTag(getIntent().getExtras().getString("speaker"));
            Log.d("IntentSpeaker", imgBtnSpeaker.getTag().toString());
            imgBtnMusic.setTag(getIntent().getExtras().getString("music"));
            Log.d("IntentMusic", imgBtnMusic.getTag().toString());

            getIntent().getExtras().clear();
        } else {
            imgBtnMusic.setTag(MediaStatus.OFF.name());
            imgBtnSpeaker.setTag(MediaStatus.OFF.name());
        }

        setSpeaker(MediaStatus.valueOf(imgBtnSpeaker.getTag().toString()));
        setMusic(MediaStatus.valueOf(imgBtnMusic.getTag().toString()));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == btnRestartMatch) {
            isRedTurn = true;
            isSelectClick = true;
            callGeneratePlayBoard();
            PlayBoardSupport.setEnable(imgBtnPlayBoards, true);

            btnRestartMatch.setVisibility(View.INVISIBLE);
            btnSuggestDraw.setVisibility(View.VISIBLE);
            btnSuggestLose.setVisibility(View.VISIBLE);
        } else if (v == btnSuggestDraw) {
            PlayBoardSupport.setEnable(imgBtnPlayBoards, false);
            btnRestartMatch.setVisibility(View.VISIBLE);
            btnSuggestDraw.setVisibility(View.INVISIBLE);
            btnSuggestLose.setVisibility(View.INVISIBLE);
        } else if (v == btnSuggestLose) {
            endGame();
        } else {
            buildImgBtnPlayBoardsOnClick(v);
        }
    }

    public void endGame() {
        try {
            if (isRedTurn) {
                tvBlackPlayerScore.setText(String.valueOf(Integer.parseInt(tvBlackPlayerScore.getText().toString()) + 1));
            } else {
                tvRedPlayerScore.setText(String.valueOf(Integer.parseInt(tvRedPlayerScore.getText().toString()) + 1));
            }
        } catch (NumberFormatException e) {
            Log.e("MatchActivity", "Error parsing score", e);
        }
        PlayBoardSupport.setEnable(imgBtnPlayBoards,false);

        btnRestartMatch.setVisibility(View.VISIBLE);
        btnSuggestDraw.setVisibility(View.INVISIBLE);
        btnSuggestLose.setVisibility(View.INVISIBLE);
    }

    private void buildImgBtnPlayBoardsOnClick(View v) {
        ImageButton clickedImgBtnPlayBoard = (ImageButton) v;
        int[] clickedIndex = extractImageButtonTag(clickedImgBtnPlayBoard);
        if (clickedIndex != null) {
            if (isSelectClick) {
                // click to select piece
                buildImgBtnPlayBoardOnFirstClick(clickedIndex[0], clickedIndex[1]);
            } else {
                // click to move piece
                buildImgBtnPlayBoardOnSecondClick(clickedIndex[0], clickedIndex[1]);
            }
        }
    }

    private void buildImgBtnPlayBoardOnFirstClick(int col, int row) {
        PieceDTO pieceDTO = playBoardDTO.getState()[col][row];
        if (pieceDTO != null && pieceDTO.isRed() == isRedTurn) {
            PlayBoardSupport.clearImage(imgBtnPlayBoards);
            selectedPieceDTO = pieceDTO;
            Log.d("selectedPieceDTO", String.valueOf(pieceDTO));
            imgBtnPlayBoards[col][row].setImageResource(R.drawable.move);
            callFindAllAvailableMove(new AvailableMoveRequestDTO(pieceDTO.getId(), playBoardDTO));
            isSelectClick = false;
            Log.d("isSelectClick in end of buildImgBtnPlayBoardOnFirstClick", String.valueOf(isSelectClick));
        }
    }

    public void buildImgBtnPlayBoardOnSecondClick(int col, int row) {
        PieceDTO pieceDTO = playBoardDTO.getState()[col][row];
        if (pieceDTO != null && pieceDTO.isRed() == isRedTurn) {
            buildImgBtnPlayBoardOnFirstClick(col, row);
        } else {
            Log.d("AvailableMove", availableMoveIndexes.toString());
            // check move matching with available move
            for (int[] index : availableMoveIndexes) {
                if (col == index[0] && row == index[1]) {
                    Log.d("selectedPieceDTO", selectedPieceDTO.toString());
                    isSelectClick = !isSelectClick;
                    isRedTurn = !isRedTurn;
                    callMakeMove(new MoveCreationDTO(selectedPieceDTO.getId(), col, row, playBoardDTO));
                    break;
                }
            }
        }
    }

    private void highlightAvailableMoves() {
        for (int[] index : availableMoveIndexes) {
            PieceDTO availableTargetPieceDTO = playBoardDTO.getState()[index[0]][index[1]];
            if (availableTargetPieceDTO != null) {
                imgBtnPlayBoards[index[0]][index[1]].setImageResource(R.drawable.available_capture);
            } else {
                imgBtnPlayBoards[index[0]][index[1]].setImageResource(R.drawable.available_move);
            }
        }
    }

    private void highlightMakeMove() {
        Log.d("Speaker tag", imgBtnSpeaker.getTag().toString());
        if (MediaStatus.ON.equals(imgBtnSpeaker.getTag())) {
            if (moveResponseDTO.getDeadPieceDTO() != null || moveResponseDTO.getCheckedGeneralPieceDTO() != null) {
                if (moveResponseDTO.getDeadPieceDTO() != null) {
                    Log.d("TrainingDetailsActivity", "Starting CaptureSpeakerService");
                    startService(new Intent(this, CaptureSpeakerService.class));
                }
                if (moveResponseDTO.getCheckedGeneralPieceDTO() != null) {
                    if (moveResponseDTO.isCheckmateState()) {
                        Log.d("TrainingDetailsActivity", "Starting CheckmateSpeakerService");
                        startService(new Intent(this, CheckmateSpeakerService.class));
                        endGame();
                    } else {
                        Log.d("TrainingDetailsActivity", "Starting CheckSpeakerService");
                        startService(new Intent(this, CheckSpeakerService.class));
                    }
                }
            } else {
                Log.d("TrainingDetailsActivity", "Starting MoveSpeakerService");
                startService(new Intent(this, MoveSpeakerService.class));
            }
        }

        PlayBoardSupport.setImageButtonPlayBoard(this, imgBtnPlayBoards, moveResponseDTO.getPlayBoardDTO(), moveResponseDTO.getMovingPieceDTO(), moveResponseDTO.getCheckedGeneralPieceDTO());
        playBoardDTO = new PlayBoardDTO(moveResponseDTO.getPlayBoardDTO().getState());
    }

    private int[] extractImageButtonTag(ImageButton imageButton) {
        try {
            String[] index = imageButton.getTag().toString().split("-");
            return new int[]{Integer.parseInt(index[0]), Integer.parseInt(index[1])};
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void setBackOnClick() {
        setHomeOnClick();
    }

    public void initImageButtonPlayBoards() {
        for (int col = 0; col < imgBtnPlayBoards.length; col++) {
            for (int row = 0; row < imgBtnPlayBoards[0].length; row++) {

                @SuppressLint("DiscouragedApi")
                int resourceId = getResources().getIdentifier(
                        "img_btn_position_" + col + "_" + row,
                        "id",
                        getPackageName()
                );
                imgBtnPlayBoards[col][row] = findViewById(resourceId);
                imgBtnPlayBoards[col][row].setTag(col + "-" + row);
                imgBtnPlayBoards[col][row].setImageDrawable(null);
                imgBtnPlayBoards[col][row].setBackground(null);
                imgBtnPlayBoards[col][row].setOnClickListener(this);
            }
        }
    }

    private void mapImageButtonPlayBoards() {
        PlayBoardSupport.setImageButtonPlayBoard(this, imgBtnPlayBoards, playBoardDTO, null);
    }

    private void callGeneratePlayBoard() {
        String tag = "PlayBoard-generate";
        Call<PlayBoardDTO> call = playBoardAPI.generate();
        call.enqueue(new Callback<PlayBoardDTO>() {
            @Override
            public void onResponse(Call<PlayBoardDTO> call, Response<PlayBoardDTO> response) {
                Log.d(tag, "Response code: " + response.code());
                if (response.isSuccessful()) {
                    playBoardDTO = response.body();
                    mapImageButtonPlayBoards();
                } else {
                    Log.d(tag, "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<PlayBoardDTO> call, Throwable throwable) {
                Log.d(tag, "API call failed", throwable);
                call.cancel();
            }
        });
    }

    private void callFindAllAvailableMove(AvailableMoveRequestDTO availableMoveRequestDTO) {
        String tag = "Move-findAllAvailable";
        Call<List<int[]>> call = moveAPI.findAllAvailable(availableMoveRequestDTO);
        call.enqueue(new Callback<List<int[]>>() {
            @Override
            public void onResponse(Call<List<int[]>> call, Response<List<int[]>> response) {
                Log.d(tag, "Response code: " + response.code());
                if (response.isSuccessful()) {
                    availableMoveIndexes = response.body();
                    Log.d("AvailableIndex", availableMoveIndexes.toString());
                    highlightAvailableMoves();  // Call the method to process the available moves
                } else {
                    Log.d(tag, "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<List<int[]>> call, Throwable throwable) {
                Log.d(tag, "API call failed", throwable);
                call.cancel();
            }
        });
    }

    private void callMakeMove(MoveCreationDTO moveCreationDTO) {
        String tag = "Move - makeMove";
        Call<MoveResponseDTO> call = moveAPI.makeMove(moveCreationDTO);
        call.enqueue(new Callback<MoveResponseDTO>() {
            @Override
            public void onResponse(Call<MoveResponseDTO> call, Response<MoveResponseDTO> response) {
                Log.d(tag, "Response code: " + response.code());
                if (response.isSuccessful()) {
                    moveResponseDTO = response.body();
                    Log.d(tag, moveResponseDTO.toString());
                    highlightMakeMove();
                } else {
                    Log.d(tag, "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<MoveResponseDTO> call, Throwable throwable) {
                Log.d(tag, "API call failed", throwable);
                call.cancel();
            }
        });
    }

}
