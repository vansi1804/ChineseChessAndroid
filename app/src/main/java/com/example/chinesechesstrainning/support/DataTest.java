package com.example.chinesechesstrainning.support;

import android.util.Log;

import com.example.chinesechesstrainning.R;
import com.example.chinesechesstrainning.model.PieceDTO;
import com.example.chinesechesstrainning.model.move.TrainingMoveCreationDTO;
import com.example.chinesechesstrainning.model.training.TrainingDTO;
import com.example.chinesechesstrainning.model.training.TrainingDetailDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        String TAG = "Find all training";
        List<TrainingDTO> trainingDTOs = trainingDataTest();
        Log.d(TAG, "training1: " + trainingDTOs.toString());
        Log.d(TAG, "training2: " + trainingJsonDataTest().toString());
        return trainingDTOs;
    }

    public static List<TrainingDTO> trainingJsonDataTest() {
        String jsonData = "[\n" +
                "    {\n" +
                "        \"createdDate\": \"10-06-2024 07:36:53\",\n" +
                "        \"createdByUserId\": 1,\n" +
                "        \"lastModifiedDate\": \"10-06-2024 07:36:53\",\n" +
                "        \"lastModifiedByUserId\": 1,\n" +
                "        \"id\": 1,\n" +
                "        \"title\": \"Cạm bẫy khai cuộc\",\n" +
                "        \"parentTrainingId\": null,\n" +
                "        \"childTrainingDTOs\": [\n" +
                "            {\n" +
                "                \"createdDate\": \"10-06-2024 07:43:57\",\n" +
                "                \"createdByUserId\": 1,\n" +
                "                \"lastModifiedDate\": \"10-06-2024 07:43:57\",\n" +
                "                \"lastModifiedByUserId\": 1,\n" +
                "                \"id\": 4,\n" +
                "                \"title\": \"Tuyển tập pháo đầu\",\n" +
                "                \"parentTrainingId\": 1,\n" +
                "                \"childTrainingDTOs\": [\n" +
                "                    {\n" +
                "                        \"createdDate\": \"10-06-2024 07:44:58\",\n" +
                "                        \"createdByUserId\": 1,\n" +
                "                        \"lastModifiedDate\": \"10-06-2024 07:44:58\",\n" +
                "                        \"lastModifiedByUserId\": 1,\n" +
                "                        \"id\": 7,\n" +
                "                        \"title\": \"Thuận pháo\",\n" +
                "                        \"parentTrainingId\": 4,\n" +
                "                        \"childTrainingDTOs\": [\n" +
                "                            {\n" +
                "                                \"createdDate\": \"10-06-2024 07:45:54\",\n" +
                "                                \"createdByUserId\": 1,\n" +
                "                                \"lastModifiedDate\": \"10-06-2024 07:45:54\",\n" +
                "                                \"lastModifiedByUserId\": 1,\n" +
                "                                \"id\": 11,\n" +
                "                                \"title\": \"Bài 1\",\n" +
                "                                \"parentTrainingId\": 7,\n" +
                "                                \"childTrainingDTOs\": []\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"createdDate\": \"10-06-2024 07:45:57\",\n" +
                "                                \"createdByUserId\": 1,\n" +
                "                                \"lastModifiedDate\": \"10-06-2024 07:45:57\",\n" +
                "                                \"lastModifiedByUserId\": 1,\n" +
                "                                \"id\": 12,\n" +
                "                                \"title\": \"Bài 2\",\n" +
                "                                \"parentTrainingId\": 7,\n" +
                "                                \"childTrainingDTOs\": []\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"createdDate\": \"10-06-2024 07:46:00\",\n" +
                "                                \"createdByUserId\": 1,\n" +
                "                                \"lastModifiedDate\": \"10-06-2024 07:46:00\",\n" +
                "                                \"lastModifiedByUserId\": 1,\n" +
                "                                \"id\": 13,\n" +
                "                                \"title\": \"Bài 3\",\n" +
                "                                \"parentTrainingId\": 7,\n" +
                "                                \"childTrainingDTOs\": []\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"createdDate\": \"10-06-2024 07:45:02\",\n" +
                "                        \"createdByUserId\": 1,\n" +
                "                        \"lastModifiedDate\": \"10-06-2024 07:45:02\",\n" +
                "                        \"lastModifiedByUserId\": 1,\n" +
                "                        \"id\": 8,\n" +
                "                        \"title\": \"Nghịch pháo\",\n" +
                "                        \"parentTrainingId\": 4,\n" +
                "                        \"childTrainingDTOs\": []\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"createdDate\": \"10-06-2024 07:45:10\",\n" +
                "                        \"createdByUserId\": 1,\n" +
                "                        \"lastModifiedDate\": \"10-06-2024 07:45:10\",\n" +
                "                        \"lastModifiedByUserId\": 1,\n" +
                "                        \"id\": 9,\n" +
                "                        \"title\": \"Uyên ương pháo\",\n" +
                "                        \"parentTrainingId\": 4,\n" +
                "                        \"childTrainingDTOs\": []\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"createdDate\": \"10-06-2024 07:45:19\",\n" +
                "                        \"createdByUserId\": 1,\n" +
                "                        \"lastModifiedDate\": \"10-06-2024 07:45:19\",\n" +
                "                        \"lastModifiedByUserId\": 1,\n" +
                "                        \"id\": 10,\n" +
                "                        \"title\": \"Tuần hà pháo\",\n" +
                "                        \"parentTrainingId\": 4,\n" +
                "                        \"childTrainingDTOs\": []\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"createdDate\": \"10-06-2024 07:44:07\",\n" +
                "                \"createdByUserId\": 1,\n" +
                "                \"lastModifiedDate\": \"10-06-2024 07:44:07\",\n" +
                "                \"lastModifiedByUserId\": 1,\n" +
                "                \"id\": 5,\n" +
                "                \"title\": \"Tuyển tập bình phong mã\",\n" +
                "                \"parentTrainingId\": 1,\n" +
                "                \"childTrainingDTOs\": []\n" +
                "            },\n" +
                "            {\n" +
                "                \"createdDate\": \"10-06-2024 07:44:25\",\n" +
                "                \"createdByUserId\": 1,\n" +
                "                \"lastModifiedDate\": \"10-06-2024 07:44:25\",\n" +
                "                \"lastModifiedByUserId\": 1,\n" +
                "                \"id\": 6,\n" +
                "                \"title\": \"Tuyển tập bình phi tượng cục\",\n" +
                "                \"parentTrainingId\": 1,\n" +
                "                \"childTrainingDTOs\": []\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"createdDate\": \"10-06-2024 07:43:18\",\n" +
                "        \"createdByUserId\": 1,\n" +
                "        \"lastModifiedDate\": \"10-06-2024 07:43:18\",\n" +
                "        \"lastModifiedByUserId\": 1,\n" +
                "        \"id\": 2,\n" +
                "        \"title\": \"Cạm bẫy trung cuộc\",\n" +
                "        \"parentTrainingId\": null,\n" +
                "        \"childTrainingDTOs\": []\n" +
                "    },\n" +
                "    {\n" +
                "        \"createdDate\": \"10-06-2024 07:43:22\",\n" +
                "        \"createdByUserId\": 1,\n" +
                "        \"lastModifiedDate\": \"10-06-2024 07:43:22\",\n" +
                "        \"lastModifiedByUserId\": 1,\n" +
                "        \"id\": 3,\n" +
                "        \"title\": \"Cạm bẫy tàn cuộc\",\n" +
                "        \"parentTrainingId\": null,\n" +
                "        \"childTrainingDTOs\": []\n" +
                "    }\n" +
                "]";

        try {
            return new ObjectMapper().readValue(jsonData, new TypeReference<List<TrainingDTO>>() {
            });
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    public static List<TrainingDTO> trainingDataTest() {
        List<TrainingDTO> trainingDTOs = new ArrayList<>(Arrays.asList(
                new TrainingDTO(
                        1L,
                        "Cạm bẫy khai cuộc",
                        null,
                        new ArrayList<>(Arrays.asList(
                                new TrainingDTO(
                                        4L,
                                        "Tuyển tập pháo đầu",
                                        1L,
                                        new ArrayList<>(Arrays.asList(
                                                new TrainingDTO(
                                                        7L,
                                                        "Thuận pháo",
                                                        4L,
                                                        new ArrayList<>(Arrays.asList(
                                                                new TrainingDTO(11L, "Bài 1", 7L, new ArrayList<>()),
                                                                new TrainingDTO(12L, "Bài 2", 7L, new ArrayList<>()),
                                                                new TrainingDTO(13L, "Bài 3", 7L, new ArrayList<>())
                                                        ))
                                                ),
                                                new TrainingDTO(8L, "Nghịch pháo", 4L, new ArrayList<>()),
                                                new TrainingDTO(9L, "Uyên ương pháo", 4L, new ArrayList<>()),
                                                new TrainingDTO(10L, "Tuần hà pháo", 4L, new ArrayList<>())
                                        ))
                                ),
                                new TrainingDTO(5L, "Tuyển tập bình phong mã", 1L, new ArrayList<>()),
                                new TrainingDTO(6L, "Tuyển tập bình phi tượng cục", 1L, new ArrayList<>())
                        ))
                ),
                new TrainingDTO(2L, "Cạm bẫy trung cuộc", null, new ArrayList<>()),
                new TrainingDTO(3L, "Cạm bẫy tàn cuộc", null, new ArrayList<>())
        ));

        return trainingDTOs;
    }

    public static List<TrainingDetailDTO> trainingDetailData() {
        List<TrainingDetailDTO> trainingDetailDTOS = new ArrayList<>();

        /**//**////////////////////////////////////////////////
        long trainingId1 = 11L;

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
