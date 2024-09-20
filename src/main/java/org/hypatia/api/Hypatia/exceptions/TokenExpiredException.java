package org.hypatia.api.Hypatia.exceptions;


import static org.hypatia.api.Hypatia.utils.Constants.TOKEN_EXPIRED_MALFORMED_ERROR_MESSAGE;

public class TokenExpiredException extends ServerErrorException {

    public TokenExpiredException() {
        super(TOKEN_EXPIRED_MALFORMED_ERROR_MESSAGE);
    }

}
