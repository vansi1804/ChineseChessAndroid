package com.example.chinesechesstrainning.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PieceDTO implements Serializable {

    private long id;
    private String name;
    private boolean isRed;
    private int currentCol;
    private int currentRow;
    private int image;

}
