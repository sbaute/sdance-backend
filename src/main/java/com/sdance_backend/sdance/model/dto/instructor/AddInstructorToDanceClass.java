package com.sdance_backend.sdance.model.dto.instructor;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddInstructorToDanceClass {

    private Integer intructorId;
    private List<Integer> danceClassId;
}
