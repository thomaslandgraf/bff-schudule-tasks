package com.landgraf.bffscheduletasks.infrastructure.client;

import com.landgraf.bffscheduletasks.business.dto.in.TasksDTORequest;
import com.landgraf.bffscheduletasks.business.dto.out.TasksDTOResponse;
import com.landgraf.bffscheduletasks.business.enums.NotificationStatusEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "tasks", url = "${tasks.url}")
public interface TasksClient {

    @PostMapping
    TasksDTOResponse registerTasks(@RequestBody TasksDTORequest tasksDTORequest,
                                   @RequestHeader("Authorization") String token);

    @GetMapping("/events")
    List<TasksDTOResponse> getTasksBetweenDates(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate,
                                                @RequestHeader("Authorization") String token);

    @GetMapping("/email")
    List<TasksDTOResponse> getTasksByUserEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deleteById(@RequestParam("id") String id, @RequestHeader("Authorization") String token);


    @PatchMapping
    TasksDTOResponse changeNotificationStatus(@RequestParam("status") NotificationStatusEnum status,
                                              @RequestParam("id") String id,
                                              @RequestHeader("Authorization") String token);


    @PutMapping
    TasksDTOResponse updateTasks(@RequestBody TasksDTOResponse tasksDTOResponse,
                                 @RequestParam("id") String id,
                                 @RequestHeader("Authorization") String token);

}
