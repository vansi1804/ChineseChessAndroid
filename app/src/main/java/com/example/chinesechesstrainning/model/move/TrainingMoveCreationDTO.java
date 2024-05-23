package com.example.chinesechesstrainning.model.move;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TrainingMoveCreationDTO extends MoveDetailDTO {

    private Long trainingId;

    public TrainingMoveCreationDTO(Integer pieceId, Integer toCol, Integer toRow, Long trainingId) {
        super(pieceId, toCol, toRow);
        this.trainingId = trainingId;
    }
}
