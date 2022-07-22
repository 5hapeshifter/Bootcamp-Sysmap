package br.com.sysmap.framework.exceptions;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class PortabilityExceptionHandler {

    @ExceptionHandler(PortabilityNotFoundException.class) // Captador da excecao da classe especificada
    public ResponseEntity<StandardError> portabilityNotFound(PortabilityNotFoundException erro,
                                                             HttpServletRequest request){
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Portability not found.");
        error.setMessage(erro.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> portabilityDoesNotExists(MethodArgumentNotValidException erro,
                                                             HttpServletRequest request){
        ValidationError error = new ValidationError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError("Portability exception");
        error.setMessage(erro.getMessage());
        error.setPath(request.getRequestURI());

        for (FieldError f : erro.getBindingResult().getFieldErrors()) {
            error.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<StandardError> portability(HttpMessageNotReadableException erro,
                                                     HttpServletRequest request){
        StandardError error = new ValidationError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError("Portability exception");
        error.setMessage("Portability 'Source' and 'Target' has to be filled");
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
