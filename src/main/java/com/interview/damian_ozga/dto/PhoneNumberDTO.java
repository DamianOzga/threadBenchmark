package com.interview.damian_ozga.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumberDTO {
    private String type;
    private String number;
}