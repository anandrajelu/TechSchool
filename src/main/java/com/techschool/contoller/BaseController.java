package com.techschool.contoller;

import com.techschool.carriers.ServiceResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public class BaseController {

    public static ResponseEntity<?> getResponseEntity(ServiceResponse response) {
        ResponseEntity<?> result = null;
        switch (response.getStatus()) {
            case OK:
                result = ResponseEntity.ok(response);
                break;
            case BAD_REQUEST:
                result = ResponseEntity.badRequest().build();
                break;
            case CREATED:
                result = ResponseEntity.created(URI.create(response.getMessage()))
                        .contentType(MediaType.APPLICATION_JSON).body(response.getResponse());
                break;
            case NOT_FOUND:
                result = ResponseEntity.notFound().build();
                break;
        }
        return result;
    }

}
