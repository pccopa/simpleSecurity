package org.edit.shop.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserDetailsService userDetailsService;

    /**
     * En el metodo aplicamos la logica de nuestro filtro.
     *
     * Hacemos doFilter si necesitamos que continue la cadena de filtros.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");  // Aqui viene el JWT
        String jwt = null;
        if (token != null) {
            jwt = token.replaceFirst("Bearer ","");
            // Verificamos el token y ejecutamos nuestra logica
            tokenService.validateToken(jwt);
            UserDetails user = userDetailsService.loadUserByUsername(tokenService.getUserFromToken(jwt));
            UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        }
        // Cuando terminemos conectamos con los otros filtros
        filterChain.doFilter(request, response);
    }


    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

}
