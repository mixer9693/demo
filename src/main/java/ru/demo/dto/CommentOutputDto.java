package ru.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentOutputDto {
    private Integer id;
    private String text;
    private Integer issueId;
    private Integer authorId;
    private LocalDateTime created;
}
