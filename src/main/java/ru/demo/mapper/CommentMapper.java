package ru.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.demo.dto.CommentInputDto;
import ru.demo.dto.CommentOutputDto;
import ru.demo.entity.Comment;

@Mapper(componentModel = "spring", uses = {IssueTypeMapper.class})
public interface CommentMapper {
    Comment dtoToComment(CommentInputDto dto);

    @Mapping(source = "issue.id", target = "issueId")
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "dateTime", target = "created")
    CommentOutputDto commentToDto(Comment comment);

    void updateFromDto(CommentInputDto dto, @MappingTarget Comment comment);
}
