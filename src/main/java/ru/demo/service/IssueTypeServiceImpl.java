package ru.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.demo.dto.IssueTypeInputDto;
import ru.demo.dto.IssueTypeOutputDto;
import ru.demo.entity.IssueType;
import ru.demo.mapper.IssueTypeMapper;
import ru.demo.repository.IssueTypeRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class IssueTypeServiceImpl implements IssueTypeService {

    private final IssueTypeRepository repository;
    private final IssueTypeMapper mapper;

    @Override
    public IssueTypeOutputDto create(IssueTypeInputDto issueTypeDto) {
        IssueType issueType = mapper.dtoToIssueType(issueTypeDto);
        repository.save(issueType);
        return mapper.issueTypeToDto(issueType);
    }

    @Override
    public IssueTypeOutputDto update(Integer id, IssueTypeInputDto issueTypeDto) {
        IssueType existIssueType = repository.findById(id).orElseThrow();
        mapper.updateFromDto(issueTypeDto, existIssueType);
        repository.save(existIssueType);
        return mapper.issueTypeToDto(existIssueType);
    }

    @Override
    public List<IssueTypeOutputDto> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::issueTypeToDto)
                .collect(Collectors.toList());
    }
}
