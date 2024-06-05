package com.example.chinesechesstrainning.model.move;

import com.example.chinesechesstrainning.model.PieceDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MoveHistoryDTO extends MoveDetailDTO {

    private long turn;

    private String description;

    private List<PieceDTO> lastDeadPieceDTOs;
}
