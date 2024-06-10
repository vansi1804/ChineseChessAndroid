package com.example.chinesechesstrainning.model.training;

import com.example.chinesechesstrainning.model.move.MoveHistoryDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainingDetailDTO {

    private TrainingDTO trainingDTO;

    private long totalTurn;

    private Map<Long, MoveHistoryDTO> moveHistoryDTOs;
}
