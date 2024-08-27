package com.sparta.springassignment.controller;

import com.sparta.springassignment.dto.ScheduleDto;
import com.sparta.springassignment.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
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
        // 서비스 호출하여 일정 생성
        ScheduleDto createdSchedule = scheduleService.createSchedule(scheduleDto);

        // 생성된 일정 반환
        return ResponseEntity.ok(createdSchedule);
    }

    // 일정 단건 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto> getSchedule(@PathVariable Long id) {
        // 서비스 호출하여 일정 조회
        ScheduleDto scheduleDto = scheduleService.getSchedule(id);

        // 조회된 일정 반환
        return ResponseEntity.ok(scheduleDto);
    }

    // 일정 수정 API
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleDto scheduleDto) {
        // 서비스 호출하여 일정 수정
        ScheduleDto updatedSchedule = scheduleService.updateSchedule(id, scheduleDto);

        // 수정된 일정 반환
        return ResponseEntity.ok(updatedSchedule);
    }
}
