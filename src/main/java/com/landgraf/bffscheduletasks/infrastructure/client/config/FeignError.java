package com.landgraf.bffscheduletasks.infrastructure.client.config;

import com.landgraf.bffscheduletasks.infrastructure.exceptions.BusinessException;
import com.landgraf.bffscheduletasks.infrastructure.exceptions.ConflictException;
import com.landgraf.bffscheduletasks.infrastructure.exceptions.ResourceNotFoundException;
import com.landgraf.bffscheduletasks.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        switch (response.status()){
            case 409:
                return new ConflictException("Attribute already exist.");
            case 403:
                return new ResourceNotFoundException("Attribute not found");
            case 401:
                return new UnauthorizedException("User not authorized.");
            default:
                return new BusinessException("Server Error.");
        }
    }
}
