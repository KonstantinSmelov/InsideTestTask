package com.smelov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
//@AllArgsConstructor
public class UserDto {
    private String name;
    private String password;
}
