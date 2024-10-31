package com.sdance_backend.sdance.dto.danceClass;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddStudentsToDanceClassDto {
    private Integer danceClassId;
    private List<Integer> studentId;
}
