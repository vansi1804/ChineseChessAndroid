package com.example.chinesechesstrainning.model.move;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TrainingMoveCreationDTO {

    private Long trainingId;

    private Integer pieceId;

    private Integer toCol;

    private Integer toRow;
}
