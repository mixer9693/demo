package ru.demo.service;

import ru.demo.dto.IssueInputDto;
import ru.demo.dto.IssueOutputDto;
import ru.demo.entity.Issue;
import ru.demo.entity.User;

public interface IssueService {
    IssueOutputDto create(IssueInputDto dto, User user);
    IssueOutputDto getById(Integer id);
    Issue findById(Integer id);
}
