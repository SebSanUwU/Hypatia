package org.hypatia.api.Hypatia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hypatia.api.Hypatia.utils.RoleEnum;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDto {
    private String email;
    private String name;
    private String lastName;
    @Setter
    private String password;
    private List<RoleEnum> roles;
}
