package com.example.chinesechesstrainning.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.chinesechesstrainning.R;
import com.example.chinesechesstrainning.enumerable.MediaStatus;
import com.example.chinesechesstrainning.model.PieceDTO;
import com.example.chinesechesstrainning.model.PlayBoardDTO;
import com.example.chinesechesstrainning.model.move.MoveHistoryDTO;
import com.example.chinesechesstrainning.model.training.TrainingDetailDTO;
import com.example.chinesechesstrainning.service.media.MusicService;
import com.example.chinesechesstrainning.service.media.SpeakerService;
import com.example.chinesechesstrainning.support.Support;

public class TrainingDetailsActivity extends HeaderActivity {
    private static final int ROW = 10;
    private static final int COL = 9;

    private TextView tvTrainingTitle;
    private static ImageButton[][] imagePlayBoard;
    private ImageButton imgBtnSwapBoard;
    private Long currentTurn = 0L;
    private TextView tvTurn;
    private TextView tvMoveDescription;
    private ImageButton imgBtnPreviousTurn;
    private ImageButton imgBtnNextTurn;
    private TrainingDetailDTO trainingDetailDTO;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_details);

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

        imagePlayBoard = new ImageButton[COL][ROW];
        mapImageButtonPlayBoard(false);

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
            trainingDetailDTO = Support.findTrainingDetailById(getIntent().getExtras().getLong("trainingId"));

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
            mapImageButtonPlayBoard(false);
        } else {
            imgBtnSwapBoard.setTag("true");
            mapImageButtonPlayBoard(true);
        }

        buildTurnEvent(currentTurn);
    }

    @Override
    protected void setBackOnClick() {
        Intent intent = new Intent(this, TrainingActivity.class);
        intent.putExtra("title", tvTrainingTitle.getText());
        intent.putExtra("speaker", imgBtnSpeaker.getTag().toString());
        intent.putExtra("music", imgBtnMusic.getTag().toString());
        intent.putExtra("trainingId", trainingDetailDTO.getTrainingDTO().getId());
        startActivity(intent);
    }

    public void mapImageButtonPlayBoard(boolean isSwap) {
        for (int col = 0; col <= COL - 1; col++) {
            for (int row = 0; row <= ROW - 1; row++) {
                int i;
                int j;
                if (isSwap) {
                    i = COL - 1 - col;
                    j = ROW - 1 - row;
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
                imagePlayBoard[col][row] = findViewById(resourceId);
                imagePlayBoard[col][row].setTag(col + "-" + row);

                imagePlayBoard[col][row].setOnClickListener(this);
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setImagePlayBoard(PieceDTO movingPieceDTO, PlayBoardDTO playBoardDTO) {
        for (int col = 0; col < COL; col++) {
            for (int row = 0; row < ROW; row++) {
                imagePlayBoard[col][row].setImageDrawable(null);
                imagePlayBoard[col][row].setBackground(null);

                PieceDTO piece = playBoardDTO.getState()[col][row];

                if (piece != null) {
                    imagePlayBoard[col][row].setImageDrawable(getDrawable(piece.getImage()));

                    if (movingPieceDTO != null && piece.getId() == movingPieceDTO.getId()) {
                        imagePlayBoard[col][row].setBackgroundResource(R.drawable.move);
                    }
                } else if (movingPieceDTO != null && col == movingPieceDTO.getCurrentCol() && row == movingPieceDTO.getCurrentRow()) {
                    imagePlayBoard[col][row].setBackgroundResource(R.drawable.move);
                }
            }
        }
    }


    public void move(Long turn) {
        MoveHistoryDTO moveHistoryDTO = trainingDetailDTO.getMoveHistoryDTOs().get(turn);
        if (moveHistoryDTO != null) {
            tvMoveDescription.setText(moveHistoryDTO.getDescription());
            setImagePlayBoard(moveHistoryDTO.getMovingPieceDTO(), moveHistoryDTO.getPlayBoardDTO());
        } else {
            tvMoveDescription.setText(null);
        }
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    public void buildTurnEvent(Long turn) {
        tvTurn.setText(turn + "/" + this.trainingDetailDTO.getTotalTurn());
        move(turn);

        imgBtnNextTurn.setEnabled(true);
        imgBtnNextTurn.setBackground(getDrawable(R.drawable.next_turn));
        imgBtnPreviousTurn.setEnabled(true);
        imgBtnPreviousTurn.setBackground(getDrawable(R.drawable.previous_turn));

        if (turn == 0) {
            setImagePlayBoard(null, Support.generatePlayBoard());
            imgBtnPreviousTurn.setEnabled(false);
            imgBtnPreviousTurn.setBackground(getDrawable(R.drawable.previous_turn_disable));
        }
        if (turn == trainingDetailDTO.getTotalTurn()) {
            imgBtnNextTurn.setEnabled(false);
            imgBtnNextTurn.setBackground(getDrawable(R.drawable.next_turn_disable));
        }
    }
}
