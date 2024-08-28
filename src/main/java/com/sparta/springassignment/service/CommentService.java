package com.sparta.springassignment.service;

import com.sparta.springassignment.dto.CommentDto;
import com.sparta.springassignment.entity.Comment;
import com.sparta.springassignment.entity.Schedule;
import com.sparta.springassignment.repository.CommentRepository;
import com.sparta.springassignment.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    // 댓글 생성 메서드
    public CommentDto createComment(Long scheduleId, CommentDto commentDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid schedule ID: " + scheduleId));

        Comment comment = new Comment(commentDto.getContent(), schedule, commentDto.getAuthorName());
        Comment savedComment = commentRepository.save(comment);

        return convertToDto(savedComment);
    }

    // 댓글 조회 메서드
    @Transactional(readOnly = true)
    public CommentDto getComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID: " + id));

        return convertToDto(comment);
    }

    // 댓글 수정 메서드
    @Transactional
    public CommentDto updateComment(Long id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID: " + id));
        comment.updateComment(commentDto.getContent());

        return convertToDto(comment);
    }

    // 댓글 삭제 메서드
    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    // 엔티티를 DTO로 변환하는 메서드
    private CommentDto convertToDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setAuthorName(comment.getAuthorName());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setUpdatedAt(comment.getUpdatedAt());
        return dto;
    }
}

