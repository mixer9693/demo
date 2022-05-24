package ru.demo.dto;

import lombok.Data;

@Data
public class IssueInputDto {
    private Integer typeId;
    private String text;
}
