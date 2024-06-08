package com.example.chinesechesstrainning.support;

import com.example.chinesechesstrainning.R;
import com.example.chinesechesstrainning.model.PieceDTO;
import com.example.chinesechesstrainning.model.move.TrainingMoveCreationDTO;
import com.example.chinesechesstrainning.model.training.TrainingDTO;
import com.example.chinesechesstrainning.model.training.TrainingDetailDTO;

import java.util.ArrayList;
import java.util.List;

public class DataTest {

    public static List<PieceDTO> pieceData() {
        List<PieceDTO> pieceDTOS = new ArrayList<>();

        pieceDTOS.add(new PieceDTO(1, "Tốt", true, 0, 6, R.drawable.red_soldier));
        pieceDTOS.add(new PieceDTO(2, "Tốt", true, 2, 6, R.drawable.red_soldier));
        pieceDTOS.add(new PieceDTO(3, "Tốt", true, 4, 6, R.drawable.red_soldier));
        pieceDTOS.add(new PieceDTO(4, "Tốt", true, 6, 6, R.drawable.red_soldier));
        pieceDTOS.add(new PieceDTO(5, "Tốt", true, 8, 6, R.drawable.red_soldier));
        pieceDTOS.add(new PieceDTO(6, "Pháo", true, 1, 7, R.drawable.red_cannon));
        pieceDTOS.add(new PieceDTO(7, "Pháo", true, 7, 7, R.drawable.red_cannon));
        pieceDTOS.add(new PieceDTO(8, "Xe", true, 0, 9, R.drawable.red_chariot));
        pieceDTOS.add(new PieceDTO(9, "Xe", true, 8, 9, R.drawable.red_chariot));
        pieceDTOS.add(new PieceDTO(10, "Mã", true, 1, 9, R.drawable.red_horse));
        pieceDTOS.add(new PieceDTO(11, "Mã", true, 7, 9, R.drawable.red_horse));
        pieceDTOS.add(new PieceDTO(12, "Tượng", true, 2, 9, R.drawable.red_elephant));
        pieceDTOS.add(new PieceDTO(13, "Tượng", true, 6, 9, R.drawable.red_elephant));
        pieceDTOS.add(new PieceDTO(14, "Sĩ", true, 3, 9, R.drawable.red_guard));
        pieceDTOS.add(new PieceDTO(15, "Sĩ", true, 5, 9, R.drawable.red_guard));
        pieceDTOS.add(new PieceDTO(16, "Tướng", true, 4, 9, R.drawable.red_general));
        /**/
        pieceDTOS.add(new PieceDTO(17, "Tốt", false, 0, 3, R.drawable.black_soldier));
        pieceDTOS.add(new PieceDTO(18, "Tốt", false, 2, 3, R.drawable.black_soldier));
        pieceDTOS.add(new PieceDTO(19, "Tốt", false, 4, 3, R.drawable.black_soldier));
        pieceDTOS.add(new PieceDTO(20, "Tốt", false, 6, 3, R.drawable.black_soldier));
        pieceDTOS.add(new PieceDTO(21, "Tốt", false, 8, 3, R.drawable.black_soldier));
        pieceDTOS.add(new PieceDTO(22, "Pháo", false, 1, 2, R.drawable.black_cannon));
        pieceDTOS.add(new PieceDTO(23, "Pháo", false, 7, 2, R.drawable.black_cannon));
        pieceDTOS.add(new PieceDTO(24, "Xe", false, 0, 0, R.drawable.black_chariot));
        pieceDTOS.add(new PieceDTO(25, "Xe", false, 8, 0, R.drawable.black_chariot));
        pieceDTOS.add(new PieceDTO(26, "Mã", false, 1, 0, R.drawable.black_horse));
        pieceDTOS.add(new PieceDTO(27, "Mã", false, 7, 0, R.drawable.black_horse));
        pieceDTOS.add(new PieceDTO(28, "Tượng", false, 2, 0, R.drawable.black_elephant));
        pieceDTOS.add(new PieceDTO(29, "Tượng", false, 6, 0, R.drawable.black_elephant));
        pieceDTOS.add(new PieceDTO(30, "Sĩ", false, 3, 0, R.drawable.black_guard));
        pieceDTOS.add(new PieceDTO(31, "Sĩ", false, 5, 0, R.drawable.black_guard));
        pieceDTOS.add(new PieceDTO(32, "Tướng", false, 4, 0, R.drawable.black_general));

        return pieceDTOS;
    }

