package com.restapi.exception.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom authentication entry point to delegate handling of authentication exceptions to a configured
 * {@link HandlerExceptionResolver}. This allows for custom exception handling and response generation.
 */
@Component("delegatedAuthenticationEntryPoint")
public class DelegatedAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    /**
     * Invoked when an unauthenticated user tries to access a secured resource.
     * Delegates the handling of the authentication exception to the configured exception resolver.
     * @param request       The request being handled.
     * @param response      The response to be populated with authentication-related information.
     * @param authException The authentication exception that occurred.
     * @throws IOException      In case of I/O errors.
     * @throws ServletException In case of servlet-related errors.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                       AuthenticationException authException) throws IOException, ServletException {
        resolver.resolveException(request, response, null, authException);
    }
}
