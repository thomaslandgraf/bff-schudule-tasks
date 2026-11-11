package com.landgraf.bffscheduletasks.infrastructure.exceptions;

import org.jspecify.annotations.Nullable;

public class UnauthorizedException  extends RuntimeException {

    public UnauthorizedException(@Nullable String msg, Throwable cause) {
        super(msg, cause);
    }

    public UnauthorizedException(@Nullable String msg) {
        super(msg);
    }
}
