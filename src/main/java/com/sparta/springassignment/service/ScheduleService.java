package com.sparta.springassignment.service;

import com.sparta.springassignment.dto.ScheduleDto;
import com.sparta.springassignment.entity.Schedule;
import com.sparta.springassignment.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 일정 저장 메서드
    public ScheduleDto createSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = new Schedule(
                scheduleDto.getTitle(),
                scheduleDto.getContent(),
                scheduleDto.getAuthorName()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return convertToDto(savedSchedule);
    }

    // 일정 단건 조회 메서드
    @Transactional(readOnly = true)
    public ScheduleDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid schedule ID: " + id));

        return convertToDto(schedule);
    }

    // 일정 수정 메서드
    @Transactional
    public ScheduleDto updateSchedule(Long id, ScheduleDto scheduleDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid schedule ID: " + id));

        schedule.updateSchedule(scheduleDto.getTitle(), scheduleDto.getContent());

        return convertToDto(schedule);
    }

    // 엔티티를 DTO로 변환하는 메서드
    private ScheduleDto convertToDto(Schedule schedule) {
        ScheduleDto dto = new ScheduleDto();
        dto.setId(schedule.getId());
        dto.setTitle(schedule.getTitle());
        dto.setContent(schedule.getContent());
        dto.setAuthorName(schedule.getAuthorName());
        dto.setCreatedAt(schedule.getCreatedAt());
        dto.setUpdatedAt(schedule.getUpdatedAt());
        return dto;
    }
}
