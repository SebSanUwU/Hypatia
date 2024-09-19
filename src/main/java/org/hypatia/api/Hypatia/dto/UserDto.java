package org.hypatia.api.Hypatia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDto {
    private String email;
    private String name;
    private String lastName;
    private String password;
}
