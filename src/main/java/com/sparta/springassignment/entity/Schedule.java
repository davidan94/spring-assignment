package com.sparta.springassignment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유 식별자

    @Column(nullable = false)
    private String title; // 할일 제목

    @Column(nullable = false)
    private String content; // 할일 내용

    @Column(nullable = false)
    private String authorName; // 작성 유저명

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // 작성일

    private LocalDateTime updatedAt; // 수정일

    // 생성자 추가
    public Schedule(String title, String content, String authorName) {
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // 수정 메서드
    public void updateSchedule(String title, String content) {
        this.title = title;
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
