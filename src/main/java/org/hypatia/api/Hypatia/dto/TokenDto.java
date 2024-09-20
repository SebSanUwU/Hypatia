package org.hypatia.api.Hypatia.dto;

import java.util.Date;

public record TokenDto (

        String token,

        Date expirationDate) {

}

