package com.landgraf.bffscheduletasks.business.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.landgraf.bffscheduletasks.business.enums.NotificationStatusEnum;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TasksDTORequest {


    private String taskName;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime eventDate;
}
