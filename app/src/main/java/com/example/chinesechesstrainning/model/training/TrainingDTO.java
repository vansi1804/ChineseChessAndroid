package com.example.chinesechesstrainning.model.training;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TrainingDTO {

    private Long id;

    private String title;

    private Long parentTrainingId;

    private List<TrainingDTO> childTrainingDTOs;
}
