package ru.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.demo.dto.IssueInputDto;
import ru.demo.dto.IssueOutputDto;
import ru.demo.entity.Issue;

@Mapper(componentModel = "spring", uses = {IssueTypeMapper.class})
public interface IssueMapper {
    @Mapping(source = "typeId", target = "type")
    Issue dtoToIssue(IssueInputDto dto);

    @Mapping(source = "type.id", target = "typeId")
    IssueOutputDto issueToDto(Issue issue);

    default Issue idToIssue(Integer id){
        Issue issue = new Issue();
        issue.setId(id);
        return issue;
    }
}
