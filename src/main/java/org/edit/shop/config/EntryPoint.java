package org.edit.shop.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EntryPoint implements AuthenticationEntryPoint {

    /**
     * commence lo utiliza Spring Security cuando se requiere autenticacion y no esta en el request.
     * Es decir, se necesita un JWT y no lo tiene.
     *
     * De tratarse de una aplicacion con paginas jsp se podria hacer un redirect a la pagina de login.
     * En nuestro caso solamente rechazamos la solicitud, ya que el frontend es un proyecto diferente
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
