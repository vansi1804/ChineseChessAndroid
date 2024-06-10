package com.example.chinesechesstrainning.model.move;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainingMoveCreationDTO {

    private Long trainingId;

    private Integer pieceId;

    private Integer toCol;

    private Integer toRow;
}