    public static List<TrainingDTO> trainingData() {
        List<TrainingDTO> trainingDTOS = new ArrayList<>();
        trainingDTOS.add(new TrainingDTO(
                1L,
                "Cạm bẫy khai cuộc",
                null,
                new ArrayList<>()
        ));

        trainingDTOS.add(new TrainingDTO(
                2L,
                "Tuyển tập pháo đầu",
                1L,
                new ArrayList<>()
        ));
        trainingDTOS.add(new TrainingDTO(
                3L,
                "Tuyển tập bình phong mã",
                1L,
                new ArrayList<TrainingDTO>()
        ));
        trainingDTOS.add(new TrainingDTO(
                4L,
                "Tuyển tập phi tượng cục",
                1L,
                new ArrayList<TrainingDTO>()
        ));
        trainingDTOS.add(new TrainingDTO(
                5L,
                "Thuận pháo",
                2L,
                new ArrayList<TrainingDTO>()
        ));
        trainingDTOS.add(new TrainingDTO(
                6L,
                "Nghịch pháo",
                2L,
                new ArrayList<TrainingDTO>()
        ));
        trainingDTOS.add(new TrainingDTO(
                7L,
                "Bài 1",
                5L,
                new ArrayList<TrainingDTO>()
        ));
        trainingDTOS.add(new TrainingDTO(
                8L,
                "Bài 2",
                5L,
                new ArrayList<TrainingDTO>()
        ));

        trainingDTOS.add(new TrainingDTO(
                9L,
                "Cạm bẫy trung cuộc",
                null,
                new ArrayList<>()
        ));

        trainingDTOS.add(new TrainingDTO(
                10L,
                "Cạm bẫy tàn cuộc",
                null,
                new ArrayList<>()
        ));

        for (TrainingDTO childTrainingDTO : trainingDTOS) {
            for (TrainingDTO parentTraining : trainingDTOS) {
                if (parentTraining.getId().equals(childTrainingDTO.getParentTrainingId())) {
                    parentTraining.getChildTrainingDTOs().add(childTrainingDTO);
                }
            }
        }

        return trainingDTOS;
    }

    public static List<TrainingDetailDTO> trainingDetailData() {
        List<TrainingDetailDTO> trainingDetailDTOS = new ArrayList<>();

        /**//**////////////////////////////////////////////////
        long trainingId1 = 7L;

        List<TrainingMoveCreationDTO> trainingMoveCreationDTOs1 = new ArrayList<>();
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 7, 4, 7));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 23, 4, 2));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 11, 6, 7));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 27, 6, 2));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 9, 8, 8));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 25, 7, 0));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 9, 3, 8));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 25, 7, 6));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 9, 3, 1));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 26, 0, 2));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 8, 0, 8));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 22, 1, 9));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 6, 1, 2));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 25, 7, 2));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 9, 6, 1));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 24, 1, 0));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 6, 6, 2));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 29, 8, 2));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 7, 4, 3));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 31, 4, 1));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 6, 8, 2));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 25, 7, 0));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 8, 7, 8));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 25, 5, 0));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 9, 7, 1));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 24, 1, 4));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 6, 8, 0));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 25, 8, 0));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 9, 7, 0));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 25, 7, 0));
        trainingMoveCreationDTOs1.add(new TrainingMoveCreationDTO(trainingId1, 8, 7, 0));

        trainingDetailDTOS.add(Support.buildTrainingDetails(trainingMoveCreationDTOs1));
        /**//**////////////////////////////////////////////////

        return trainingDetailDTOS;
    }

}
