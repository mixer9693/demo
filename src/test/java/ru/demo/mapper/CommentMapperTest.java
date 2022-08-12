package ru.demo.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.demo.dto.CommentInputDto;
import ru.demo.dto.CommentOutputDto;
import ru.demo.entity.Comment;
import ru.demo.entity.Issue;
import ru.demo.entity.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CommentMapperTest {

    private final CommentMapper mapper = Mappers.getMapper(CommentMapper.class);

    @Test
    void dtoToComment() {
        CommentInputDto inputDto = new CommentInputDto();
        inputDto.setText("TestText");

        Comment comment = mapper.dtoToComment(inputDto);

        assertEquals(comment.getText(), inputDto.getText());
    }

    @Test
    void commentToDto() {
        Comment comment = new Comment();
        comment.setId(12);
        comment.setText("CommentText");
        Issue issue = new Issue();
        issue.setId(10);
        comment.setIssue(issue);
        User user = new User();
        user.setId(11);
        comment.setAuthor(user);
        comment.setDateTime(LocalDateTime.now());

        CommentOutputDto dto = mapper.commentToDto(comment);

        assertEquals(dto.getId(), comment.getId());
        assertEquals(dto.getText(), comment.getText());
        assertEquals(dto.getCreated(), comment.getDateTime());
        assertEquals(dto.getAuthorId(), comment.getAuthor().getId());
        assertEquals(dto.getIssueId(), comment.getIssue().getId());
    }

    @Test
    void updateFromDto() {
        CommentInputDto inputDto = new CommentInputDto();
        Comment comment = new Comment();

        mapper.updateFromDto(inputDto, comment);

        assertEquals(comment.getText(), inputDto.getText());
    }
}