package com.example.chinesechesstrainning.model.move;

import com.example.chinesechesstrainning.model.PlayBoardDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AvailableMoveRequestDTO {

    private Integer movingPieceId;

    private PlayBoardDTO playBoardDTO;
}
