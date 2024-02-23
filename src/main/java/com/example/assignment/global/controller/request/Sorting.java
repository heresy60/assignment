package com.example.assignment.global.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class Sorting<T extends SortingProperty> {

    T property;

    @Schema(description = "정렬 방향", anyOf = Sort.Direction.class)
    Sort.Direction direction = Sort.Direction.DESC;

    public Sort parser() {
        return Sort.by(direction, property.getProperty());
    }
}
