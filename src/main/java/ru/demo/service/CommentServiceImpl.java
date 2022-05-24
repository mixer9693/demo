package ru.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.demo.dto.CommentInputDto;
import ru.demo.dto.CommentOutputDto;
import ru.demo.entity.Comment;
import ru.demo.entity.Issue;
import ru.demo.mapper.CommentMapper;
import ru.demo.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final CommentMapper mapper;
    private final IssueService issueService;

    @Override
    public CommentOutputDto create(Integer issueId, CommentInputDto commentDto) {
        Issue issue = issueService.findById(issueId);
        Comment comment = mapper.dtoToComment(commentDto);
        comment.setIssue(issue);
        repository.save(comment);
        return mapper.commentToDto(comment);
    }

    @Override
    public List<CommentOutputDto> getByIssueId(Integer issueId) {
        return repository.findByIssueId(issueId)
                .stream()
                .map(mapper::commentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentOutputDto update(Integer id, CommentInputDto commentDto) {
        Comment existComment = repository.findById(id).orElseThrow();
        mapper.updateFromDto(commentDto, existComment);
        repository.save(existComment);
        return mapper.commentToDto(existComment);
    }
}
