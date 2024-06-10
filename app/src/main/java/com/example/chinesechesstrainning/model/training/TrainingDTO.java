package com.example.chinesechesstrainning.model.training;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainingDTO {

    private Long id;

    private String title;

    private Long parentTrainingId;

    private List<TrainingDTO> childTrainingDTOs;
}
