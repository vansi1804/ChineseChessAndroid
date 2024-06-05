package com.example.chinesechesstrainning.model.move;

import com.example.chinesechesstrainning.model.PieceDTO;
import com.example.chinesechesstrainning.model.PlayBoardDTO;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MoveDetailDTO implements Serializable {

    private PieceDTO movingPieceDTO;

    private int toCol;

    private int toRow;

    private PieceDTO deadPieceDTO;

    private PlayBoardDTO playBoardDTO;

    private PieceDTO checkedGeneralPieceDTO;

    private boolean isCheckmateState;
}
