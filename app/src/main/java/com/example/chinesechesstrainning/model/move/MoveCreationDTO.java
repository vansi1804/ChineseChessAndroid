package com.example.chinesechesstrainning.model.move;

import com.example.chinesechesstrainning.model.PlayBoardDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MoveCreationDTO {
    private Integer movingPieceId;
    private Integer toCol;
    private Integer toRow;
    private PlayBoardDTO playBoardDTO;
}
