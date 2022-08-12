package ru.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.demo.dto.IssueTypeInputDto;
import ru.demo.dto.IssueTypeOutputDto;
import ru.demo.service.IssueTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IssueTypeController {
    private final IssueTypeService issueTypeService;

    @PostMapping("/issue-types")
    public IssueTypeOutputDto create(@RequestBody IssueTypeInputDto issueTypeDto){
        return issueTypeService.create(issueTypeDto);
    }

    @PatchMapping("/issue-types/{id}")
    public IssueTypeOutputDto update(@PathVariable Integer id, @RequestBody IssueTypeInputDto issueTypeDto){
        return issueTypeService.update(id, issueTypeDto);
    }

    @GetMapping("/issue-types")
    public List<IssueTypeOutputDto> getAll(){
        return issueTypeService.getAll();
    }

}
