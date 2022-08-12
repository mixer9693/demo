package ru.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CommentInputDto {
    @NotEmpty
    private String text;
}
