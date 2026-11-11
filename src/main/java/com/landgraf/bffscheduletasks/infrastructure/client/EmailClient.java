package com.landgraf.bffscheduletasks.infrastructure.client;


import com.landgraf.bffscheduletasks.business.dto.out.TasksDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "notification", url = "${notification.url}")
public interface EmailClient {

    @PostMapping
    void sendEmail(@RequestBody TasksDTOResponse tasksDTOResponse);

}
