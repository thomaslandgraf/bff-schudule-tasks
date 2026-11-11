package com.landgraf.bffscheduletasks.controller;

import com.landgraf.bffscheduletasks.business.TasksService;
import com.landgraf.bffscheduletasks.business.dto.in.TasksDTORequest;
import com.landgraf.bffscheduletasks.business.dto.out.TasksDTOResponse;
import com.landgraf.bffscheduletasks.business.enums.NotificationStatusEnum;
import com.landgraf.bffscheduletasks.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "Tasks register, tasks update, tasks searches")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TasksController {

    private final TasksService tasksService;

    @PostMapping
    @Operation(summary = "Save a task", description = "Create a new task.")
    @ApiResponse(responseCode = "200", description = "Task saved successfully.")
    @ApiResponse(responseCode = "500", description = "Server Error.")
    public ResponseEntity<TasksDTOResponse> registerTasks(@RequestBody TasksDTORequest tasksDTORequest,
                                                          @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tasksService.registerTasks(tasksDTORequest, token));
    }

    @GetMapping("/events")
    @Operation(summary = "Get tasks by Dates", description = "Get tasks between dates, start date and final date")
    @ApiResponse(responseCode = "200", description = "Task found")
    @ApiResponse(responseCode = "500", description = "Server Error.")
    public ResponseEntity<List<TasksDTOResponse>> getTasksBetweenDates(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate,
                                                                       @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tasksService.getTasksBetweenDates(startDate, finalDate, token));
    }

    @GetMapping("/email")
    @Operation(summary = "Get tasks by Email", description = "Get tasks by email.")
    @ApiResponse(responseCode = "200", description = "Tasks found")
    @ApiResponse(responseCode = "500", description = "Server Error.")
    public ResponseEntity<List<TasksDTOResponse>> getTasksByUserEmail(@RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tasksService.getTasksByUserEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Delete task by Id", description = "delete task by id.")
    @ApiResponse(responseCode = "200", description = "Task deleted")
    @ApiResponse(responseCode = "500", description = "Server Error.")
    public ResponseEntity<Void> deleteById(@RequestParam("id") String id,
                                           @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    @Operation(summary = "Change status", description = "Change notification status from a task.")
    @ApiResponse(responseCode = "200", description = "Notification updated")
    @ApiResponse(responseCode = "500", description = "Server Error.")
    public ResponseEntity<TasksDTOResponse> changeNotificationStatus(@RequestParam("status") NotificationStatusEnum status,
                                                                     @RequestParam("id") String id,
                                                                     @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tasksService.changeStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Update Tasks", description = "Update Tasks.")
    @ApiResponse(responseCode = "200", description = "Tasks updated")
    @ApiResponse(responseCode = "500", description = "Server Error.")
    public ResponseEntity<TasksDTOResponse> updateTasks(@RequestBody TasksDTOResponse tasksDTOResponse,
                                                        @RequestParam("id") String id,
                                                        @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tasksService.updateTasks(tasksDTOResponse, id, token));
    }
}
