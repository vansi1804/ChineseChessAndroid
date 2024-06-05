package com.example.chinesechesstrainning.support;

import android.widget.ImageButton;
import android.content.Context;

import com.example.chinesechesstrainning.R;
import com.example.chinesechesstrainning.model.PieceDTO;
import com.example.chinesechesstrainning.model.PlayBoardDTO;

public class PlayBoardSupport {
    public static void setImageButtonPlayBoard(Context context, ImageButton[][] imagePlayBoards, PlayBoardDTO playBoardDTO, PieceDTO movingPieceDTO) {
        for (int col = 0; col < imagePlayBoards.length; col++) {
            for (int row = 0; row < imagePlayBoards[0].length; row++) {
                imagePlayBoards[col][row].setImageDrawable(null);
                imagePlayBoards[col][row].setBackground(null);

                PieceDTO piece = playBoardDTO.getState()[col][row];

                if (piece != null) {
                    imagePlayBoards[col][row].setImageResource(piece.getImage());

                    if (movingPieceDTO != null && piece.getId() == movingPieceDTO.getId()) {
                        imagePlayBoards[col][row].setBackgroundResource(R.drawable.move);
                    }
                } else if (movingPieceDTO != null && col == movingPieceDTO.getCurrentCol() && row == movingPieceDTO.getCurrentRow()) {
                    imagePlayBoards[col][row].setBackgroundResource(R.drawable.move);
                }
            }
        }
    }
}
