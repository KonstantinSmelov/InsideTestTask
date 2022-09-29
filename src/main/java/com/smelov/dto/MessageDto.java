package com.smelov.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
//@NoArgsConstructor
public class MessageDto {
    private String name;
    private String message;
}
