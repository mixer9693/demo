package ru.demo.service;

import ru.demo.dto.IssueTypeInputDto;
import ru.demo.dto.IssueTypeOutputDto;

import java.util.List;

public interface IssueTypeService {
    IssueTypeOutputDto create(IssueTypeInputDto issueTypeDto);
    IssueTypeOutputDto update(Integer id, IssueTypeInputDto issueTypeDto);
    List<IssueTypeOutputDto> getAll();
}
