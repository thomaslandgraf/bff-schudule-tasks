package com.landgraf.bffscheduletasks.business;

import com.landgraf.bffscheduletasks.business.dto.out.TasksDTOResponse;
import com.landgraf.bffscheduletasks.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void sendEmail(TasksDTOResponse tasksDTOResponse){
       emailClient.sendEmail(tasksDTOResponse);
    }
}
