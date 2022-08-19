package ru.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.demo.dto.IssueInputDto;
import ru.demo.dto.IssueOutputDto;
import ru.demo.entity.User;
import ru.demo.service.IssueService;

@RestController
@RequiredArgsConstructor
public class IssueController {
    private final IssueService issueService;

    @PostMapping("/issues")
    public IssueOutputDto create(@RequestBody IssueInputDto issueDto, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return issueService.create(issueDto, user);
    }

    @GetMapping("/issues/{id}")
    public IssueOutputDto getById(@PathVariable Integer id){
        return issueService.getById(id);
    }

}
