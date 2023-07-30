package com.danielbbontii.mentorshiptracker.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    private String status;
    private Object data;
}
