package com.example.chinesechesstrainning.model;

import com.example.chinesechesstrainning.R;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PieceDTO implements Serializable {

    private int id;
    private String name;
    private boolean red;
    private int currentCol;
    private int currentRow;
    private int imageSource;

    private static final Map<String, Integer> pieceImageMap = new HashMap<>();

    static {
        // Define mapping between name and image resource for red pieces
        pieceImageMap.put("GENERAL_red", R.drawable.red_general);
        pieceImageMap.put("GUARD_red", R.drawable.red_guard);
        pieceImageMap.put("ELEPHANT_red", R.drawable.red_elephant);
        pieceImageMap.put("HORSE_red", R.drawable.red_horse);
        pieceImageMap.put("CHARIOT_red", R.drawable.red_chariot);
        pieceImageMap.put("CANNON_red", R.drawable.red_cannon);
        pieceImageMap.put("SOLDIER_red", R.drawable.red_soldier);

        // Define mapping between name and image resource for black pieces
        pieceImageMap.put("GENERAL_black", R.drawable.black_general);
        pieceImageMap.put("GUARD_black", R.drawable.black_guard);
        pieceImageMap.put("ELEPHANT_black", R.drawable.black_elephant);
        pieceImageMap.put("HORSE_black", R.drawable.black_horse);
        pieceImageMap.put("CHARIOT_black", R.drawable.black_chariot);
        pieceImageMap.put("CANNON_black", R.drawable.black_cannon);
        pieceImageMap.put("SOLDIER_black", R.drawable.black_soldier);
    }

//    public PieceDTO(PieceDTO pieceDTO) {
//        setId(pieceDTO.getId());
//        setName(pieceDTO.getName());
//        setCurrentCol(pieceDTO.getCurrentCol());
//        setCurrentRow(pieceDTO.getCurrentRow());
//        setRed(pieceDTO.isRed());
//        setImageSource();
//    }

    public void setImageSource() {
        String pieceKey = name.toUpperCase() + "_" + (red ? "red" : "black");
        Integer resource = pieceImageMap.get(pieceKey);
        if (resource != null) {
            imageSource = resource;
        }
    }
}
