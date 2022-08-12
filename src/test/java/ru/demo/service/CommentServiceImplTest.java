package ru.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import ru.demo.dto.CommentInputDto;
import ru.demo.entity.Comment;
import ru.demo.mapper.CommentMapper;
import ru.demo.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentServiceImplTest {

    private CommentRepository repository;
    private CommentMapper mapper;
    private IssueService issueService;
    private CommentService commentService;

    @BeforeEach
    public void setUp(){
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        comments.add(new Comment());
        comments.add(new Comment());
        repository = mock(CommentRepository.class);
        when(repository.findByIssueId(any())).thenReturn(comments);
        when(repository.findById(10)).thenReturn(Optional.of(new Comment()));
        when(repository.findById(AdditionalMatchers.not(eq(10)))).thenReturn(Optional.empty());

        mapper = mock(CommentMapper.class);
        when(mapper.dtoToComment(any())).thenReturn(new Comment());

        issueService = mock(IssueService.class);
        commentService = new CommentServiceImpl(repository, mapper, issueService);
    }


    @Test
    void create() {
        CommentInputDto commentDto = new CommentInputDto();
        commentDto.setText("TextTest");

        commentService.create(10, commentDto);

        verify(issueService, times(1)).findById(10);
        verify(mapper, times(1)).dtoToComment(commentDto);
        verify(repository, times(1)).save(any());
        verify(mapper, times(1)).commentToDto(any());
    }

    @Test
    void getByIssueId() {
        commentService.getByIssueId(10);

        verify(repository, times(1)).findByIssueId(10);
        verify(mapper, times(3)).commentToDto(any());
    }

    @Test
    void update() {
        CommentInputDto commentDto = new CommentInputDto();

        commentService.update(10, commentDto);

        verify(repository, times(1)).findById(10);
        verify(mapper, times(1)).updateFromDto(eq(commentDto), any());
        verify(repository, times(1)).save(any());
        verify(mapper, times(1)).commentToDto(any());

        assertThrows(NoSuchElementException.class, () ->  commentService.update(5, commentDto));
    }
}