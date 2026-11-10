package com.landgraf.bffscheduletasks.business;

import com.landgraf.bffscheduletasks.business.dto.in.UserLoginRequest;
import com.landgraf.bffscheduletasks.business.dto.out.TasksDTOResponse;
import com.landgraf.bffscheduletasks.business.enums.NotificationStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TasksService tasksService;
    private final EmailService emailService;
    private final UserService userService;

    @Value("${user.email}")
    private String email;

    @Value("${user.password}")
    private String password;


    @Scheduled(cron = "${cron.time}")
    public void getTasksNextHour(){
        String token = login(converterToRequestDTO());
        LocalDateTime futureHour = LocalDateTime.now().plusHours(1);
        LocalDateTime futureHourPlusFiveMin = LocalDateTime.now().plusHours(1).plusMinutes(5);

        log.info(String.valueOf(futureHour));
        log.info(String.valueOf(futureHourPlusFiveMin));
        log.info(token);
        log.info(email);
        List<TasksDTOResponse> tasksDTOResponseList = tasksService.getTasksBetweenDates(futureHour, futureHourPlusFiveMin, token);

        log.info(tasksDTOResponseList.toString());
        tasksDTOResponseList.forEach(task -> {
            emailService.sendEmail(task);
            tasksService.changeStatus(NotificationStatusEnum.NOTIFIED, task.getId(), token);
        });
    }

    public String login(UserLoginRequest userLoginRequest){
        return userService.login(userLoginRequest);
    }

    public UserLoginRequest converterToRequestDTO(){
        return UserLoginRequest.builder()
                .email(email)
                .password(password)
                .build();
    }
}
