package com.sdance_backend.sdance.security.filter;

import com.sdance_backend.sdance.entity.User;
import com.sdance_backend.sdance.exception.CustomException;
import com.sdance_backend.sdance.messages.errors.UserError;
import com.sdance_backend.sdance.security.service.JwtService;
import com.sdance_backend.sdance.service.impl.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 🔹 Ignorar preflight OPTIONS
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        // 🔹 Ignorar login y registro (rutas públicas)
        String path = request.getServletPath();
        if (path.startsWith("/api/v1/auth") || path.startsWith("/api/v1/user/register")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 1. Obtener encabezado HTTP Authorization
        String authorizationHeader = request.getHeader("Authorization");

        // Valida si el encabezado está presente y empieza con "Bearer"
        if (!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Obtener el JWT desde el header (dsp de Bearer)
        String jwt = authorizationHeader.substring(7);

        // 3. Obtengo el subject/username desde el token
        // Esta accion valida el formato del token, firma y fecha de expiración
        String username = jwtService.extractUsername(jwt);

        // 4. Setea objeto Authentication dentro del SecurityContextHolder
        User user = userService.findOneByUsername(username)
                .orElseThrow(() -> new CustomException(UserError.USER_NOT_FOUND));

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username, null, user.getAuthorities()
        );

        authToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 5. Ejecuta el resto de filtros
        filterChain.doFilter(request, response);
    }
}
