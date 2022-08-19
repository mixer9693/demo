package ru.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.demo.dto.IssueInputDto;
import ru.demo.dto.IssueOutputDto;
import ru.demo.entity.Issue;
import ru.demo.entity.User;
import ru.demo.mapper.IssueMapper;
import ru.demo.repository.IssueRepository;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository repository;
    private final IssueMapper mapper;

    @Override
    public IssueOutputDto create(IssueInputDto dto, User user) {
        Issue issue = mapper.dtoToIssue(dto);
        issue.setAuthor(user);
        repository.save(issue);
        return mapper.issueToDto(issue);
    }

    @Override
    public IssueOutputDto getById(Integer id) {
        Issue issue = repository.findById(id).orElseThrow();
        return mapper.issueToDto(issue);
    }

    @Override
    public Issue findById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

}
