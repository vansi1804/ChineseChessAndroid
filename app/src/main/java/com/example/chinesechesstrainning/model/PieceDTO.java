package com.example.chinesechesstrainning.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PieceDTO implements Serializable {

    private long id;
    private String name;
    private boolean isRed;
    private int currentCol;
    private int currentRow;
    private int image;

    public PieceDTO(PieceDTO pieceDTO){
        setId(pieceDTO.getId());
        setName(pieceDTO.getName());
        setRed(pieceDTO.isRed());
        setCurrentCol(pieceDTO.getCurrentCol());
        setCurrentRow(pieceDTO.getCurrentRow());
        setImage(pieceDTO.getImage());
    }
}
