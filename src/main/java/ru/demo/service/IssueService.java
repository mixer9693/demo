package ru.demo.service;

import ru.demo.dto.IssueInputDto;
import ru.demo.dto.IssueOutputDto;
import ru.demo.entity.Issue;

public interface IssueService {
    IssueOutputDto create(IssueInputDto dto);
    IssueOutputDto getById(Integer id);
    Issue findById(Integer id);
}
