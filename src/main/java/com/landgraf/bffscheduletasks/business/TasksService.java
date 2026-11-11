package com.landgraf.bffscheduletasks.business;


import com.landgraf.bffscheduletasks.business.dto.in.TasksDTORequest;
import com.landgraf.bffscheduletasks.business.dto.out.TasksDTOResponse;
import com.landgraf.bffscheduletasks.business.enums.NotificationStatusEnum;
import com.landgraf.bffscheduletasks.infrastructure.client.TasksClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {

    private final TasksClient tasksClient;

    public TasksDTOResponse registerTasks(TasksDTORequest tasksDTORequest, String token){
        return tasksClient.registerTasks(tasksDTORequest, token);
    }

    public List<TasksDTOResponse> getTasksBetweenDates(LocalDateTime startDate, LocalDateTime finalDate, String token){
        return tasksClient.getTasksBetweenDates(startDate, finalDate, token);
    }

    public List<TasksDTOResponse> getTasksByUserEmail(String token){
        return tasksClient.getTasksByUserEmail(token);
    }

    public void deleteById(String id, String token){
        tasksClient.deleteById(id, token);
    }

    public TasksDTOResponse changeStatus(NotificationStatusEnum notificationStatusEnum, String id, String token) {
        return tasksClient.changeNotificationStatus(notificationStatusEnum, id, token);
    }

    public TasksDTOResponse updateTasks(TasksDTOResponse tasksDTOResponse, String id, String token){
        return  tasksClient.updateTasks(tasksDTOResponse, id, token);
    }
}
