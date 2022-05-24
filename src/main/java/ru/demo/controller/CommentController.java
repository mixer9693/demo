package ru.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.demo.dto.CommentInputDto;
import ru.demo.dto.CommentOutputDto;
import ru.demo.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/issues/{id}/comments")
    public CommentOutputDto create(@PathVariable("id") Integer issueId, @RequestBody CommentInputDto commentDto){
        return commentService.create(issueId, commentDto);
    }

    @GetMapping("/issues/{id}/comments")
    public List<CommentOutputDto> getCommentsByIssueId(@PathVariable Integer id){
        return commentService.getByIssueId(id);
    }

    @PatchMapping("/comments/{id}")
    public CommentOutputDto update(@PathVariable Integer id, @RequestBody CommentInputDto commentDto) {
        return commentService.update(id, commentDto);
    }
}
