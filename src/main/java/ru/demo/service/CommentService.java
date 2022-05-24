package ru.demo.service;

import ru.demo.dto.CommentInputDto;
import ru.demo.dto.CommentOutputDto;

import java.util.List;

public interface CommentService {
    CommentOutputDto create(Integer issueId, CommentInputDto commentDto);
    CommentOutputDto update(Integer id, CommentInputDto commentDto);
    List<CommentOutputDto> getByIssueId(Integer issueId);
}
