package com.example.pet_project.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ApplicationDTO {
    private Long id;
    private Long postId;
    private Long userId;
    private Boolean isActive;
}
