package com.interview.damian_ozga.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumber {
    private String type;
    private String number;
}