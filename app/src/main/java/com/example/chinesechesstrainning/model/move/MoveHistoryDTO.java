package com.example.chinesechesstrainning.model.move;

import com.example.chinesechesstrainning.model.PieceDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveHistoryDTO extends MoveResponseDTO {

    private long turn;

    private String description;

    private List<PieceDTO> lastDeadPieceDTOs;
}
