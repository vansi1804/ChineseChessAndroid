package com.example.chinesechesstrainning.support;

import android.widget.ImageButton;
import android.content.Context;

import com.example.chinesechesstrainning.R;
import com.example.chinesechesstrainning.enumerable.PlayBoardSize;
import com.example.chinesechesstrainning.model.PieceDTO;
import com.example.chinesechesstrainning.model.PlayBoardDTO;

public class PlayBoardSupport {

    public static ImageButton[][] init(Context context) {
        ImageButton[][] imageButtons = new ImageButton[PlayBoardSize.COL.getSize()][PlayBoardSize.ROW.getSize()];
        for (int col = 0; col < PlayBoardSize.COL.getSize(); col++) {
            for (int row = 0; row < PlayBoardSize.ROW.getSize(); row++) {
                imageButtons[col][row] = new ImageButton(context);
                imageButtons[col][row].setImageDrawable(null);
                imageButtons[col][row].setBackground(null);
            }
        }
        return imageButtons;
    }


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

    public static void copyImageButtonDrawables(ImageButton[][] source, ImageButton[][] target) {
        for (int col = 0; col < source.length; col++) {
            for (int row = 0; row < source[0].length; row++) {
                target[col][row].setImageDrawable(null);
                target[col][row].setBackground(null);

                target[col][row].setImageDrawable(source[col][row].getDrawable());
                target[col][row].setBackground(source[col][row].getBackground());
            }
        }
    }

    public static ImageButton[][] getStartPlayBoard(Context context) {
        ImageButton[][] startBoard = init(context);

        // black
        startBoard[0][0].setImageResource(R.drawable.black_chariot);
        startBoard[1][0].setImageResource(R.drawable.black_horse);
        startBoard[2][0].setImageResource(R.drawable.black_elephant);
        startBoard[3][0].setImageResource(R.drawable.black_guard);
        startBoard[4][0].setImageResource(R.drawable.black_general);
        startBoard[5][0].setImageResource(R.drawable.black_guard);
        startBoard[6][0].setImageResource(R.drawable.black_elephant);
        startBoard[7][0].setImageResource(R.drawable.black_horse);
        startBoard[8][0].setImageResource(R.drawable.black_chariot);
        startBoard[1][2].setImageResource(R.drawable.black_cannon);
        startBoard[7][2].setImageResource(R.drawable.black_cannon);
        startBoard[0][3].setImageResource(R.drawable.black_soldier);
        startBoard[2][3].setImageResource(R.drawable.black_soldier);
        startBoard[4][3].setImageResource(R.drawable.black_soldier);
        startBoard[6][3].setImageResource(R.drawable.black_soldier);
        startBoard[8][3].setImageResource(R.drawable.black_soldier);

        // red
        startBoard[0][9].setImageResource(R.drawable.black_chariot);
        startBoard[1][9].setImageResource(R.drawable.black_horse);
        startBoard[2][9].setImageResource(R.drawable.black_elephant);
        startBoard[3][9].setImageResource(R.drawable.black_guard);
        startBoard[4][9].setImageResource(R.drawable.black_general);
        startBoard[5][9].setImageResource(R.drawable.black_guard);
        startBoard[6][9].setImageResource(R.drawable.black_elephant);
        startBoard[7][9].setImageResource(R.drawable.black_horse);
        startBoard[8][9].setImageResource(R.drawable.black_chariot);
        startBoard[1][7].setImageResource(R.drawable.black_cannon);
        startBoard[7][7].setImageResource(R.drawable.black_cannon);
        startBoard[0][6].setImageResource(R.drawable.black_soldier);
        startBoard[2][6].setImageResource(R.drawable.black_soldier);
        startBoard[4][6].setImageResource(R.drawable.black_soldier);
        startBoard[6][6].setImageResource(R.drawable.black_soldier);
        startBoard[8][6].setImageResource(R.drawable.black_soldier);

        return startBoard;
    }

}
