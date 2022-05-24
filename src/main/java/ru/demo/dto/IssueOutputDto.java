package ru.demo.dto;

import lombok.Data;

@Data
public class IssueOutputDto {
    private Integer id;
    private Integer typeId;
    private String status;
    private String text;
    private Integer authorId;
}
