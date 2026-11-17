package com.landgraf.bffscheduletasks.infrastructure.client.config;

import com.landgraf.bffscheduletasks.infrastructure.exceptions.*;
import com.landgraf.bffscheduletasks.infrastructure.exceptions.IllegalArgumentException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        String errMessage = errorMessage(response);
        switch (response.status()) {
            case 400:
                return new IllegalArgumentException("Error: " + errMessage);
            case 401:
                return new UnauthorizedException("Error: " + errMessage);
            case 403:
                return new ForbiddenException("Error: " + errMessage);
            case 404:
                return new ResourceNotFoundException("Error: " + errMessage);
            case 409:
                return new ConflictException("Error: " + errMessage);
            default:
                return new BusinessException("Error: " + errMessage);
        }
    }

    private String errorMessage(Response response){
        try {
            if(Objects.isNull(response.body())){
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
