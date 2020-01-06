package br.com.crud.api.exceptionhandler;

import br.com.crud.api.exceptionhandler.error.ResourceError;
import br.com.crud.api.exceptionhandler.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CrudExceptionHandler {

    private static final String RESOURCE_NOT_FOUND = "Recurso não encontrado";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceError> handleResourceNotFoundException
            (ResourceNotFoundException rfnException) {

        ResourceError resourceError = ResourceError.builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .tilte(RESOURCE_NOT_FOUND)
                .userMessage(rfnException.getMessage())
                .developerMessage(rfnException.toString())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceError);
    }
}
