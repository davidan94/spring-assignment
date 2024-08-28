package com.sparta.springassignment.controller;

import com.sparta.springassignment.dto.ScheduleDto;
import com.sparta.springassignment.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 일정 생성 API
    @PostMapping
    public ResponseEntity<ScheduleDto> createSchedule(@RequestBody ScheduleDto scheduleDto) {
        ScheduleDto createdSchedule = scheduleService.createSchedule(scheduleDto);

        return ResponseEntity.ok(createdSchedule);
    }

    // 일정 단건 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto> getSchedule(@PathVariable Long id) {
        ScheduleDto scheduleDto = scheduleService.getSchedule(id);

        return ResponseEntity.ok(scheduleDto);
    }

    // 일정 수정 API
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleDto scheduleDto) {
        ScheduleDto updatedSchedule = scheduleService.updateSchedule(id, scheduleDto);

        return ResponseEntity.ok(updatedSchedule);
    }

    // 3단계: 페이징된 일정 조회 API
    @GetMapping
    public ResponseEntity<Page<ScheduleDto>> getSchedules(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<ScheduleDto> schedulePage = scheduleService.getSchedules(pageable);

        return ResponseEntity.ok(schedulePage);
    }

    // 4단계: 일정 삭제 API
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);

        return ResponseEntity.noContent().build();
    }
}
