package br.com.sysmap.framework.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;

@ExtendWith(SpringExtension.class)
class PortabilityExceptionHandlerTest {

    @InjectMocks
    private PortabilityExceptionHandler portabilityExceptionHandler;

    @Mock
    MethodArgumentNotValidException methodArgumentNotValidException;

    @Test
    void portabilityNotFoundShouldReturnResponseEntityStandardError() {
        PortabilityNotFoundException exception = new PortabilityNotFoundException("");
        HttpServletRequest httpServletRequest = new MockHttpServletRequest();
        ResponseEntity<StandardError> result = portabilityExceptionHandler.portabilityNotFound(exception, httpServletRequest);
        Assertions.assertNotNull(result);
    }

    @Test
    void portabilityReturnResponseEntityStandardError() {
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException("");
        HttpServletRequest httpServletRequest = new MockHttpServletRequest();
        var result = portabilityExceptionHandler.portability(exception, httpServletRequest);
        Assertions.assertNotNull(result);
    }
}