package com.sdance_backend.sdance.model.dto;
import com.sdance_backend.sdance.model.entity.enums.Days;
import com.sdance_backend.sdance.model.entity.enums.Hour;
import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
public class DanceClassDto {

    private Integer id;
    private String className;
    private Days dayOfWeek;
    private Hour classTime;
    private Integer instructorId;
    private List<Integer> studentsId;


}