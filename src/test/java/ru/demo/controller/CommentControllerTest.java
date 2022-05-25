package ru.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.demo.dto.CommentInputDto;
import ru.demo.dto.CommentOutputDto;
import ru.demo.service.CommentService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommentService commentService;

    static CommentOutputDto getOutputDto(String text){
        CommentOutputDto outputDto = new CommentOutputDto();
        outputDto.setText(text);
        outputDto.setCreated(LocalDateTime.now());
        outputDto.setIssueId(10);
        outputDto.setId(6);
        return outputDto;
    }

    @Test
    void create() throws Exception {
        CommentInputDto commentDto = new CommentInputDto();
        commentDto.setText("textText");
        CommentOutputDto outputDto = getOutputDto(commentDto.getText());

        when(commentService.create(10, commentDto)).thenReturn(outputDto);
        when(commentService.create(not(eq(10)), any())).thenThrow(NoSuchElementException.class);

        mockMvc.perform(post("/issues/{id}/comments", 10)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(commentDto))
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.text").value(commentDto.getText()))
                .andExpect(jsonPath("$.issueId").value(10))
                .andExpect(jsonPath("$.created").isNotEmpty());


        mockMvc.perform(post("/issues/{id}/comments", 10)
                        .contentType("application/json")
                        .content("{}")
                ).andExpect(status().isBadRequest());

        mockMvc.perform(post("/issues/{id}/comments", 10)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new CommentInputDto()))
        ).andExpect(status().isBadRequest());

        mockMvc.perform(post("/issues/{id}/comments", 15)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(commentDto))
        ).andExpect(status().isNotFound());
    }

    @Test
    void getCommentsByIssueId() throws Exception {
        List<CommentOutputDto> comments = new ArrayList<>();
        comments.add(getOutputDto("com1"));
        comments.add(getOutputDto("com2"));

        when(commentService.getByIssueId(10)).thenReturn(comments);
        when(commentService.getByIssueId(not(eq(10)))).thenThrow(NoSuchElementException.class);

        mockMvc.perform(get("/issues/{id}/comments", 10))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        mockMvc.perform(get("/issues/{id}/comments", 15))
                .andExpect(status().isNotFound());
    }

    @Test
    void update() throws Exception {
        CommentInputDto commentDto = new CommentInputDto();
        commentDto.setText("NewText");

        when(commentService.update(eq(10), any())).thenReturn(new CommentOutputDto());
        when(commentService.update(not(eq(10)), any())).thenThrow(NoSuchElementException.class);

        mockMvc.perform(patch("/comments/{id}", 10)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(commentDto)))
                .andExpect(status().isOk());

        mockMvc.perform(patch("/comments/{id}", 10)
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isBadRequest());

        mockMvc.perform(patch("/comments/{id}", 15)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(commentDto)))
                .andExpect(status().isNotFound());

    }
}