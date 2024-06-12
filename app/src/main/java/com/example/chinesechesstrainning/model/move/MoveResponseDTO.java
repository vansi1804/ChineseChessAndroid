package com.example.chinesechesstrainning.model.move;

import com.example.chinesechesstrainning.model.PieceDTO;
import com.example.chinesechesstrainning.model.PlayBoardDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveResponseDTO implements Serializable {

    private PieceDTO movingPieceDTO;

    private int toCol;

    private int toRow;

    private PieceDTO deadPieceDTO;

    private PlayBoardDTO playBoardDTO;

    private PieceDTO checkedGeneralPieceDTO;

    private boolean checkmateState;
}
