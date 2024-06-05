package com.example.chinesechesstrainning.enumerable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PlayBoardSize {
    COL(9),
    ROW(10);

    private final int size;
}
