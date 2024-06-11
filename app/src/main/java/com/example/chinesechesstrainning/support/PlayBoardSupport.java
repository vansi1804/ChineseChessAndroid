package com.example.chinesechesstrainning.support;

import android.content.Context;
import android.util.Log;
import android.widget.ImageButton;
import com.example.chinesechesstrainning.R;
import com.example.chinesechesstrainning.enumerable.PlayBoardSize;
import com.example.chinesechesstrainning.model.PieceDTO;
import com.example.chinesechesstrainning.model.PlayBoardDTO;
import com.example.chinesechesstrainning.model.move.MoveHistoryDTO;

public class PlayBoardSupport {

    public static void setImageButtonPlayBoard(Context context, ImageButton[][] imagePlayBoards, PlayBoardDTO playBoardDTO, PieceDTO movingPieceDTO) {
        int cols = imagePlayBoards.length;
        int rows = imagePlayBoards[0].length;

        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                ImageButton button = imagePlayBoards[col][row];
                button.setImageDrawable(null);
                button.setBackground(null);

                PieceDTO piece = playBoardDTO.getState()[col][row];
                if (piece != null) {
                    piece.setImageSource();
                    Log.d("piece state", piece.toString());
                    button.setImageResource(piece.getImageSource());
                    if (movingPieceDTO != null && piece.getId() == movingPieceDTO.getId()) {
                        button.setBackgroundResource(R.drawable.move);
                    }
                } else if (movingPieceDTO != null && col == movingPieceDTO.getCurrentCol() && row == movingPieceDTO.getCurrentRow()) {
                    button.setBackgroundResource(R.drawable.move);
                }
            }
        }
    }

    public static void setImageButtonPlayBoard(Context context, ImageButton[][] imagePlayBoards, MoveHistoryDTO moveHistoryDTO) {
        PlayBoardDTO playBoardDTO = moveHistoryDTO.getPlayBoardDTO();
        PieceDTO movingPieceDTO = moveHistoryDTO.getMovingPieceDTO();
        PieceDTO generalBeingChecked = moveHistoryDTO.getCheckedGeneralPieceDTO();

        for (int col = 0; col < imagePlayBoards.length; col++) {
            for (int row = 0; row < imagePlayBoards[0].length; row++) {
                imagePlayBoards[col][row].setImageDrawable(null);
                imagePlayBoards[col][row].setBackground(null);

                PieceDTO piece = playBoardDTO.getState()[col][row];
                if (piece != null) {
                    piece.setImageSource();
                    Log.d("piece state", piece.toString());
                    imagePlayBoards[col][row].setBackgroundResource(piece.getImageSource());
                    if (movingPieceDTO != null && piece.getId() == movingPieceDTO.getId()) {
                        imagePlayBoards[col][row].setImageResource(R.drawable.move);
                    }
                    if (generalBeingChecked != null && piece.getId() == generalBeingChecked.getId()){
                        imagePlayBoards[col][row].setImageResource(R.drawable.checking_general);
                    }
                } else if (movingPieceDTO != null && col == movingPieceDTO.getCurrentCol() && row == movingPieceDTO.getCurrentRow()) {
                    imagePlayBoards[col][row].setImageResource(R.drawable.move);
                }
            }
        }
    }
}
