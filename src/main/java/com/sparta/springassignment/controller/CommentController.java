package com.sparta.springassignment.controller;

import com.sparta.springassignment.dto.CommentDto;
import com.sparta.springassignment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules/{scheduleId}/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 생성 API
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@PathVariable Long scheduleId, @RequestBody CommentDto commentDto) {
        CommentDto createdComment = commentService.createComment(scheduleId, commentDto);

        return ResponseEntity.ok(createdComment);
    }

    // 댓글 단건 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Long id) {
        CommentDto commentDto = commentService.getComment(id);

        return ResponseEntity.ok(commentDto);
    }

    // 댓글 수정 API
    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        CommentDto updatedComment = commentService.updateComment(id, commentDto);

        return ResponseEntity.ok(updatedComment);
    }

    // 댓글 삭제 API
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);

        return ResponseEntity.noContent().build();
    }
}
