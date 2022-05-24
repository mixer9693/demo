package ru.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.demo.dto.IssueTypeInputDto;
import ru.demo.dto.IssueTypeOutputDto;
import ru.demo.entity.IssueType;

@Mapper(componentModel = "spring")
public interface IssueTypeMapper {
    default IssueType idToIssueType(Integer id){
        IssueType issueType = new IssueType();
        issueType.setId(id);
        return issueType;
    }

    default Integer issueTypeToId(IssueType issueType){
        return issueType.getId();
    }

    IssueType dtoToIssueType(IssueTypeInputDto dto);

    IssueTypeOutputDto issueTypeToDto(IssueType issueType);

    void updateFromDto(IssueTypeInputDto dto, @MappingTarget IssueType issueType);
}
