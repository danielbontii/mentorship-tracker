package com.danielbbontii.mentorshiptracker.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    private String status;
    private Object data;
}
